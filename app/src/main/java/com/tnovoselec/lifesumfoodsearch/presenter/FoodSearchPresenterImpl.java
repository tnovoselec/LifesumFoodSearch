package com.tnovoselec.lifesumfoodsearch.presenter;

import android.util.Pair;

import com.tnovoselec.lifesumfoodsearch.Router;
import com.tnovoselec.lifesumfoodsearch.api.FlickrClient;
import com.tnovoselec.lifesumfoodsearch.api.LifesumClient;
import com.tnovoselec.lifesumfoodsearch.api.model.ApiFoodItem;
import com.tnovoselec.lifesumfoodsearch.business.ApiToDbConverter;
import com.tnovoselec.lifesumfoodsearch.db.dao.FoodDao;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.view.FoodSearchView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FoodSearchPresenterImpl extends BasePresenter implements FoodSearchPresenter {

  private LifesumClient lifesumClient;
  private FlickrClient flickrClient;
  private FoodDao foodDao;
  private Router router;
  private FoodSearchView foodSearchView;

  private Subscription currentSearch;

  @Inject
  public FoodSearchPresenterImpl(LifesumClient lifesumClient, FlickrClient flickrClient, FoodDao foodDao, Router router) {
    this.lifesumClient = lifesumClient;
    this.flickrClient = flickrClient;
    this.foodDao = foodDao;
    this.router = router;
  }

  @Override
  public void searchForFood(String query) {

    if (currentSearch != null && !currentSearch.isUnsubscribed()){
      currentSearch.unsubscribe();
    }

    currentSearch = Observable.defer(() ->
        lifesumClient.getFoods(query, "en", "us"))
        .flatMap(apiFoodResponse -> Observable.from(apiFoodResponse.apiFoodItems))
        .flatMap(apiFoodItem -> flickrClient.pullImage(apiFoodItem.title)
            .map(imageUrl -> Pair.create(apiFoodItem, imageUrl)))
        .toList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onApiFoodItemsSearched,
            this::onDbFoodItemsSearchFailed);
    addSubscription(currentSearch);
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

  private void onApiFoodItemsSearched(List<Pair<ApiFoodItem, String>> apiFoodItems) {
    if (apiFoodItems == null || apiFoodItems.size() == 0) {
      foodSearchView.renderItems(Collections.emptyList());
      return;
    }
    List<DbFoodItem> dbFoodItems = ApiToDbConverter.convertFromApiWithImages(apiFoodItems);
    persistSearchResults(dbFoodItems);
    if (foodSearchView != null) {
      foodSearchView.renderItems(dbFoodItems);
    }
  }

  private void onDbFoodItemsSearchFailed(Throwable throwable) {
    throwable.printStackTrace();
  }
}
