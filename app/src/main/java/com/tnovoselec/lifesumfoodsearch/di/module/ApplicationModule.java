package com.tnovoselec.lifesumfoodsearch.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tnovoselec.lifesumfoodsearch.LifesumFoodSearchApplication;
import com.tnovoselec.lifesumfoodsearch.api.FlickrApi;
import com.tnovoselec.lifesumfoodsearch.api.FlickrClient;
import com.tnovoselec.lifesumfoodsearch.api.LifesumApi;
import com.tnovoselec.lifesumfoodsearch.api.LifesumClient;
import com.tnovoselec.lifesumfoodsearch.di.qualifier.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


@Module
public final class ApplicationModule {

  private final LifesumFoodSearchApplication lifesumFoodSearchApplication;

  public ApplicationModule(LifesumFoodSearchApplication lifesumFoodSearchApplication) {
    this.lifesumFoodSearchApplication = lifesumFoodSearchApplication;
  }

  @Provides
  @Singleton
  @ForApplication
  Context provideApplicationContext() {
    return lifesumFoodSearchApplication;
  }

  @Provides
  @Singleton
  Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    return gsonBuilder.create();
  }

  @Provides
  @Singleton
  protected SharedPreferences getSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(lifesumFoodSearchApplication);
  }

  @Provides
  @Singleton
  protected Resources getResources() {
    return lifesumFoodSearchApplication.getResources();
  }

  @Provides
  @Singleton
  protected LifesumApi getLifesumApi(){
    return new Retrofit.Builder()
        .baseUrl(LifesumApi.API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(LifesumApi.class);
  }

  @Provides
  @Singleton
  protected FlickrApi getFlickrApi(){
    return new Retrofit.Builder()
        .baseUrl(FlickrApi.API_ENDPOINT)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(FlickrApi.class);
  }

  @Provides
  @Singleton
  protected LifesumClient getLifesumClient(LifesumApi lifesumApi){
    return new LifesumClient(lifesumApi);
  }

  @Provides
  @Singleton
  protected FlickrClient getFlickrClient(FlickrApi flickrApi, Gson gson){
    return new FlickrClient(flickrApi, gson);
  }


  @Provides
  @Singleton
  protected Realm getRealm(){
    return Realm.getDefaultInstance();
  }

}
