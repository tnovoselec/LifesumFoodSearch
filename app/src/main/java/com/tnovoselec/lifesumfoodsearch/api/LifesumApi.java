package com.tnovoselec.lifesumfoodsearch.api;

import com.tnovoselec.lifesumfoodsearch.api.model.FoodResponse;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;

public interface LifesumApi {

  String API_ENDPOINT = "https://api.lifesum.com/"; //icebox/v1/foods/LANGUAGE/COUNTRY/SEARCH
  String AUTH_HEADER = "5055538:e52b2981d949fea96d3a103643f377e1ab85c08e347e310adf5ed927831e1018";

  @GET("/icebox/v1/foods/{language}/{country}/{name}")
  @Headers("Authorization: " + AUTH_HEADER)
  Observable<FoodResponse> getFood(@Path("name") String name, @Path("language") String language, @Path("country") String country);

}
