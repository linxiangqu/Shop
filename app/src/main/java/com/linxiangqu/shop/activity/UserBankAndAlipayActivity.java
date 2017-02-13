package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.ChooseCustomerCardRecyclerAdapter;
import com.linxiangqu.shop.bean.good.ChoseCustomerCard;
import com.linxiangqu.shop.bean.good.QueryCustomerCardList;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import okhttp3.Call;
import okhttp3.Response;

public class UserBankAndAlipayActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private RecyclerView recyclerView;
    private ChooseCustomerCardRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userbankandalipay);
        initToolbar();
        initView();
        initData();
        initListener();
        setStatusBar();

    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_userbankandalipay_revyclerview);
    }

    @Override
    public void initData() {
        OKHttpManager.postQueryCustomerCardList(Config.QUERY_CUSTOMER_CARD_LIST, Config.getCacheUserId(this), 0, new SpotsCallBack<QueryCustomerCardList>(this) {
            @Override
            public void onSuccess(Call call, Response response, QueryCustomerCardList queryCustomerCardList) {
                if (queryCustomerCardList.getStateCode() == 0) {
                    adapter = new ChooseCustomerCardRecyclerAdapter(UserBankAndAlipayActivity.this, queryCustomerCardList.getMallCustomerCardDTOs());
                    recyclerView.setLayoutManager(new LinearLayoutManager(UserBankAndAlipayActivity.this));
                    recyclerView.setAdapter(adapter);
                } else
                    showToasts(queryCustomerCardList.getMsg());
                call.cancel();
                adapter.setListener(new ChooseCustomerCardRecyclerAdapter.ChangeListener() {
                    @Override
                    public void ItemChangeListener(View view, QueryCustomerCardList.MallCustomerCardDTOsBean mallCustomerCardDTOsBean, int position) {
                        if (!mallCustomerCardDTOsBean.isIsUse())
                            chooseCard(mallCustomerCardDTOsBean, position);
                    }
                });
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    private void chooseCard(QueryCustomerCardList.MallCustomerCardDTOsBean mallCustomerCardDTOsBean, final int position) {
        OKHttpManager.postChoseCustomerCard(Config.CHOSE_CUSTOMER_CARD, Config.getCacheUserId(this), mallCustomerCardDTOsBean.getCardId(), new SpotsCallBack<ChoseCustomerCard>(this) {
            @Override
            public void onSuccess(Call call, Response response, ChoseCustomerCard choseCustomerCard) {
                if (choseCustomerCard.getStateCode() == 0) {
                    adapter.setDefaultCard(position);
                    setResult(3);
                }
                showToasts(choseCustomerCard.getMsg());
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
        mToolbar_tv.setText("提现方式");
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
