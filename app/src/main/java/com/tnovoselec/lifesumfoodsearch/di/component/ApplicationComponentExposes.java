package com.tnovoselec.lifesumfoodsearch.di.component;

import android.content.Context;
import android.content.res.Resources;

import com.tnovoselec.lifesumfoodsearch.api.FlickrApi;
import com.tnovoselec.lifesumfoodsearch.api.FlickrClient;
import com.tnovoselec.lifesumfoodsearch.api.LifesumApi;
import com.tnovoselec.lifesumfoodsearch.api.LifesumClient;
import com.tnovoselec.lifesumfoodsearch.di.qualifier.ForApplication;

import io.realm.Realm;

public interface ApplicationComponentExposes {

  @ForApplication
  Context provideApplicationContext();

  Resources provideResources();

  LifesumApi provideLifesumApi();

  FlickrApi provideFlickrApi();

  LifesumClient provideLifesumClient();

  FlickrClient provideFlickrClient();

  Realm provideRealm();

}
