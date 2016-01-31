package com.tnovoselec.lifesumfoodsearch.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tnovoselec.lifesumfoodsearch.R;
import com.tnovoselec.lifesumfoodsearch.adapter.FoodItemsAdapter;
import com.tnovoselec.lifesumfoodsearch.adapter.FoodItemsAdapter.OnFoodItemClickedListener;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.di.BaseActivity;
import com.tnovoselec.lifesumfoodsearch.di.component.ActivityComponent;
import com.tnovoselec.lifesumfoodsearch.presenter.FoodListPresenter;
import com.tnovoselec.lifesumfoodsearch.view.FoodListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodListActivity extends BaseActivity implements FoodListView {

  @Bind(R.id.food_items_recycler)
  RecyclerView foodItemsRecycler;
  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Bind(R.id.food_items_empty)
  View foodItemsEmpty;

  @Inject
  FoodListPresenter foodListPresenter;

  private FoodItemsAdapter foodItemsAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_food_list);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);

    foodItemsRecycler.setHasFixedSize(true);
    foodItemsRecycler.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
  }

  @Override
  protected void onResume() {
    super.onResume();
    foodListPresenter.activate();
    foodListPresenter.takeView(this);
    foodListPresenter.loadItems();
  }

  @Override
  protected void onPause() {
    super.onPause();
    foodListPresenter.deactivate();
    foodListPresenter.releaseView();
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_food_list, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_search:
        foodListPresenter.onSearchClicked();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void renderItems(List<DbFoodItem> foodItems) {
    foodItemsAdapter = new FoodItemsAdapter(foodItems, new OnFoodItemClickHandler());
    foodItemsRecycler.setAdapter(foodItemsAdapter);
    foodItemsEmpty.setVisibility(foodItems.isEmpty() ? View.VISIBLE : View.GONE);
  }

  private class OnFoodItemClickHandler implements OnFoodItemClickedListener {

    @Override
    public void onFoodItemClicked(DbFoodItem dbFoodItem) {
      foodListPresenter.onFoodItemClicked(dbFoodItem);
    }
  }

}
