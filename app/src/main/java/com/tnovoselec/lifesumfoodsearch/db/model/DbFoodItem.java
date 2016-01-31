package com.tnovoselec.lifesumfoodsearch.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DbFoodItem extends RealmObject{

  @PrimaryKey
  private long id;

  private String categoryid;

  private String fat;

  private String title;

  private double potassium;

  private String category;

  private String calories;

  private String imageUrl;

  private boolean favorite;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCalories() {
    return calories;
  }

  public void setCalories(String calories) {
    this.calories = calories;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCategoryid() {
    return categoryid;
  }

  public void setCategoryid(String categoryid) {
    this.categoryid = categoryid;
  }

  public String getFat() {
    return fat;
  }

  public void setFat(String fat) {
    this.fat = fat;
  }

  public double getPotassium() {
    return potassium;
  }

  public void setPotassium(double potassium) {
    this.potassium = potassium;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isFavorite() {
    return favorite;
  }

  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
