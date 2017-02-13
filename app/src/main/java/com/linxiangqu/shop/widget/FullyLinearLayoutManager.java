package com.linxiangqu.shop.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.linxiangqu.shop.activity.JiaoLiuQuActivity;

/**
 * Created by linxiangqu on 2016/9/22.
 */
public class FullyLinearLayoutManager extends LinearLayoutManager {
    public FullyLinearLayoutManager(Context context) {
        super(context);
    }

    public FullyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    private int[] mMeasuredDimension = new int[2];

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        Log.e("onmeasure", "on");
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);
        Log.e("wsize+hsize", "" + widthSize + " " + heightSize);
        Log.e("count", "" + getItemCount());
        int width = 0;
        int height = 0;
        for (int i = 0; i < getItemCount(); i++) {
            Log.e("position", "" + i);
            measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasuredDimension);

            if (getOrientation() == HORIZONTAL) {
                Log.e("HORIZONTAL", "HORIZONTAL");
                width = width + mMeasuredDimension[0];
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                Log.e("vetial", "vetial");
                height = height + mMeasuredDimension[1];
                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }
        Log.e("witdth_tmp", "" + width);
        Log.e("hei_tmp", "" + height);
        switch (widthMode) {
            case View.MeasureSpec.EXACTLY: {
                Log.e("with", "ex");
                width = widthSize;
            }
            case View.MeasureSpec.AT_MOST:
                Log.e("with", "most");

            case View.MeasureSpec.UNSPECIFIED:
                Log.e("with", "un");

        }

        switch (heightMode) {
            case View.MeasureSpec.EXACTLY: {
                Log.e("hei", "ex");
                height = heightSize;
            }
            case View.MeasureSpec.AT_MOST:
                Log.e("hei", "mo");

            case View.MeasureSpec.UNSPECIFIED:
                Log.e("hei", "un");

        }
        Log.e("width+height", width + " " + height);
        setMeasuredDimension(width, height);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec, int[] measuredDimension) {
        Log.e("scrapchild", "");
        View view = recycler.getViewForPosition(position);
        if (view != null) {
            Log.e("position", position + "");
            Log.e("View", "exist");
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
            int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                    getPaddingLeft() + getPaddingRight(), p.width);
            Log.e("widspec_mode", View.MeasureSpec.getSize(widthSpec) + "");
            switch (View.MeasureSpec.getMode(widthSpec)) {
                case View.MeasureSpec.EXACTLY:
                    Log.e("wid", "ex");
                    break;
                case View.MeasureSpec.AT_MOST:
                    Log.e("wid", "at");
                    break;
                case View.MeasureSpec.UNSPECIFIED:
                    Log.e("wid", "un");
                    break;
                default:
                    break;
            }
            Log.e("padingwdi", getPaddingLeft() + getPaddingRight() + "");
            Log.e("P.wid", p.width + "");
            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                    getPaddingTop() + getPaddingBottom(), p.height);
            Log.e("widspec_mode", View.MeasureSpec.getSize(heightSpec) + "");
            switch (View.MeasureSpec.getMode(heightSpec)) {
                case View.MeasureSpec.EXACTLY:
                    Log.e("hei", "ex");
                    break;
                case View.MeasureSpec.AT_MOST:
                    Log.e("hei", "at");
                    break;
                case View.MeasureSpec.UNSPECIFIED:
                    Log.e("hei", "un");
                    break;
                default:
                    break;
            }
            Log.e("hei", heightSpec + "");
            Log.e("padinghei", getPaddingTop() + getPaddingBottom() + "");
            Log.e("P.hei", p.height + "");
            Log.e("children", View.MeasureSpec.getSize(childWidthSpec) + " " + View.MeasureSpec.getSize(childHeightSpec));

            switch (View.MeasureSpec.getMode(childWidthSpec)) {
                case View.MeasureSpec.EXACTLY:
                    Log.e("cw", "ex");
                    break;
                case View.MeasureSpec.AT_MOST:
                    Log.e("cw", "at");
                    break;
                case View.MeasureSpec.UNSPECIFIED:
                    Log.e("cw", "un");
                    break;
                default:
                    break;
            }
            switch (View.MeasureSpec.getMode(childHeightSpec)) {
                case View.MeasureSpec.EXACTLY:
                    Log.e("ch", "ex");
                    break;
                case View.MeasureSpec.AT_MOST:
                    Log.e("ch", "at");
                    break;
                case View.MeasureSpec.UNSPECIFIED:
                    Log.e("ch", "un");
                    break;
                default:
                    break;
            }

            view.measure(childWidthSpec, childHeightSpec);
            measuredDimension[0] = (view.getMeasuredWidth() + p.leftMargin + p.rightMargin) / 2;
            Log.e("dimen_w", measuredDimension[0] + "");
            measuredDimension[1] = (view.getMeasuredHeight() + p.bottomMargin + p.topMargin) * 2;
            Log.e("dimen_h", measuredDimension[1] + "");

            recycler.recycleView(view);
        }
    }
}
