package com.linxiangqu.shop.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.linxiangqu.shop.Config;

import java.lang.reflect.Field;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by linxiangqu on 2016/7/26.
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    private Toast mToast;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void openActivity(Class<?> pClass) {
        Intent mIntent = new Intent(getActivity(), pClass);
        this.startActivity(mIntent);
    }

    protected void openActivityForResult(Class<?> pClass, int code) {
        Intent mIntent = new Intent(getActivity(), pClass);
        getActivity().startActivityForResult(mIntent, code);
    }

    /**
     * Toast弹出网络请求Error
     */
    public void showErrorToasts(Call call, Response response) {
        CharSequence text = Config.getCodeMsg(mActivity, response.code());
        CharSequence content = TextUtils.isEmpty(text) ? "程序出错，请稍候再试!" : text;
        if (mToast != null) {
            mToast.setText(content);
            mToast.show();
            return;
        }
        mToast = mToast.makeText(mActivity, content, mToast.LENGTH_LONG);
        mToast.show();
        call.cancel();
    }

    /**
     * Toast弹出普通
     *
     * @param text
     */
    public void showToasts(final CharSequence text) {
        if (mToast != null) {
            mToast.setText(text);
            mToast.show();
            return;
        }
        mToast = mToast.makeText(mActivity, text, mToast.LENGTH_LONG);
        mToast.show();
    }

    /**
     * 设置沉浸式状态栏
     */
    protected void setStatusBar(final LinearLayout linear_bar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final int statusHeight = getStatusBarHeight();
            linear_bar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = linear_bar.getHeight();
                    android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) linear_bar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    linear_bar.setLayoutParams(params);
                }
            });
        }
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    protected int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
