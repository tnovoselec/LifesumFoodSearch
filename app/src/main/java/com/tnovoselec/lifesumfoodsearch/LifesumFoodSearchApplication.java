package com.tnovoselec.lifesumfoodsearch;

import android.app.Application;

import com.tnovoselec.lifesumfoodsearch.di.component.ApplicationComponent;
import com.tnovoselec.lifesumfoodsearch.di.component.ComponentFactory;
import com.tnovoselec.lifesumfoodsearch.di.component.ComponentProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LifesumFoodSearchApplication extends Application implements ComponentProvider<ApplicationComponent> {

  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    inject();
    initRealm();
  }

  private void initRealm(){
    RealmConfiguration config = new RealmConfiguration.Builder(this).build();
    Realm.setDefaultConfiguration(config);
  }

  protected void inject() {
    applicationComponent = ComponentFactory.createApplicationComponent(this);
    applicationComponent.inject(this);
  }

  @Override
  public ApplicationComponent getComponent() {
    return applicationComponent;
  }
}
