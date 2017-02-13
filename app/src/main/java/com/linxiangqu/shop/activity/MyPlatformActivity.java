package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.MyFragmentPagerAdapter;
import com.linxiangqu.shop.bean.good.GetGoodsInfo;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragmentActivity;
import com.linxiangqu.shop.fragment.CommentFragment;
import com.linxiangqu.shop.fragment.DongTaiFragment;
import com.linxiangqu.shop.fragment.GoodsImgTextInfoFragment;
import com.linxiangqu.shop.fragment.GoodsInfoFragment;
import com.linxiangqu.shop.fragment.JiaoLiuQuFragment;
import com.linxiangqu.shop.fragment.ShangPinQuFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MyPlatformActivity extends BaseFragmentActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back, mToolbar_ft;
    private TabLayout mToolbar_tabLayout;
    private ViewPager mViewPager;
    private LinearLayout one, two;
    private MyFragmentPagerAdapter adapter;
    private ShangPinQuFragment shangPinQuFragment;
    private JiaoLiuQuFragment jiaoLiuQuFragment;
    private DongTaiFragment dongTaiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myplatform);
        EventBus.getDefault().register(this);
        initToolbar();
        initView();
        initData();
        bindData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.activity_myplatform_viewpager);
        shangPinQuFragment = new ShangPinQuFragment(this);
        jiaoLiuQuFragment = new JiaoLiuQuFragment(this);
        dongTaiFragment = new DongTaiFragment(this);
    }

    @Override
    public void initData() {
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(shangPinQuFragment, "商品区");
        adapter.addFragment(jiaoLiuQuFragment, "交流区");
        adapter.addFragment(dongTaiFragment, "动态");
        mViewPager.setAdapter(adapter);
        mToolbar_tabLayout.setTabMode(TabLayout.MODE_FIXED);
        mToolbar_tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
    }

    @Subscribe
    public void onEventMainThread(String msg) {
        if (msg.equals("jiaoLiuQuFragment")) {
            jiaoLiuQuFragment.initData();
        }
        if (msg.equals("shangPinQuFragment")) {
            shangPinQuFragment.initData();
        }
        if (msg.equals("replyed")) {
            jiaoLiuQuFragment.initData();
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }

    @Override
    public void initToolbar() {
        one = (LinearLayout) findViewById(R.id.toolbar_linear_one);
        two = (LinearLayout) findViewById(R.id.toolbar_linear_two);
        one.setVisibility(View.GONE);
        two.setVisibility(View.VISIBLE);
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_pl_back);
        mToolbar_ft = (ImageButton) findViewById(R.id.toolbar_pl_ft);
        mToolbar_tabLayout = (TabLayout) findViewById(R.id.activity_myplatform_tablayout);
        mToolbar_back.setOnClickListener(this);
        mToolbar_ft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_pl_back:
                finish();
                break;
            case R.id.toolbar_pl_ft:
                openActivity(FaTieActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
