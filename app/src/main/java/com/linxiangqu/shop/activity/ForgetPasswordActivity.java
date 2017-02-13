package com.linxiangqu.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.user.Forget;
import com.linxiangqu.shop.bean.user.GetForgetSMS;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import okhttp3.Call;
import okhttp3.Response;

public class ForgetPasswordActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private EditText sjh, yzm, password, repassword;
    private Button huoquyzm, sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initToolbar();
        initView();
        initData();
        initListener();
        bindData();
        setStatusBar();
    }

    @Override
    public void initView() {
        sjh = (EditText) findViewById(R.id.activity_forget_password_sjh);
        yzm = (EditText) findViewById(R.id.activity_forget_password_yzm);
        password = (EditText) findViewById(R.id.activity_forget_password_password);
        repassword = (EditText) findViewById(R.id.activity_forget_password_qrpassword);
        huoquyzm = (Button) findViewById(R.id.activity_forget_password_fsyzm);
        sure = (Button) findViewById(R.id.activity_forget_password_sure);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        huoquyzm.setOnClickListener(this);
        sure.setOnClickListener(this);
    }

    @Override
    public void bindData() {

    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText(R.string.wjmm);
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.activity_forget_password_fsyzm:
                getForgetSMS();
                break;
            case R.id.activity_forget_password_sure:
                if (password.getText().toString().trim().equals(repassword.getText().toString().trim()))
                    modifyPassword();
                else
                    showToasts("两次密码不相同！");
                break;
            default:
                break;
        }
    }

    private void modifyPassword() {
        String photo = sjh.getText().toString().trim();
        if (!Config.isMobileNO(photo)) {
            showToasts("请输入正确的手机号码！");
        } else
            OKHttpManager.postForget(Config.FORGET, sjh.getText().toString().trim(), password.getText().toString().trim(), yzm.getText().toString().trim(), 2, new SpotsCallBack<Forget>(this) {
                @Override
                public void onSuccess(Call call, Response response, Forget forget) {
                    if (forget.getStateCode() == 0) {
                        Intent intent = getIntent();
                        intent.putExtra("username", sjh.getText().toString().trim());
                        intent.putExtra("password", password.getText().toString().trim());
                        setResult(2, intent);
                        finish();
                    }
                    showToasts(forget.getMsg());
                    call.cancel();
                }

                @Override
                public void onError(Call call, Response response) {
                    showErrorToasts(call, response);
                }
            });
    }

    private void getForgetSMS() {
        String photo = sjh.getText().toString().trim();
        if (!Config.isMobileNO(photo)) {
            showToasts("请输入正确的手机号码！");
        } else
            OKHttpManager.postGetForgetSMS(Config.GET_FORGET_SMS, sjh.getText().toString().trim(), 2, new SpotsCallBack<GetForgetSMS>(this) {
                @Override
                public void onSuccess(Call call, Response response, GetForgetSMS getForgetSMS) {
                    showToasts(getForgetSMS.getMsg());
                    call.cancel();
                }

                @Override
                public void onError(Call call, Response response) {
                    showErrorToasts(call, response);
                }
            });
    }
}
