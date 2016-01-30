package com.tnovoselec.lifesumfoodsearch;

import android.content.Context;

import com.tnovoselec.lifesumfoodsearch.activity.FoodDetailsActivity;
import com.tnovoselec.lifesumfoodsearch.activity.FoodSearchActivity;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;

import javax.inject.Inject;

public class Router {

  private final Context context;

  @Inject
  public Router(Context context) {
    this.context = context;
  }

  public void startFoodDetailsActivity(DbFoodItem dbFoodItem) {
    context.startActivity(FoodDetailsActivity.createIntent(context, dbFoodItem));
  }

  public void startFoodSearchActivity() {
    context.startActivity(FoodSearchActivity.createIntent(context));
  }
}
