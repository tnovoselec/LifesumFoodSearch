package com.tnovoselec.lifesumfoodsearch.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tnovoselec.lifesumfoodsearch.R;
import com.tnovoselec.lifesumfoodsearch.adapter.FoodItemsAdapter;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.di.BaseActivity;
import com.tnovoselec.lifesumfoodsearch.di.component.ActivityComponent;
import com.tnovoselec.lifesumfoodsearch.presenter.FoodListPresenter;
import com.tnovoselec.lifesumfoodsearch.view.FoodListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements FoodListView {

  @Bind(R.id.food_items_recycler)
  RecyclerView foodItemsRecycler;
  @Bind(R.id.toolbar)
  Toolbar toolbar;

  @Inject
  FoodListPresenter foodListPresenter;

  private FoodItemsAdapter foodItemsAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setSupportActionBar(toolbar);

    foodItemsRecycler.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
    foodItemsAdapter = new FoodItemsAdapter();
    foodItemsRecycler.setAdapter(foodItemsAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
    foodListPresenter.activate();
    foodListPresenter.takeView(this);
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
  public void renderItems(List<DbFoodItem> foodItems) {
    foodItemsAdapter.setData(foodItems);
  }

  @Override
  public void showProgress() {

  }

  @Override
  public void hideProgress() {

  }
}
