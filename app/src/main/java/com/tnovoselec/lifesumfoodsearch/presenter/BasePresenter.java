package com.tnovoselec.lifesumfoodsearch.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements ScopedPresenter {

  private CompositeSubscription subscriptions;

  @Override
  public void activate() {
  }

  @Override
  public void deactivate() {
    unSubscribe();
  }

  protected void addSubscription(final Subscription subscription) {
    if (subscriptions == null) {
      subscriptions = new CompositeSubscription();
    }

    subscriptions.add(subscription);
  }

  private void unSubscribe() {
    if (subscriptions != null && !subscriptions.isUnsubscribed()) {
      subscriptions.unsubscribe();
    }

    subscriptions = null;
  }
}
