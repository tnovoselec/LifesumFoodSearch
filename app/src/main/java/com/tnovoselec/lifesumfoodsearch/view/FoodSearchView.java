package com.tnovoselec.lifesumfoodsearch.view;

import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;

import java.util.List;

public interface FoodSearchView {

  void renderItems(List<FoodItemViewModel> foodItemViewModels);

  void showProgress();

  void hideProgress();
}
