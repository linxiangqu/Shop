package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.BuyWithSendRecyclerAdapter;
import com.linxiangqu.shop.bean.good.GetBuySongPage;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import okhttp3.Call;
import okhttp3.Response;

public class BuyWithSendActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private RecyclerView recyclerView;
    private BuyWithSendRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buywithsend);
        initToolbar();
        initView();
        initData();
        bindData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_buywithsend_recycler);
    }

    @Override
    public void initData() {
        OKHttpManager.postGetBuySongPage(Config.BUY_SONG_PAGE, Config.getCacheUserId(this), 1, 20, new SpotsCallBack<GetBuySongPage>(this) {
            @Override
            public void onSuccess(Call call, Response response, GetBuySongPage getBuySongPage) {
                if (getBuySongPage.getTotalCount() != 0) {
                    adapter = new BuyWithSendRecyclerAdapter(BuyWithSendActivity.this, getBuySongPage.getBuySongListContentDTO());
                    recyclerView.setLayoutManager(new LinearLayoutManager(BuyWithSendActivity.this));
                    recyclerView.setAdapter(adapter);
                }
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
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
        mToolbar_tv.setText("买就送");
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            default:
                break;
        }
    }
}
