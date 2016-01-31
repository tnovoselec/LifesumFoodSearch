package com.tnovoselec.lifesumfoodsearch.api;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;
import com.tnovoselec.lifesumfoodsearch.api.model.FlickrResult;
import com.tnovoselec.lifesumfoodsearch.util.FlickrUtils;
import com.tnovoselec.lifesumfoodsearch.util.IOUtils;

import javax.inject.Inject;

import rx.Observable;

public class FlickrClient {


  private final FlickrApi flickrApi;
  private final Gson gson;

  @Inject
  public FlickrClient(FlickrApi flickrApi, Gson gson) {
    this.flickrApi = flickrApi;
    this.gson = gson;
  }


  public Observable<String> pullImage(String query) {
    return flickrApi.searchByTag(query)
        .map(this::convertResponseToUrl);
  }


  private String convertResponseToUrl(ResponseBody responseBody) {
    try {
      String responseAsString = IOUtils.convertStreamToString(responseBody.byteStream());

      responseAsString = responseAsString.substring("jsonFlickrApi(".length(), responseAsString.length() - 2);

      FlickrResult flickrResult = gson.fromJson(responseAsString, FlickrResult.class);
      String url = FlickrUtils.buildUrl(flickrResult.getRandomPhoto());
      return url;
    } catch (Exception e) {
      return null;
    }
  }


}
