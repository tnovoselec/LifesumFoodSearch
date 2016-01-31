package com.tnovoselec.lifesumfoodsearch.util;


import com.tnovoselec.lifesumfoodsearch.api.model.FlickrResult;

public class FlickrUtils {

  private static final String URL_TEMPLATE = "https://farm%s.staticflickr.com/%s/%s_%s.jpg";

  public static String buildUrl(FlickrResult.Photos.Photo photo) {
    return String.format(URL_TEMPLATE, photo.getFarm(), photo.getServer(), photo.getId(), photo.getSecret());
  }
}
