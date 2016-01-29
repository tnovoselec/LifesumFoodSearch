package com.tnovoselec.lifesumfoodsearch.di.module;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuInflater;

import com.tnovoselec.lifesumfoodsearch.di.BaseActivity;
import com.tnovoselec.lifesumfoodsearch.di.qualifier.ForActivity;
import com.tnovoselec.lifesumfoodsearch.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {

  private final BaseActivity activity;

  public ActivityModule(BaseActivity activity) {
    this.activity = activity;
  }

  @Provides
  @ActivityScope
  @ForActivity
  Context provideActivityContext() {
    return activity;
  }

  @Provides
  @ActivityScope
  FragmentActivity provideActivity() {
    return activity;
  }

  @Provides
  @ActivityScope
  FragmentManager provideSupportFragmentManager() {
    return activity.getSupportFragmentManager();
  }

  @Provides
  @ActivityScope
  android.app.FragmentManager provideFragmentManager() {
    return activity.getFragmentManager();
  }

  @Provides
  @ActivityScope
  MenuInflater provideMenuInflater() {
    return activity.getMenuInflater();
  }

  @Provides
  @ActivityScope
  ActionBar provideActionBar() {
    return activity.getSupportActionBar();
  }

  @Provides
  @ActivityScope
  LayoutInflater provideLayoutInflater(@ForActivity Context activityContext) {
    return (LayoutInflater) activityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

}
