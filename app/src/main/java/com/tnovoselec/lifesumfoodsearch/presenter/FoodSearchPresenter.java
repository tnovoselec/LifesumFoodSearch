package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.view.FoodSearchView;

public interface FoodSearchPresenter extends ScopedPresenter {

  void searchForFood(String query);

  void takeView(FoodSearchView foodSearchView);

  void releaseView();

  void onFoodItemClicked(DbFoodItem dbFoodItem);
}
