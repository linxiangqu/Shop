package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.user.GetCustomerCode;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.squareup.picasso.Picasso;

import okhttp3.Call;
import okhttp3.Response;

public class InviteCodeActivity extends BaseActivity implements OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private ImageView img;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitecode);
        initView();
        request();
        initToolbar();
        setStatusBar();
    }

    private void request() {
        OKHttpManager.postGetCustomerCode(Config.GET_CUSTOMER_CODE, Config.getCacheUserId(this), new SpotsCallBack<GetCustomerCode>(this) {
            @Override
            public void onSuccess(Call call, Response response, GetCustomerCode getCustomerCode) {
                if (getCustomerCode.getStateCode() == 0) {
                    Picasso.with(InviteCodeActivity.this).load(Config.IP + getCustomerCode.getQrcode()).into(img);
                    tv.setText(getCustomerCode.getCode());
                }
                showToasts(getCustomerCode.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.activity_invitecode_yqm_img);
        tv = (TextView) findViewById(R.id.activity_invitecode_yqm_tv);
    }

    private void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText("我的邀请码");
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
