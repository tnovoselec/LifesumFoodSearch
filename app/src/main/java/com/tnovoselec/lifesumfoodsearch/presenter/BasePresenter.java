package com.tnovoselec.lifesumfoodsearch.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter {

  private final CompositeSubscription compositeSubscription = new CompositeSubscription();

  public void addSubscribtion(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  public void activate(){

  }

  public void deactivate(){
    compositeSubscription.unsubscribe();
  }
}
