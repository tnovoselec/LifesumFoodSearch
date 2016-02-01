package com.tnovoselec.lifesumfoodsearch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tnovoselec.lifesumfoodsearch.R;
import com.tnovoselec.lifesumfoodsearch.di.BaseActivity;
import com.tnovoselec.lifesumfoodsearch.di.component.ActivityComponent;
import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;
import com.tnovoselec.lifesumfoodsearch.presenter.FoodDetailsPresenter;
import com.tnovoselec.lifesumfoodsearch.view.FoodDetailsView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodDetailsActivity extends BaseActivity implements FoodDetailsView {

  private static final String KEY_FOOD_ITEM_ID = "key_food_item_ID";

  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Bind(R.id.fab)
  FloatingActionButton floatingActionButton;

  @Bind(R.id.food_details_category_value)
  TextView foodDetailsCategory;
  @Bind(R.id.food_details_fat_value)
  TextView foodDetailsFat;
  @Bind(R.id.food_details_potassium_value)
  TextView foodDetailsPotassium;
  @Bind(R.id.food_details_calories_value)
  TextView foodDetailsCalories;
  @Bind(R.id.food_details_image)
  ImageView foodDetailsImage;

  @Inject
  FoodDetailsPresenter foodDetailsPresenter;

  private FoodItemViewModel foodItemViewModel;

  private final int favoriteActiveDrawable = android.R.drawable.btn_star_big_on;
  private final int favoriteInactiveDrawable = android.R.drawable.btn_star_big_off;


  public static Intent createIntent(Context context, long itemId) {
    Intent intent = new Intent(context, FoodDetailsActivity.class);
    intent.putExtra(KEY_FOOD_ITEM_ID, itemId);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_food_details);
    ButterKnife.bind(this);

    long id = getIntent().getLongExtra(KEY_FOOD_ITEM_ID, -1);
    foodItemViewModel = foodDetailsPresenter.getDbFoodItem(id);
    toolbar.setTitle(foodItemViewModel.getTitle());

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    floatingActionButton.setImageResource(foodItemViewModel.isFavorite() ? favoriteActiveDrawable : favoriteInactiveDrawable);

    foodDetailsCategory.setText(foodItemViewModel.getCategory());
    foodDetailsCalories.setText(foodItemViewModel.getCalories());
    foodDetailsPotassium.setText(String.format("%s", foodItemViewModel.getPotassium()));
    foodDetailsFat.setText(foodItemViewModel.getFat());
    loadImage(foodItemViewModel.getImageUrl());
  }

  private void loadImage(String imageUrl) {
    if (TextUtils.isEmpty(imageUrl)) {
      Picasso.with(this)
          .load(R.mipmap.ic_launcher)
          .into(foodDetailsImage);

    } else {
      Picasso.with(this)
          .load(imageUrl)
          .into(foodDetailsImage);
    }

  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @OnClick(R.id.fab)
  public void onFavoriteButtonClicked() {
    foodDetailsPresenter.onFavoriteClicked(foodItemViewModel, !foodItemViewModel.isFavorite());
    foodItemViewModel = foodDetailsPresenter.getDbFoodItem(foodItemViewModel.getId());
    floatingActionButton.setImageResource(foodItemViewModel.isFavorite() ? favoriteActiveDrawable : favoriteInactiveDrawable);
  }
}
