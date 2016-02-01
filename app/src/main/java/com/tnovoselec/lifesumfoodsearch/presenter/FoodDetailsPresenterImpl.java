package com.tnovoselec.lifesumfoodsearch.presenter;

import com.tnovoselec.lifesumfoodsearch.business.DbToViewModelConverter;
import com.tnovoselec.lifesumfoodsearch.db.dao.FoodDao;
import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;

import javax.inject.Inject;

public class FoodDetailsPresenterImpl extends BasePresenter implements FoodDetailsPresenter {

  private final FoodDao foodDao;

  @Inject
  public FoodDetailsPresenterImpl(FoodDao foodDao){
    this.foodDao = foodDao;
  }

  @Override
  public FoodItemViewModel getDbFoodItem(long id) {
    return DbToViewModelConverter.fromDb(foodDao.getItemById(id));
  }

  @Override
  public void onFavoriteClicked(FoodItemViewModel foodItemViewModel, boolean isFavorite) {
    foodDao.updateItemFavoriteStatus(foodItemViewModel.getId(), isFavorite);
  }
}
