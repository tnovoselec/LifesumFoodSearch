package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.db.dao.FoodDao;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;

import javax.inject.Inject;

public class FoodDetailsPresenterImpl extends BasePresenter implements FoodDetailsPresenter {

  private final FoodDao foodDao;

  @Inject
  public FoodDetailsPresenterImpl(FoodDao foodDao){
    this.foodDao = foodDao;
  }

  @Override
  public DbFoodItem getDbFoodItem(long id) {
    return foodDao.getItemById(id);
  }

  @Override
  public void onFavoriteClicked(DbFoodItem dbFoodItem, boolean isFavorite) {
    foodDao.updateItemFavoriteStatus(dbFoodItem, isFavorite);
  }
}
