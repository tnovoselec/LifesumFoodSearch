package com.tnovoselec.lifesumfoodsearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tnovoselec.lifesumfoodsearch.R;
import com.tnovoselec.lifesumfoodsearch.model.FoodItemViewModel;
import com.tnovoselec.lifesumfoodsearch.ui.CircleTransform;
import com.tnovoselec.lifesumfoodsearch.ui.IgnoreTransform;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodItemsAdapter extends RecyclerView.Adapter<FoodItemsAdapter.FoodItemViewHolder> {

  public enum Mode{
    LIST, GRID
  }

  public interface OnFoodItemClickedListener{
    void onFoodItemClicked(FoodItemViewModel dbFoodItem);
  }

  private final List<FoodItemViewModel> foodItems;
  private final OnFoodItemClickedListener onFoodItemClickedListener;
  private final Mode mode;

  public FoodItemsAdapter(List<FoodItemViewModel> foodItems, OnFoodItemClickedListener onFoodItemClickedListener, Mode mode) {
    this.foodItems = foodItems;
    this.onFoodItemClickedListener = onFoodItemClickedListener;
    this.mode = mode;
  }

  @Override
  public FoodItemsAdapter.FoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    int layoutId = mode == Mode.LIST ? R.layout.item_food_list : R.layout.item_food_grid;
    View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
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

    void fillView(FoodItemViewModel foodItem) {
      loadImage(foodItem.getImageUrl());
      itemFoodTitle.setText(foodItem.getTitle());
      itemFoodCategory.setText(foodItem.getCategory());
      this.itemView.setOnClickListener(v -> onFoodItemClickedListener.onFoodItemClicked(foodItem));
    }

    private void loadImage(String imageUrl) {
      Transformation transformation = mode == Mode.LIST ? new CircleTransform() : new IgnoreTransform();
      if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(itemView.getContext())
            .load(R.mipmap.ic_launcher)
            .transform(transformation)
            .into(itemFoodImage);
      } else {
        Picasso.with(itemView.getContext())
            .load(imageUrl)
            .transform(transformation)
            .into(itemFoodImage);
      }
    }
  }
}
