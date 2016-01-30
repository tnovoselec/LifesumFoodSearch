package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.view.FoodListView;

public interface FoodListPresenter extends ScopedPresenter{

  void takeView(FoodListView foodListView);

  void releaseView();

  void loadItems();

  void onFoodItemClicked(DbFoodItem dbFoodItem);

  void onSearchClicked();

}
