package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.good.AddCustomerCard;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import java.util.ArrayList;

import cn.qqtheme.framework.picker.OptionPicker;
import okhttp3.Call;
import okhttp3.Response;

public class AddBankOrAlipayActivity extends BaseActivity implements IDefineView, View.OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private int selector;
    private LinearLayout bank, zfb;
    private TextView bank_xm, bank_kh, bank_szwd, zfb_xm, zfb_zh;
    private TextView bank_yhxz;
    private Button ok;
    private String cardName, cardNo, cardNetwork, cardCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbankoralipay);
        bindData();
        initToolbar();
        initView();
        initListener();
        setStatusBar();
    }

    @Override
    public void initView() {
        bank = (LinearLayout) findViewById(R.id.activity_takecash_bank);
        zfb = (LinearLayout) findViewById(R.id.activity_takecash_zfb);
        if (selector == 1)
            bank.setVisibility(View.VISIBLE);
        else if (selector == 2)
            zfb.setVisibility(View.VISIBLE);
        bank_xm = (TextView) findViewById(R.id.activity_takecash_bank_xm);
        bank_kh = (TextView) findViewById(R.id.activity_takecash_bank_kh);
        bank_yhxz = (TextView) findViewById(R.id.activity_takecash_bank_yhxz);
        bank_szwd = (TextView) findViewById(R.id.activity_takecash_bank_szwd);
        zfb_xm = (TextView) findViewById(R.id.activity_takecash_zfb_xm);
        zfb_zh = (TextView) findViewById(R.id.activity_takecash_zfb_zh);
        ok = (Button) findViewById(R.id.activity_takecash_btnOK);
        bank_yhxz.setOnClickListener(this);
        ok.setOnClickListener(this);

    }

    private void initbankPickerView() {
        String[] mBanks = getResources().getStringArray(R.array.banks);
        OptionPicker picker = new OptionPicker(this, mBanks);
        picker.setOffset(2);
        picker.setSelectedIndex(1);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(String option) {
                bank_yhxz.setText(option);
            }
        });
        picker.show();
    }

    @Override
    public void initData() {
        if (selector == 1) {
            cardName = bank_xm.getText().toString().trim();
            cardNo = bank_kh.getText().toString().trim();
            cardCompany = bank_yhxz.getText().toString().trim();
            cardNetwork = bank_kh.getText().toString().trim();
        }
        if (selector == 2) {
            cardName = zfb_xm.getText().toString().trim();
            cardNo = zfb_zh.getText().toString().trim();
            cardCompany = null;
            cardNetwork = null;
        }
        OKHttpManager.postAddCustomerCard(Config.ADD_CUSTOMER_CARD, Config.getCacheUserId(this), selector, cardNo, cardName, cardCompany, cardNetwork, new SpotsCallBack<AddCustomerCard>(this) {
            @Override
            public void onSuccess(Call call, Response response, AddCustomerCard addCustomerCard) {
                if (addCustomerCard.getStateCode() == 0) {
                    if (selector == 1) {
                        setResult(1);
                    } else if (selector == 2) {
                        setResult(2);
                    }
                    showToasts(addCustomerCard.getMsg());
                    call.cancel();
                    finish();
                } else {
                    showToasts(addCustomerCard.getMsg());
                    call.cancel();
                }
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
        selector = getIntent().getIntExtra("selector", -1);
    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        if (selector == 1)
            mToolbar_tv.setText("银行卡");
        else if (selector == 2)
            mToolbar_tv.setText("支付宝");
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.activity_takecash_bank_yhxz:
                initbankPickerView();
                break;
            case R.id.activity_takecash_btnOK:
                initData();
                break;
            default:
                break;
        }
    }
}
