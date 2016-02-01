package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;
import com.tnovoselec.lifesumfoodsearch.view.FoodSearchView;

public interface FoodSearchPresenter extends ScopedPresenter {

  void searchForFood(String query);

  void takeView(FoodSearchView foodSearchView);

  void releaseView();

  void onFoodItemClicked(FoodItemViewModel dbFoodItem);
}
