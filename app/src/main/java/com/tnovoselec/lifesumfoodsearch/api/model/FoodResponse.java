package com.tnovoselec.lifesumfoodsearch.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {

  @SerializedName("food")
  public List<FoodItem> foodItems;
}
