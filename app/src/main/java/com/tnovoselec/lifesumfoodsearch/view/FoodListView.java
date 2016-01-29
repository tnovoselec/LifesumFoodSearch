package com.tnovoselec.lifesumfoodsearch.view;

import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;

import java.util.List;

public interface FoodListView {

  void renderItems(List<DbFoodItem> foodItems);

  void showProgress();

  void hideProgress();
}
