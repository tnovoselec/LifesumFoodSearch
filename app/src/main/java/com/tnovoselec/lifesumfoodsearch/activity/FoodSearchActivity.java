package com.tnovoselec.lifesumfoodsearch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.tnovoselec.lifesumfoodsearch.R;
import com.tnovoselec.lifesumfoodsearch.adapter.FoodItemsAdapter;
import com.tnovoselec.lifesumfoodsearch.di.BaseActivity;
import com.tnovoselec.lifesumfoodsearch.di.component.ActivityComponent;
import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;
import com.tnovoselec.lifesumfoodsearch.presenter.FoodSearchPresenter;
import com.tnovoselec.lifesumfoodsearch.view.FoodSearchView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.widget.WidgetObservable;

public class FoodSearchActivity extends BaseActivity implements FoodSearchView {

  private static final long DEBOUNCE_OFFSET = 350L;
  private static final int MINIMUM_QUERY_LENGTH = 3;

  @Bind(R.id.food_search_recycler)
  RecyclerView foodItemsRecycler;
  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Bind(R.id.food_search_empty)
  View foodItemsEmpty;
  @Bind(R.id.food_search_label)
  EditText foodSearchLabel;
  @Bind(R.id.food_search_progress_container)
  View foodSearchProgressContainer;

  @Inject
  FoodSearchPresenter foodSearchPresenter;

  private FoodItemsAdapter foodItemsAdapter;
  private Subscription searchSubscription;

  public static Intent createIntent(Context context) {
    return new Intent(context, FoodSearchActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_food_search);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    foodItemsRecycler.setLayoutManager(new LinearLayoutManager(this));
    foodItemsRecycler.setHasFixedSize(true);
  }

  @Override
  protected void onResume() {
    super.onResume();
    foodSearchPresenter.activate();
    foodSearchPresenter.takeView(this);
    bindSearch();
  }


  @Override
  protected void onPause() {
    super.onPause();
    foodSearchPresenter.deactivate();
    foodSearchPresenter.releaseView();
    if (searchSubscription != null && !searchSubscription.isUnsubscribed()) {
      searchSubscription.unsubscribe();
    }
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Override
  public void renderItems(List<FoodItemViewModel> foodItems) {
    foodItemsAdapter = new FoodItemsAdapter(foodItems, new OnFoodItemClickHandler(), FoodItemsAdapter.Mode.LIST);
    foodItemsRecycler.setAdapter(foodItemsAdapter);
    foodItemsEmpty.setVisibility(foodItems.isEmpty() ? View.VISIBLE : View.GONE);
    foodItemsRecycler.setVisibility(!foodItems.isEmpty() ? View.VISIBLE : View.GONE);
  }

  @Override
  public void showProgress() {
    foodSearchProgressContainer.setVisibility(View.VISIBLE);
    foodSearchProgressContainer.animate().alpha(1);
  }

  @Override
  public void hideProgress() {

    foodSearchProgressContainer.animate()
        .alpha(0)
        .withEndAction(() -> foodSearchProgressContainer.setVisibility(View.GONE));
  }

  private void bindSearch() {
    searchSubscription = WidgetObservable.text(foodSearchLabel)
        .debounce(DEBOUNCE_OFFSET, TimeUnit.MILLISECONDS)
        .map(onTextChangeEvent -> onTextChangeEvent.text().toString())
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::performSearch,
            this::onError
        );
  }

  private void performSearch(String query) {
    if (!TextUtils.isEmpty(query) && query.length() >= MINIMUM_QUERY_LENGTH) {
      foodSearchPresenter.searchForFood(query);
    }
  }

  private void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @OnClick(R.id.food_search_clear)
  public void onLabelClear() {
    foodSearchLabel.setText("");
  }

  private class OnFoodItemClickHandler implements FoodItemsAdapter.OnFoodItemClickedListener {

    @Override
    public void onFoodItemClicked(FoodItemViewModel foodItemViewModel) {
      foodSearchPresenter.onFoodItemClicked(foodItemViewModel);
    }
  }

}
