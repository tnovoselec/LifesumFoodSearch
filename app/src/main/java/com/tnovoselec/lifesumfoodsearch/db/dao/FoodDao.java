package com.tnovoselec.lifesumfoodsearch.db.dao;

import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;

public class FoodDao {

  private Realm realm;

  @Inject
  public FoodDao(Realm realm) {
    this.realm = realm;
  }

  public void storeFoodItems(List<DbFoodItem> foodItems) {
    realm.beginTransaction();
    realm.copyToRealm(foodItems);
    realm.commitTransaction();
  }

  public Observable<? extends List<DbFoodItem>> loadFoodItems() {
    return realm.where(DbFoodItem.class).findAllAsync().asObservable();
  }
}
