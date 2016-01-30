package com.tnovoselec.lifesumfoodsearch.di.component;

import com.tnovoselec.lifesumfoodsearch.activity.FoodDetailsActivity;
import com.tnovoselec.lifesumfoodsearch.activity.FoodListActivity;
import com.tnovoselec.lifesumfoodsearch.activity.FoodSearchActivity;

public interface ActivityComponentInjects {

  void inject(FoodListActivity foodListActivity);

  void inject(FoodSearchActivity foodSearchActivity);

  void inject(FoodDetailsActivity foodDetailsActivity);

}
