package com.tnovoselec.lifesumfoodsearch.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiFoodResponse {

  @SerializedName("food")
  public List<ApiFoodItem> apiFoodItems;
}
