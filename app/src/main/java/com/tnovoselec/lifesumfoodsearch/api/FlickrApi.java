package com.tnovoselec.lifesumfoodsearch.api;

import com.squareup.okhttp.ResponseBody;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface FlickrApi {

  String API_ENDPOINT = "https://api.flickr.com";

  String API_KEY = "5d887b46d03932e737f716c762499fb6";
  String API_SECRET = "8dc562ad42e18547";
  String BASE_URL = "/services/rest/?method=flickr.photos.search&per_page=10&api_key=" + API_KEY + "&format=json";

  @GET(BASE_URL)
  Observable<ResponseBody> searchByTag(@Query("tags") String tag);

}
