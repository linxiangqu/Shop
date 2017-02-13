package com.linxiangqu.shop.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.utils.DensityDpiUtil;

/**
 * Created by linxiangqu on 2016/9/1.
 */
public class MyTextView extends TextView {
    private Context mContext;
    private Paint mPaint;
    private int mColor, mWidth;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mPaint = new Paint();
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MyTextView, defStyleAttr, 0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int type = typedArray.getIndex(i);
            switch (type) {
                case R.styleable.MyTextView_mytextview_width:
                    mWidth = typedArray.getDimensionPixelSize(type, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, context.getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyTextView_mytextview_color:
                    mColor = typedArray.getColor(type, Color.green(R.color.gray));
                    break;
            }
        }
        typedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mWidth);
        mPaint.setColor(mColor);
        canvas.drawLine(0, 0, getMeasuredWidth(), getMeasuredHeight() - DensityDpiUtil.dip2px(mContext, 4), mPaint);
        canvas.drawLine(0, DensityDpiUtil.dip2px(mContext, 4), getMeasuredWidth(), getMeasuredHeight(), mPaint);
    }
}
