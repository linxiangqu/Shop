package com.linxiangqu.shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxiangqu.shop.activity.LoginActivity;
import com.linxiangqu.shop.bean.Tab;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.fragment.MainFragment;
import com.linxiangqu.shop.fragment.PeopleFragment;
import com.linxiangqu.shop.fragment.ShopCarFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements IDefineView {

    private FragmentTabHost mFragmentTabHost;
    private List<Tab> mTab = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_main);
        initView();
        initToolbar();
        initListener();
        initData();
        bindData();
    }

    @Override
    public void initView() {
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        Tab sy = new Tab(R.drawable.selector_main_activity_tabhost_ico_sy, R.string.sy, MainFragment.class);
        Tab order = new Tab(R.drawable.selector_main_activity_tabhost_ico_gwc, R.string.order, ShopCarFragment.class);
        Tab people = new Tab(R.drawable.selector_main_activity_tabhost_ico_wd, R.string.people, PeopleFragment.class);
        mTab.add(sy);
        mTab.add(order);
        mTab.add(people);
        for (Tab tab : mTab) {
            mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(getString(tab.getTabName())).setIndicator(build(tab)), tab.getFragment(), null);
        }
        mFragmentTabHost.setCurrentTab(0);
    }

    private View build(Tab tab) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_main_activity_tabhost, null);
        ImageView mImageView = (ImageView) view.findViewById(R.id.item_main_activity_tabhost_img);
        TextView mTextView = (TextView) view.findViewById(R.id.item_main_activity_tabhost_tv);
        mImageView.setBackgroundResource(tab.getIcoId());
        mTextView.setText(tab.getTabName());
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mFragmentTabHost.getTabWidget().getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Config.getCacheUserId(MainActivity.this) == 33)
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                else
                    mFragmentTabHost.setCurrentTab(1);
            }
        });
        mFragmentTabHost.getTabWidget().getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Config.getCacheUserId(MainActivity.this) == 33)
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                else
                    mFragmentTabHost.setCurrentTab(2);
            }
        });
    }

    @Override
    public void bindData() {

    }

    @Override
    public void initToolbar() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1)
                mFragmentTabHost.setCurrentTab(0);
        }
    }
}
