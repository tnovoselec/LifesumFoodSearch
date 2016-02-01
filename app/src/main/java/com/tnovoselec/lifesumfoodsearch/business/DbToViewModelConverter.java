package com.tnovoselec.lifesumfoodsearch.business;

import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class DbToViewModelConverter {

  public static List<FoodItemViewModel> fromDb(List<DbFoodItem> dbFoodItems) {
    List<FoodItemViewModel> foodItemViewModels = new ArrayList<>();
    for (DbFoodItem dbFoodItem : dbFoodItems) {
      foodItemViewModels.add(fromDb(dbFoodItem));
    }
    return foodItemViewModels;
  }

  public static FoodItemViewModel fromDb(DbFoodItem dbFoodItem) {
    FoodItemViewModel foodItemViewModel = new FoodItemViewModel();
    foodItemViewModel.setId(dbFoodItem.getId());
    foodItemViewModel.setTitle(dbFoodItem.getTitle());
    foodItemViewModel.setImageUrl(dbFoodItem.getImageUrl());
    foodItemViewModel.setFavorite(dbFoodItem.isFavorite());
    foodItemViewModel.setCalories(dbFoodItem.getCalories());
    foodItemViewModel.setPotassium(dbFoodItem.getPotassium());
    foodItemViewModel.setFat(dbFoodItem.getFat());
    foodItemViewModel.setCategory(dbFoodItem.getCategory());
    foodItemViewModel.setCategoryid(dbFoodItem.getCategoryid());
    return foodItemViewModel;
  }
}
