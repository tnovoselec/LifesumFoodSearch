package com.tnovoselec.lifesumfoodsearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tnovoselec.lifesumfoodsearch.R;
import com.tnovoselec.lifesumfoodsearch.db.model.DbFoodItem;
import com.tnovoselec.lifesumfoodsearch.ui.CircleTransform;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodItemsAdapter extends RecyclerView.Adapter<FoodItemsAdapter.FoodItemViewHolder> {

  public interface OnFoodItemClickedListener{
    void onFoodItemClicked(DbFoodItem dbFoodItem);
  }

  private final List<DbFoodItem> foodItems;
  private final OnFoodItemClickedListener onFoodItemClickedListener;

  public FoodItemsAdapter(List<DbFoodItem> foodItems, OnFoodItemClickedListener onFoodItemClickedListener) {
    this.foodItems = foodItems;
    this.onFoodItemClickedListener = onFoodItemClickedListener;
  }

  @Override
  public FoodItemsAdapter.FoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
    return new FoodItemViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(FoodItemsAdapter.FoodItemViewHolder holder, int position) {
    holder.fillView(foodItems.get(position));
  }

  @Override
  public int getItemCount() {
    return foodItems.size();
  }

  class FoodItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_food_title)
    TextView itemFoodTitle;

    @Bind(R.id.item_food_category)
    TextView itemFoodCategory;

    @Bind(R.id.item_food_image)
    ImageView itemFoodImage;

    public FoodItemViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void fillView(DbFoodItem foodItem) {
      loadImage(foodItem.getImageUrl());
      itemFoodTitle.setText(foodItem.getTitle());
      itemFoodCategory.setText(foodItem.getCategory());
      this.itemView.setOnClickListener(v -> onFoodItemClickedListener.onFoodItemClicked(foodItem));
    }

    private void loadImage(String imageUrl) {
      if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(itemView.getContext())
            .load(R.mipmap.ic_launcher)
            .transform(new CircleTransform())
            .into(itemFoodImage);
      } else {
        Picasso.with(itemView.getContext())
            .load(imageUrl)
            .transform(new CircleTransform())
            .into(itemFoodImage);
      }

    }
  }
}
