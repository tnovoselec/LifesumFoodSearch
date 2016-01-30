package com.tnovoselec.lifesumfoodsearch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import com.tnovoselec.lifesumfoodsearch.R;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.di.BaseActivity;
import com.tnovoselec.lifesumfoodsearch.di.component.ActivityComponent;
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

  @Inject
  FoodDetailsPresenter foodDetailsPresenter;

  private DbFoodItem dbFoodItem;

  private final int favoriteActiveDrawable = android.R.drawable.btn_star_big_on;
  private final int favoriteInactiveDrawable = android.R.drawable.btn_star_big_off;


  public static Intent createIntent(Context context, DbFoodItem dbFoodItem) {
    Intent intent = new Intent(context, FoodDetailsActivity.class);
    intent.putExtra(KEY_FOOD_ITEM_ID, dbFoodItem.getId());
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_food_details);
    ButterKnife.bind(this);

    long id = getIntent().getLongExtra(KEY_FOOD_ITEM_ID, -1);
    dbFoodItem = foodDetailsPresenter.getDbFoodItem(id);
    toolbar.setTitle(dbFoodItem.getTitle());

    setSupportActionBar(toolbar);

    floatingActionButton.setImageResource(dbFoodItem.isFavorite() ? favoriteActiveDrawable : favoriteInactiveDrawable);
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @OnClick(R.id.fab)
  public void onFavoriteButtonClicked() {
    foodDetailsPresenter.onFavoriteClicked(dbFoodItem, !dbFoodItem.isFavorite());
    floatingActionButton.setImageResource(dbFoodItem.isFavorite() ? favoriteActiveDrawable : favoriteInactiveDrawable);
  }
}
