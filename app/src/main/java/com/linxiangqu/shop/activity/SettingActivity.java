package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.common.base.BaseActivity;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initToolbar();
        setStatusBar();
    }

    private void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText("设置");
        mToolbar_back.setOnClickListener(this);
    }

    private void initView() {
        back = (Button) findViewById(R.id.activity_setting_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.activity_setting_back:
                setResult(1);//返回 MainActivity 进行Fragment选择
                Config.setCacheUserId(this, 33);
                finish();
                break;
            default:
                break;
        }
    }
}
