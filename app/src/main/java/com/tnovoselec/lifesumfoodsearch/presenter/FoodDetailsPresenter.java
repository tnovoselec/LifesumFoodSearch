package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;

public interface FoodDetailsPresenter {

  FoodItemViewModel getDbFoodItem(long id);

  void onFavoriteClicked(FoodItemViewModel foodItemViewModel, boolean makeFavorite);
}
