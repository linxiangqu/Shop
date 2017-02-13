package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.TiXianHistoryRecyclerAdapter;
import com.linxiangqu.shop.bean.good.QueryCashOutList;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import okhttp3.Call;
import okhttp3.Response;

public class TiXianHistoryActivity extends BaseActivity implements IDefineView, View.OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private RecyclerView recyclerView;
    private TiXianHistoryRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixianhistory);
        initToolbar();
        initView();
        bindData();
        initData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_tixianhistory);
    }

    @Override
    public void initData() {
        OKHttpManager.postQueryCashOutList(Config.QUERY_CASH_OUT_LIST, Config.getCacheUserId(this), 1, 20, new SpotsCallBack<QueryCashOutList>(this) {
            @Override
            public void onSuccess(Call call, Response response, QueryCashOutList queryCashOutList) {
                if (queryCashOutList.getStateCode() == 0) {
                    adapter = new TiXianHistoryRecyclerAdapter(TiXianHistoryActivity.this, queryCashOutList.getMallCashOutDTOs());
                    recyclerView.setLayoutManager(new LinearLayoutManager(TiXianHistoryActivity.this));
                    recyclerView.setAdapter(adapter);
                } else
                    showToasts(queryCashOutList.getMsg());
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
        mToolbar_tv.setText("提现历史纪录");
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
