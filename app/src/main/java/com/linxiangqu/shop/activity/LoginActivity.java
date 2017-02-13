package com.linxiangqu.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.user.Login;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements IDefineView, View.OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private TextView login_register, login_forget;
    private EditText username, password;
    private Button login;
    private LinearLayout mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initToolbar();
        initListener();
        setStatusBar();
    }

    @Override
    public void initView() {
        username = (EditText) findViewById(R.id.activity_login_username);
        password = (EditText) findViewById(R.id.activity_login_password);
        login = (Button) findViewById(R.id.activity_login_login);
        login_register = (TextView) findViewById(R.id.activity_login_register);
        login_forget = (TextView) findViewById(R.id.activity_login_forget_password);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        login.setOnClickListener(this);
        login_register.setOnClickListener(this);
        login_forget.setOnClickListener(this);
    }

    @Override
    public void bindData() {
        OKHttpManager.postLogin(Config.LOGIN, username.getText().toString().trim(), password.getText().toString().trim(), 2, new SpotsCallBack<Login>(this) {
            @Override
            public void onSuccess(Call call, Response response, Login login) {
                if (login.getStateCode() == 0) {
                    Config.setCacheUserId(LoginActivity.this, login.getUserId());
                    Config.setCacheUsername(LoginActivity.this, login.getUsername());
                    setResult(1, getIntent().putExtra("username", login.getUsername()));
                    EventBus.getDefault().post("Login");
                    finish();
                } else
                    showToasts(login.getMsg());
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
        mToolbar = (LinearLayout) findViewById(R.id.toolbar);
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar.setBackground(getResources().getDrawable(R.color.login));
        mToolbar_tv.setText(R.string.login);
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_login_login:
                bindData();
                break;
            case R.id.activity_login_register:
                openActivityForResult(RegisterActivity.class, 1);
                break;
            case R.id.activity_login_forget_password:
                openActivityForResult(ForgetPasswordActivity.class, 2);
                break;
            case R.id.toolbar_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == 1) {
                username.setText(data.getStringExtra("username"));
                password.setText(data.getStringExtra("password"));
            }
        }
        if (requestCode == 2) {
            if (resultCode == 2) {
                username.setText(data.getStringExtra("username"));
                password.setText(data.getStringExtra("password"));
            }
        }
    }
}
