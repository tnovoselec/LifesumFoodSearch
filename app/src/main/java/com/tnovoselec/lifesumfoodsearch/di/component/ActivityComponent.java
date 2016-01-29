package com.tnovoselec.lifesumfoodsearch.di.component;

import android.content.Context;

import com.tnovoselec.lifesumfoodsearch.di.BaseActivity;
import com.tnovoselec.lifesumfoodsearch.di.module.ActivityModule;
import com.tnovoselec.lifesumfoodsearch.di.qualifier.ForActivity;
import com.tnovoselec.lifesumfoodsearch.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
    dependencies = {
        ApplicationComponent.class
    },
    modules = {
        ActivityModule.class
    }
)
public interface ActivityComponent extends ApplicationComponent, ActivityComponentInjects {

  final class Initializer {
    static public ActivityComponent init(BaseActivity injectorActivity,
                                         ComponentProvider<ApplicationComponent> applicationComponentProvider) {
      return DaggerActivityComponent.builder()
          .applicationComponent(applicationComponentProvider.getComponent())
          .activityModule(new ActivityModule(injectorActivity))
          .build();
    }

    // No instances
    private Initializer() {
    }
  }

  @ForActivity
  Context provideActivityContext();
}
