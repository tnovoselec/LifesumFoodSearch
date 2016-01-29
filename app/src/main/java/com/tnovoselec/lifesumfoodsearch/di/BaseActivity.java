package com.tnovoselec.lifesumfoodsearch.di;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.tnovoselec.lifesumfoodsearch.LifesumFoodSearchApplication;
import com.tnovoselec.lifesumfoodsearch.di.component.ActivityComponent;
import com.tnovoselec.lifesumfoodsearch.di.component.ComponentFactory;
import com.tnovoselec.lifesumfoodsearch.di.component.ComponentProvider;


public abstract class BaseActivity extends ActionBarActivity implements ComponentProvider<ActivityComponent> {

  /**
   * Can be added to an intent that starts an activity to signal that the activity was started from a notification
   */
  private ActivityComponent activityComponent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injectMe();
  }

  @Override
  public ActivityComponent getComponent() {
        /*
            This can happen when system kills activity. In that case all components depending on
            this one would crash. You can simulate this by checking
            Settings->Developer options->Don't keep activities
         */
    if (activityComponent == null) {
      activityComponent = ComponentFactory.createActivityComponent(this, (LifesumFoodSearchApplication) getApplicationContext());
    }
    return activityComponent;
  }

  protected abstract void inject(ActivityComponent activityComponent);

  private void injectMe() {
    activityComponent = getComponent();
    inject(activityComponent);
  }
}
