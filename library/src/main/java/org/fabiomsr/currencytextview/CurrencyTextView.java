package org.fabiomsr.currencytextview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CurrencyTextView extends View {

  private static final int GRAVITY_START = 1;
  private static final int GRAVITY_END = 2;
  private static final int GRAVITY_TOP = 4;
  private static final int GRAVITY_BOTTOM = 8;

  private TextPaint mTextPaint;
  private DecimalFormat mDecimalFormat;
  private Section mSymbolSection;
  private Section mIntegerSection;
  private Section mDecimalSection;
  private char mDecimalSeparator;
  private float  mAmount;
  private int mSymbolGravity;
  private int mDecimalGravity;
  private float mSymbolMargin;
  private float mDecimalMargin;
  private boolean mIncludeDecimalSeparator;

  private int mWidth;
  private int mHeight;

  public CurrencyTextView(Context context) {
    super(context);
    init(context, null);
  }

  public CurrencyTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public CurrencyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public CurrencyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }


  private void init(Context context, AttributeSet attrs) {

    mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    mSymbolSection = new Section();
    mIntegerSection = new Section();
    mDecimalSection = new Section();


    TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CurrencyTextView,
        0, R.style.CurrencyTextViewDefaultStyle);

    try {
      mSymbolSection.text = typedArray.getString(R.styleable.CurrencyTextView_symbol);
      mAmount       = typedArray.getFloat(R.styleable.CurrencyTextView_amount, 0);
      mSymbolGravity  = typedArray.getInt(R.styleable.CurrencyTextView_symbolGravity, GRAVITY_TOP | GRAVITY_START);
      mDecimalGravity = typedArray.getInt(R.styleable.CurrencyTextView_decimalGravity, GRAVITY_TOP);
      mIncludeDecimalSeparator = typedArray.getBoolean(R.styleable.CurrencyTextView_includeDecimalSeparator, true);
      mSymbolMargin = typedArray.getDimensionPixelSize(R.styleable.CurrencyTextView_symbolMargin, 0);
      mDecimalMargin = typedArray.getDimensionPixelSize(R.styleable.CurrencyTextView_decimalMargin, 0);
      mIntegerSection.textSize = typedArray.getDimension(R.styleable.CurrencyTextView_baseTextSize, 12f);
      mSymbolSection.textSize  = typedArray.getDimension(R.styleable.CurrencyTextView_symbolTextSize, mIntegerSection.textSize);
      mDecimalSection.textSize = typedArray.getDimension(R.styleable.CurrencyTextView_decimalDigitsTextSize, mIntegerSection.textSize);
      mIntegerSection.color = typedArray.getInt(R.styleable.CurrencyTextView_baseTextColor, 0);
      mSymbolSection.color  = typedArray.getInt(R.styleable.CurrencyTextView_symbolTextColor, mIntegerSection.color);
      mDecimalSection.color = typedArray.getInt(R.styleable.CurrencyTextView_decimalTextColor, mIntegerSection.color);
      mDecimalSection.drawUnderline = typedArray.getBoolean(R.styleable.CurrencyTextView_decimalUnderline, false);

      String format = typedArray.getString(R.styleable.CurrencyTextView_format);
      String decimalSeparator = typedArray.getString(R.styleable.CurrencyTextView_decimalSeparator);
      String fontPath = typedArray.getString(R.styleable.CurrencyTextView_fontPath);
      if(fontPath != null) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
        mTextPaint.setTypeface(typeface);
      }

      if(format == null){
        format = context.getString(R.string.default_format);
      }

      mDecimalFormat = new DecimalFormat(format);
      DecimalFormatSymbols decimalFormatSymbol = new DecimalFormatSymbols(Locale.getDefault());

      if(decimalSeparator != null && !decimalSeparator.isEmpty()) {
        mDecimalSeparator = decimalSeparator.charAt(0);
      }else{
        mDecimalSeparator = context.getString(R.string.default_decimal_separator).charAt(0);
      }

      decimalFormatSymbol.setDecimalSeparator(mDecimalSeparator);
      mDecimalFormat.setDecimalFormatSymbols(decimalFormatSymbol);

      setAmount(mAmount);
    }finally {
      typedArray.recycle();
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    setMeasuredDimension(mWidth, mHeight);
  }

  public void setAmount(float amount){
    mAmount = amount;
    createTextFromAmount();
  }

  private void createTextFromAmount() {
    String formattedAmount = mDecimalFormat.format(mAmount);

    int separatorIndex = formattedAmount.indexOf(mDecimalSeparator);

    if(separatorIndex > -1) {
      mIntegerSection.text = formattedAmount.substring(0, separatorIndex);
      mDecimalSection.text = formattedAmount.substring(mIncludeDecimalSeparator ? separatorIndex :
          separatorIndex + 1);
    }else{
      mIntegerSection.text = formattedAmount;
      mDecimalSection.text = "";
    }

    calculateBounds();
    calculatePositions();
    requestLayout();
  }

  private void calculateBounds() {
    mIntegerSection.calculateBounds(mTextPaint);
    mDecimalSection.calculateBounds(mTextPaint);
    mSymbolSection.calculateBounds(mTextPaint);

    mWidth = (int) (mIntegerSection.width + mDecimalSection.width + mSymbolSection.width
        + mSymbolMargin + mDecimalMargin + getPaddingLeft() + getPaddingRight());
    mHeight = getPaddingTop() + getPaddingBottom()
        + Math.max(mIntegerSection.height, Math.max(mDecimalSection.height, mSymbolSection.height));
  }

  private void calculatePositions() {

    int symbolGravityXAxis  = mSymbolGravity & GRAVITY_START;
    int symbolGravityYAxis  = mSymbolGravity & GRAVITY_TOP;

    if(symbolGravityXAxis == GRAVITY_START){
      mSymbolSection.x  = getPaddingLeft();
      mIntegerSection.x = (int) (mSymbolSection.x + mSymbolSection.width + mSymbolMargin);
      mDecimalSection.x = (int) (mIntegerSection.x + mIntegerSection.width + mDecimalMargin);
    }else{
      mIntegerSection.x = getPaddingLeft();
      mDecimalSection.x = (int) (mIntegerSection.x + mIntegerSection.width + mDecimalMargin);
      mSymbolSection.x  = (int) (mDecimalSection.x + mDecimalSection.width + mSymbolMargin);
    }

    int y = mHeight - getPaddingBottom();
    mIntegerSection.y = y;
    mSymbolSection.y  = y - (symbolGravityYAxis == GRAVITY_TOP ?
                            mIntegerSection.height - mSymbolSection.height : 0);
    mDecimalSection.y = y - (mDecimalGravity == GRAVITY_TOP ?
                            mIntegerSection.height - mDecimalSection.height : 0);
  }


  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    drawSection(canvas, mIntegerSection);
    drawSection(canvas, mDecimalSection);
    drawSection(canvas, mSymbolSection);
  }


  private void drawSection(Canvas canvas, Section section){
    mTextPaint.setTextSize(section.textSize);
    mTextPaint.setColor(section.color);
    mTextPaint.setUnderlineText(section.drawUnderline);
    canvas.drawText(section.text, section.x, section.y, mTextPaint);
  }


  private static class Section{
    public int x;
    public int y;
    public Rect bounds;
    public String text;
    public float textSize;
    public int color;
    public int width;
    public int height;
    public boolean drawUnderline;

    public Section(){
      bounds = new Rect();
    }

    public void calculateBounds(TextPaint paint){
      TextUtils.getTextBounds(paint, text, bounds, textSize);
      width = bounds.width();
      height = bounds.height();
    }
  }

}
