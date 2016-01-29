package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.api.LifesumClient;
import com.tnovoselec.lifesumfoodsearch.db.dao.FoodDao;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.view.FoodListView;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FoodListPresenterImpl extends BasePresenter implements FoodListPresenter {

  private LifesumClient lifesumClient;
  private FoodListView foodListView;
  private FoodDao foodDao;

  @Inject
  public FoodListPresenterImpl(LifesumClient lifesumClient) {
    this.lifesumClient = lifesumClient;
  }

  public void takeView(FoodListView foodListView) {
    this.foodListView = foodListView;
  }

  public void releaseView() {
    this.foodListView = null;
  }

  @Override
  public void loadItems() {
    addSubscribtion(
        foodDao.loadFoodItems()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onDbFoodItemsLoaded,
                this::onDbFoodItemsLoadFailed
            )
    );
  }

  private void onDbFoodItemsLoaded(List<DbFoodItem> foodItems) {
    if (foodListView != null) {
      foodListView.renderItems(foodItems);
    }
  }

  private void onDbFoodItemsLoadFailed(Throwable throwable) {

  }
}
