package com.linxiangqu.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.MyFragmentPagerAdapter;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragmentActivity;
import com.linxiangqu.shop.fragment.OrderFrament;

public class OrderActivity extends BaseFragmentActivity implements IDefineView, View.OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        initToolbar();
        initView();
        initData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        i = intent.getIntExtra("page", 0);
        mTabLayout = (TabLayout) findViewById(R.id.activity_myorder_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.activity_myorder_viewpager);
    }

    @Override
    public void initData() {
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        myFragmentPagerAdapter.addFragment(new OrderFrament().newInstance(this, 0), "全部");
        myFragmentPagerAdapter.addFragment(new OrderFrament().newInstance(this, 10), "待支付");
        myFragmentPagerAdapter.addFragment(new OrderFrament().newInstance(this, 20), "待发货");
        myFragmentPagerAdapter.addFragment(new OrderFrament().newInstance(this, 30), "待收货");
        myFragmentPagerAdapter.addFragment(new OrderFrament().newInstance(this, 40), "待评价");
        myFragmentPagerAdapter.addFragment(new OrderFrament().newInstance(this, 89), "退款/退货");
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(i);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText(R.string.wddd);
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindData();
    }
}
