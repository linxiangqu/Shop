package com.linxiangqu.shop.activity;

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
import com.linxiangqu.shop.fragment.JiFenFragment;
import com.linxiangqu.shop.fragment.TiChengFragment;
import com.linxiangqu.shop.fragment.TiXianFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MyAssetsActivity extends BaseFragmentActivity implements View.OnClickListener, IDefineView {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private int page;
    private String money_tc, money_jf, money_tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myassets);
        initToolbar();
        initView();
        initData();
        bindData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.activity_myassets_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.activity_myassets_viewpager);
    }

    @Override
    public void initData() {
        page = getIntent().getIntExtra("page", 0);
        money_tc = getIntent().getStringExtra("money_tc");
        money_jf = getIntent().getStringExtra("money_jf");
        money_tx = getIntent().getStringExtra("money_tx");
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), MyAssetsActivity.this);
        myFragmentPagerAdapter.addFragment(new TiChengFragment().newInstance(MyAssetsActivity.this, money_tc), "提成");
        if (page == 1)
            myFragmentPagerAdapter.addFragment(new JiFenFragment().newInstance(MyAssetsActivity.this, money_jf), "积分");
        myFragmentPagerAdapter.addFragment(new TiXianFragment().newInstance(MyAssetsActivity.this, money_tx), "提现");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(page);
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
        mToolbar_tv.setText(R.string.wdzc);
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
}
