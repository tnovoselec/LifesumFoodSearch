package com.tnovoselec.lifesumfoodsearch.api;

import com.tnovoselec.lifesumfoodsearch.api.model.FoodResponse;

import rx.Observable;

public class LifesumClient {

  private final LifesumApi lifesumApi;

  public LifesumClient(LifesumApi lifesumApi) {
    this.lifesumApi = lifesumApi;
  }

  public Observable<FoodResponse> getFoods(String name, String language, String country) {
    return lifesumApi.getFood(name, language, country);
  }
}
