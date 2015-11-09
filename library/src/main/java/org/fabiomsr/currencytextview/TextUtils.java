package org.fabiomsr.currencytextview;

import android.graphics.Rect;
import android.text.TextPaint;

public class TextUtils {

  public static void getTextBounds(TextPaint paint, String text, Rect bounds, float textSize){
    paint.setTextSize(textSize);
    paint.getTextBounds(text, 0, text.length(), bounds);
  }

}
