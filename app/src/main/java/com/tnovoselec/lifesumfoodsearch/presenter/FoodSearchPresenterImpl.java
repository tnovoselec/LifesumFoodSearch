package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.Router;
import com.tnovoselec.lifesumfoodsearch.api.LifesumClient;
import com.tnovoselec.lifesumfoodsearch.api.model.ApiFoodResponse;
import com.tnovoselec.lifesumfoodsearch.business.ApiToDbConverter;
import com.tnovoselec.lifesumfoodsearch.db.dao.FoodDao;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.view.FoodSearchView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FoodSearchPresenterImpl extends BasePresenter implements FoodSearchPresenter {

  private LifesumClient lifesumClient;
  private FoodDao foodDao;
  private Router router;
  private FoodSearchView foodSearchView;

  @Inject
  public FoodSearchPresenterImpl(LifesumClient lifesumClient, FoodDao foodDao, Router router) {
    this.lifesumClient = lifesumClient;
    this.foodDao = foodDao;
    this.router = router;
  }

  @Override
  public void searchForFood(String query) {
    addSubscription(
        Observable.defer(() ->
            lifesumClient.getFoods(query, "en", "us"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onApiFoodItemsSearched,
                this::onDbFoodItemsSearchFailed));
  }

  @Override
  public void takeView(FoodSearchView foodSearchView) {
    this.foodSearchView = foodSearchView;
  }

  @Override
  public void releaseView() {
    this.foodSearchView = null;
  }

  @Override
  public void onFoodItemClicked(DbFoodItem dbFoodItem) {
    router.startFoodDetailsActivity(dbFoodItem);
  }

  private void persistSearchResults(List<DbFoodItem> foodItems) {
    foodDao.storeFoodItems(foodItems);
  }

  private void onApiFoodItemsSearched(ApiFoodResponse apiFoodResponse) {
    if (apiFoodResponse == null) {
      foodSearchView.renderItems(Collections.emptyList());
      return;
    }
    List<DbFoodItem> dbFoodItems = ApiToDbConverter.convertFromApi(apiFoodResponse.apiFoodItems);
    persistSearchResults(dbFoodItems);
    if (foodSearchView != null) {
      foodSearchView.renderItems(dbFoodItems);
    }
  }

  private void onDbFoodItemsSearchFailed(Throwable throwable) {
    throwable.printStackTrace();
  }
}
