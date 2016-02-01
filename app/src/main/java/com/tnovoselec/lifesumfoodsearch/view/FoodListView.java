package com.tnovoselec.lifesumfoodsearch.view;

import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;

import java.util.List;

public interface FoodListView {

  void renderItems(List<FoodItemViewModel> foodItemViewModels);

}
