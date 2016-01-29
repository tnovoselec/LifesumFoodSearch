package com.tnovoselec.lifesumfoodsearch.di.component;

import android.content.Context;
import android.content.res.Resources;

import com.tnovoselec.lifesumfoodsearch.api.LifesumApi;
import com.tnovoselec.lifesumfoodsearch.api.LifesumClient;
import com.tnovoselec.lifesumfoodsearch.di.qualifier.ForApplication;

import io.realm.Realm;

public interface ApplicationComponentExposes {

  @ForApplication
  Context provideApplicationContext();

  Resources provideResources();

  LifesumApi provideLifesumApi();

  LifesumClient provideLifesumClient();

  Realm provideRealm();
}
