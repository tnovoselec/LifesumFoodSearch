package com.tnovoselec.lifesumfoodsearch.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tnovoselec.lifesumfoodsearch.R;
import com.tnovoselec.lifesumfoodsearch.adapter.FoodItemsAdapter;
import com.tnovoselec.lifesumfoodsearch.adapter.FoodItemsAdapter.Mode;
import com.tnovoselec.lifesumfoodsearch.adapter.FoodItemsAdapter.OnFoodItemClickedListener;
import com.tnovoselec.lifesumfoodsearch.di.BaseActivity;
import com.tnovoselec.lifesumfoodsearch.di.component.ActivityComponent;
import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;
import com.tnovoselec.lifesumfoodsearch.presenter.FoodListPresenter;
import com.tnovoselec.lifesumfoodsearch.ui.DividerItemDecoration;
import com.tnovoselec.lifesumfoodsearch.ui.OffsetItemDecoration;
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
  private OffsetItemDecoration offsetItemDecoration;
  private DividerItemDecoration dividerItemDecoration;
  private Mode mode = Mode.LIST;
  private List<FoodItemViewModel> foodItemViewModels;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_food_list);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);

    foodItemsRecycler.setHasFixedSize(true);
    offsetItemDecoration = new OffsetItemDecoration(R.dimen.grid_spacing);
    dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
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
    MenuItem listItem = menu.findItem(R.id.action_list);
    listItem.setVisible(mode == Mode.GRID);

    MenuItem gridItem = menu.findItem(R.id.action_grid);
    gridItem.setVisible(mode == Mode.LIST);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_search:
        foodListPresenter.onSearchClicked();
        return true;
      case R.id.action_list:
        mode = Mode.LIST;
        showAsList(foodItemViewModels);
        invalidateOptionsMenu();
        return true;
      case R.id.action_grid:
        mode = Mode.GRID;
        showAsGrid(foodItemViewModels);
        invalidateOptionsMenu();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void renderItems(List<FoodItemViewModel> foodItems) {
    this.foodItemViewModels = foodItems;
    if (mode == Mode.LIST) {
      showAsList(foodItems);
    } else {
      showAsGrid(foodItems);
    }
    foodItemsEmpty.setVisibility(foodItems.isEmpty() ? View.VISIBLE : View.GONE);
  }

  private void showAsList(List<FoodItemViewModel> foodItems) {
    foodItemsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    foodItemsRecycler.removeItemDecoration(offsetItemDecoration);
    foodItemsRecycler.addItemDecoration(dividerItemDecoration);
    foodItemsAdapter = new FoodItemsAdapter(foodItems, new OnFoodItemClickHandler(), Mode.LIST);
    foodItemsRecycler.setAdapter(foodItemsAdapter);
  }

  private void showAsGrid(List<FoodItemViewModel> foodItems) {
    foodItemsRecycler.setLayoutManager(new GridLayoutManager(this, 2));
    foodItemsRecycler.removeItemDecoration(dividerItemDecoration);
    foodItemsRecycler.addItemDecoration(offsetItemDecoration);
    foodItemsAdapter = new FoodItemsAdapter(foodItems, new OnFoodItemClickHandler(), Mode.GRID);
    foodItemsRecycler.setAdapter(foodItemsAdapter);
  }

  private class OnFoodItemClickHandler implements OnFoodItemClickedListener {

    @Override
    public void onFoodItemClicked(FoodItemViewModel dbFoodItem) {
      foodListPresenter.onFoodItemClicked(dbFoodItem);
    }
  }

}
