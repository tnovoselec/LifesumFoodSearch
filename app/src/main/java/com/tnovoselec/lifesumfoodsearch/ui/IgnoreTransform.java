package com.tnovoselec.lifesumfoodsearch.ui;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

public class IgnoreTransform implements Transformation {
  @Override
  public Bitmap transform(Bitmap source) {
    return source;
  }

  @Override
  public String key() {
    return "ignore";
  }
}
