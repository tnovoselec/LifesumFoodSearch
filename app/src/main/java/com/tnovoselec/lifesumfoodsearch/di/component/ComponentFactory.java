package com.tnovoselec.lifesumfoodsearch.di.component;

import com.tnovoselec.lifesumfoodsearch.LifesumFoodSearchApplication;
import com.tnovoselec.lifesumfoodsearch.di.BaseActivity;

public final class ComponentFactory {

  public static ApplicationComponent createApplicationComponent(LifesumFoodSearchApplication application) {
    return ApplicationComponent.Initializer.init(application);
  }

  public static ActivityComponent createActivityComponent(BaseActivity injectorActivity,
                                                          ComponentProvider<ApplicationComponent> applicationComponentProvider) {
    return ActivityComponent.Initializer.init(injectorActivity, applicationComponentProvider);
  }
}
