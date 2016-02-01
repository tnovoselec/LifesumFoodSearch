package com.tnovoselec.lifesumfoodsearch;

import android.content.Context;

import com.tnovoselec.lifesumfoodsearch.activity.FoodDetailsActivity;
import com.tnovoselec.lifesumfoodsearch.activity.FoodSearchActivity;

import javax.inject.Inject;

public class Router {

  private final Context context;

  @Inject
  public Router(Context context) {
    this.context = context;
  }

  public void startFoodDetailsActivity(long itemId) {
    context.startActivity(FoodDetailsActivity.createIntent(context, itemId));
  }

  public void startFoodSearchActivity() {
    context.startActivity(FoodSearchActivity.createIntent(context));
  }
}
