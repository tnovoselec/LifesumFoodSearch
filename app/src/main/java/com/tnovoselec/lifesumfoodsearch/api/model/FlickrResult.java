package com.tnovoselec.lifesumfoodsearch.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Random;

public class FlickrResult {

  @SerializedName("photos")
  private Photos photos;

  public Photos getPhotos() {
    return photos;
  }

  public static class Photos {

    @SerializedName("photo")
    List<Photo> photos;

    public List<Photo> getPhotos() {
      return photos;
    }

    public static class Photo {

      @SerializedName("id")
      private String id;

      @SerializedName("farm")
      private String farm;

      @SerializedName("server")
      private String server;

      @SerializedName("secret")
      private String secret;

      public String getId() {
        return id;
      }

      public String getFarm() {
        return farm;
      }

      public String getServer() {
        return server;
      }

      public String getSecret() {
        return secret;
      }
    }
  }

  public Photos.Photo getRandomPhoto(){
    Random random =  new Random();
    int position = random.nextInt(getPhotos().getPhotos().size());
    return getPhotos().getPhotos().get(position);
  }
}
