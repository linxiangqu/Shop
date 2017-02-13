package com.linxiangqu.shop.common.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;

import java.lang.reflect.Field;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by linxiangqu on 2016/8/4.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    protected void openActivity(Class<?> pClass) {
        Intent mIntent = new Intent(this, pClass);
        this.startActivity(mIntent);
    }

    protected void openActivityForResult(Class<?> pClass, int code) {
        Intent mIntent = new Intent(this, pClass);
        startActivityForResult(mIntent, code);
    }

    /**
     * Toast弹出网络请求Error
     */
    public void showErrorToasts(Call call, Response response) {
        CharSequence text = Config.getCodeMsg(this, response.code());
        CharSequence content = TextUtils.isEmpty(text) ? "程序出错，请稍候再试!" : text;
        if (mToast != null) {
            mToast.setText(content);
            mToast.show();
            return;
        }
        mToast = mToast.makeText(this, content, mToast.LENGTH_LONG);
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
        mToast = mToast.makeText(this, text, mToast.LENGTH_LONG);
        mToast.show();
    }

    /**
     * 设置沉浸式状态栏
     */
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final ViewGroup linear_bar = (ViewGroup) findViewById(R.id.toolbar);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}
