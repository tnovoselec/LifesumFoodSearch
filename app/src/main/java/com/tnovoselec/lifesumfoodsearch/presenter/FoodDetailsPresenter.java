package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;

public interface FoodDetailsPresenter {

  DbFoodItem getDbFoodItem(long id);

  void onFavoriteClicked(DbFoodItem dbFoodItem, boolean makeFavorite);
}
