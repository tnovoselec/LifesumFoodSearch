package com.tnovoselec.lifesumfoodsearch.ui;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class OffsetItemDecoration extends RecyclerView.ItemDecoration {


  private final int itemOffsetId;

  public OffsetItemDecoration(int itemOffsetId) {
    this.itemOffsetId = itemOffsetId;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    int itemOffset = view.getContext().getResources().getDimensionPixelOffset(itemOffsetId);
    outRect.set(itemOffset, itemOffset, itemOffset, itemOffset);
  }
}
