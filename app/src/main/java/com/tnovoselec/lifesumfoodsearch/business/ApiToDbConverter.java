package com.tnovoselec.lifesumfoodsearch.business;

import com.tnovoselec.lifesumfoodsearch.api.model.ApiFoodItem;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;

public class ApiToDbConverter {

  public static DbFoodItem convertFromApi(ApiFoodItem apiFoodItem){
    DbFoodItem dbFoodItem = new DbFoodItem();
    dbFoodItem.setCalories(apiFoodItem.calories);
    dbFoodItem.setCategory(apiFoodItem.category);
    dbFoodItem.setCategoryid(apiFoodItem.categoryid);
    dbFoodItem.setFat(apiFoodItem.fat);
    dbFoodItem.setPotassium(apiFoodItem.potassium);
    dbFoodItem.setTitle(apiFoodItem.title);
    return dbFoodItem;
  }
}
