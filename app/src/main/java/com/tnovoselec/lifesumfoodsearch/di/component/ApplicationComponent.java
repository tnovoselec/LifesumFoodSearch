package com.tnovoselec.lifesumfoodsearch.di.component;

import com.tnovoselec.lifesumfoodsearch.LifesumFoodSearchApplication;
import com.tnovoselec.lifesumfoodsearch.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
        ApplicationModule.class,
    }
)
public interface ApplicationComponent extends ApplicationComponentInjects, ApplicationComponentExposes {

  /**
   * An initializer that creates the graph from an application.
   */
  final class Initializer {
    static public ApplicationComponent init(LifesumFoodSearchApplication application) {
      return DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(application))
          .build();
    }

    // No instances
    private Initializer() {
    }
  }
}
