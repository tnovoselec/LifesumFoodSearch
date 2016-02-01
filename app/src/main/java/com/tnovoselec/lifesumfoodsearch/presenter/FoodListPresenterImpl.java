package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.Router;
import com.tnovoselec.lifesumfoodsearch.business.DbToViewModelConverter;
import com.tnovoselec.lifesumfoodsearch.db.dao.FoodDao;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;
import com.tnovoselec.lifesumfoodsearch.view.FoodListView;

import java.util.List;

import javax.inject.Inject;

public class FoodListPresenterImpl extends BasePresenter implements FoodListPresenter {

  private final FoodDao foodDao;
  private final Router router;
  private FoodListView foodListView;

  @Inject
  public FoodListPresenterImpl(FoodDao foodDao, Router router) {
    this.foodDao = foodDao;
    this.router = router;
  }

  public void takeView(FoodListView foodListView) {
    this.foodListView = foodListView;
  }

  public void releaseView() {
    this.foodListView = null;
  }

  @Override
  public void loadItems() {
    List<DbFoodItem> dbFoodItems = foodDao.loadFoodItems();
    onDbFoodItemsLoaded(dbFoodItems);
  }

  @Override
  public void onFoodItemClicked(FoodItemViewModel foodItemViewModel) {
    router.startFoodDetailsActivity(foodItemViewModel.getId());
  }

  @Override
  public void onSearchClicked() {
    router.startFoodSearchActivity();
  }


  private void onDbFoodItemsLoaded(List<DbFoodItem> foodItems) {
    if (foodListView != null) {
      List<FoodItemViewModel> foodItemViewModels = DbToViewModelConverter.fromDb(foodItems);
      foodListView.renderItems(foodItemViewModels);
    }
  }

  private void onDbFoodItemsLoadFailed(Throwable throwable) {
    throwable.printStackTrace();
  }


}
