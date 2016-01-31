package com.tnovoselec.lifesumfoodsearch.business;

import android.util.Pair;

import com.tnovoselec.lifesumfoodsearch.api.model.ApiFoodItem;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;

import java.util.ArrayList;
import java.util.List;

public class ApiToDbConverter {

  public static List<DbFoodItem> convertFromApiWithImages(List<Pair<ApiFoodItem, String>> apiFoodItemsWithImage){
    List<DbFoodItem> dbFoodItems = new ArrayList<>();
    for (Pair<ApiFoodItem, String> apiFoodItemWithImage : apiFoodItemsWithImage){
      ApiFoodItem apiFoodItem = apiFoodItemWithImage.first;
      String imageUrl = apiFoodItemWithImage.second;
      DbFoodItem dbFoodItem = convertFromApi(apiFoodItem);
      dbFoodItem.setImageUrl(imageUrl);
      dbFoodItems.add(dbFoodItem);
    }
    return dbFoodItems;
  }

  public static List<DbFoodItem> convertFromApi(List<ApiFoodItem> apiFoodItems){
    List<DbFoodItem> dbFoodItems = new ArrayList<>();
    for (ApiFoodItem apiFoodItem : apiFoodItems){
      dbFoodItems.add(convertFromApi(apiFoodItem));
    }
    return dbFoodItems;
  }

  public static DbFoodItem convertFromApi(ApiFoodItem apiFoodItem){
    DbFoodItem dbFoodItem = new DbFoodItem();
    dbFoodItem.setId(apiFoodItem.id);
    dbFoodItem.setCalories(apiFoodItem.calories);
    dbFoodItem.setCategory(apiFoodItem.category);
    dbFoodItem.setCategoryid(apiFoodItem.categoryid);
    dbFoodItem.setFat(apiFoodItem.fat);
    dbFoodItem.setPotassium(apiFoodItem.potassium);
    dbFoodItem.setTitle(apiFoodItem.title);
    return dbFoodItem;
  }
}
