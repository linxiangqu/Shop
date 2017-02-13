package com.linxiangqu.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.BankAndAlipayRecyclerAdapter;
import com.linxiangqu.shop.bean.good.QueryCustomerCardList;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import okhttp3.Call;
import okhttp3.Response;

public class BankAndAlipayActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private int selector;
    private BankAndAlipayRecyclerAdapter adapter;
    private TextView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankandalipay);
        selector = getIntent().getIntExtra("selector", -1);
        bindData();
        initToolbar();
        initView();
        initData();
        initListener();
        setStatusBar();
    }

    @Override
    public void bindData() {
        OKHttpManager.postQueryCustomerCardList(Config.QUERY_CUSTOMER_CARD_LIST, Config.getCacheUserId(this), selector, new SpotsCallBack<QueryCustomerCardList>(this) {
            @Override
            public void onSuccess(Call call, Response response, QueryCustomerCardList queryCustomerCardList) {
                if (queryCustomerCardList.getStateCode() == 0) {
                    adapter = new BankAndAlipayRecyclerAdapter(BankAndAlipayActivity.this, queryCustomerCardList.getMallCustomerCardDTOs());
                    recyclerView.setLayoutManager(new LinearLayoutManager(BankAndAlipayActivity.this));
                    recyclerView.setAdapter(adapter);
                } else
                    showToasts(queryCustomerCardList.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        if (selector == 1)
            mToolbar_tv.setText("银行卡");
        if (selector == 2)
            mToolbar_tv.setText("支付宝");
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_bankandalipay_recyclerview);
        linearLayout = (LinearLayout) findViewById(R.id.activity_bankandalipay_linearlayout);
        add = (TextView) findViewById(R.id.add);
        if (selector == 1)
            add.setText("添加银行卡");
        if (selector == 2)
            add.setText("添加支付宝");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        linearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.activity_bankandalipay_linearlayout:
                Intent intent = new Intent(this, AddBankOrAlipayActivity.class);
                intent.putExtra("selector", selector);
                startActivityForResult(intent, 0);
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == 1)
                bindData();
            else if (resultCode == 2)
                bindData();
        }
    }
}
