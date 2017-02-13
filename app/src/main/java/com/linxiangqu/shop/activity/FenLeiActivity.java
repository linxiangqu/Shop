package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.MyFragmentPagerAdapter;
import com.linxiangqu.shop.bean.good.GetGoodsList;
import com.linxiangqu.shop.bean.eventbus.ShaiXuanEvent;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragmentActivity;
import com.linxiangqu.shop.fragment.ShaiXuanFragment;
import com.linxiangqu.shop.fragment.XiaoLiangFragment;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class FenLeiActivity extends BaseFragmentActivity implements IDefineView, View.OnClickListener {
    private ImageButton mToolbar_back;
    private LinearLayout mToolbar_LinearLayout;
    private TextView mToolbar_tv;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private int i;
    private ShaiXuanEvent shaiXuanEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_fenlei);
        initToolbar();
        initView();
        initData();
        initListener();
        request(null, i, 1);
        setStatusBar();
    }

    @Override
    public void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.activity_fenlei_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.activity_fenlei_viewpager);
    }

    @Override
    public void initData() {
        i = getIntent().getIntExtra("id", 0);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
    }

    public void request(ShaiXuanEvent shaiXuanEvent, int firstGoodsClassId, int pageNo) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", Config.getCacheUserId(this) + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", 20 + "");
        params.put("firstGoodsClassId", firstGoodsClassId + "");
        if (shaiXuanEvent != null) {
            params.put("smallPrice", shaiXuanEvent.getFirstMoney() + "");
            params.put("bigPrice", shaiXuanEvent.getEndMoney() + "");
            params.put("smallSalenum", shaiXuanEvent.getFirstXL() + "");
            params.put("bigSalenum", shaiXuanEvent.getEndXL() + "");
            if (shaiXuanEvent.getFl() != -1)
                params.put("secondGoodsClassId", shaiXuanEvent.getFl() + "");
        }
        OKHttpManager.postGetGoodsList(Config.GET_GOODS_LIST, params, new SpotsCallBack<GetGoodsList>(this) {
            @Override
            public void onSuccess(Call call, Response response, GetGoodsList getGoodsList) {
                if (getGoodsList.getStateCode() == 0) {
                    myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), FenLeiActivity.this);
                    myFragmentPagerAdapter.addFragment(new XiaoLiangFragment().newInstance(FenLeiActivity.this, "销量最多", getGoodsList.getMallGoodsDTO()), "销量最多");
                    myFragmentPagerAdapter.addFragment(new ShaiXuanFragment().newInstance(FenLeiActivity.this, "筛选", getGoodsList.getMallGoodsSecondClassDTO()), "筛选");
                    mTabLayout.setTabMode(TabLayout.MODE_FIXED);
                    mViewPager.setAdapter(myFragmentPagerAdapter);
                    mTabLayout.setupWithViewPager(mViewPager);
                } else
                    showToasts(getGoodsList.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @Subscribe
    public void onEventMainThread(ShaiXuanEvent shaiXuanEvent) {
        if (shaiXuanEvent != null) {
            this.shaiXuanEvent = new ShaiXuanEvent();
            this.shaiXuanEvent = shaiXuanEvent;
            request(shaiXuanEvent, i, 1);
            mViewPager.setCurrentItem(0);
        }
    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setVisibility(View.GONE);
        mToolbar_LinearLayout = (LinearLayout) findViewById(R.id.toolbar_ss);
        mToolbar_LinearLayout.setVisibility(View.VISIBLE);
        mToolbar_back.setOnClickListener(this);
        mToolbar_LinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.toolbar_ss:
                Toast.makeText(this, "toolbar_ss", Toast.LENGTH_SHORT).show();
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
