package com.tnovoselec.lifesumfoodsearch.db.dao;

import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class FoodDao {

  private Realm realm;

  @Inject
  public FoodDao(Realm realm) {
    this.realm = realm;
  }

  public synchronized void storeFoodItems(List<DbFoodItem> foodItems) {
    realm.beginTransaction();
    for (DbFoodItem dbFoodItem : foodItems) {
      if (getItemById(dbFoodItem.getId()) == null) {
        realm.copyToRealmOrUpdate(dbFoodItem);
      }
    }

    realm.commitTransaction();
  }

  public synchronized List<DbFoodItem> loadFoodItems() {
    return realm.where(DbFoodItem.class).equalTo("favorite", true).findAll();
  }

  public synchronized void updateItemFavoriteStatus(DbFoodItem dbFoodItem, boolean isFavorite) {
    realm.beginTransaction();
    dbFoodItem.setFavorite(isFavorite);
    realm.copyToRealmOrUpdate(dbFoodItem);
    realm.commitTransaction();
  }

  public synchronized DbFoodItem getItemById(long id) {
    return realm.where(DbFoodItem.class).equalTo("id", id).findFirst();
  }
}
