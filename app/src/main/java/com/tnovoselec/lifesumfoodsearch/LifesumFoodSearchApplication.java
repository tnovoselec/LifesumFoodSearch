package com.tnovoselec.lifesumfoodsearch;

import android.app.Application;

import com.tnovoselec.lifesumfoodsearch.di.component.ApplicationComponent;
import com.tnovoselec.lifesumfoodsearch.di.component.ComponentFactory;
import com.tnovoselec.lifesumfoodsearch.di.component.ComponentProvider;

public class LifesumFoodSearchApplication extends Application implements ComponentProvider<ApplicationComponent> {

  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    inject();
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
