package com.linxiangqu.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.common.base.BaseActivity;

public class SafetyActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private LinearLayout dlmm, yhk, zfb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        initToolbar();
        initView();
        initListener();
        setStatusBar();
    }

    private void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText("账号安全");
        mToolbar_back.setOnClickListener(this);
    }


    private void initView() {
        dlmm = (LinearLayout) findViewById(R.id.activity_safety_dlmm);
        yhk = (LinearLayout) findViewById(R.id.activity_safety_yhk);
        zfb = (LinearLayout) findViewById(R.id.activity_safety_zfb);
    }

    private void initListener() {
        dlmm.setOnClickListener(this);
        yhk.setOnClickListener(this);
        zfb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.activity_safety_dlmm:
                openActivity(ForgetPasswordActivity.class);
                break;
            case R.id.activity_safety_yhk:
                Intent intent_bank = new Intent(this, BankAndAlipayActivity.class);
                intent_bank.putExtra("selector", 1);
                startActivity(intent_bank);
                break;
            case R.id.activity_safety_zfb:
                Intent intent_zfb = new Intent(this, BankAndAlipayActivity.class);
                intent_zfb.putExtra("selector", 2);
                startActivity(intent_zfb);
                break;
            default:
                break;
        }
    }
}
