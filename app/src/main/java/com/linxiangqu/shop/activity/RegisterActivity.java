package com.linxiangqu.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.user.GetRegisterSMS;
import com.linxiangqu.shop.bean.user.Register;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private EditText sjh, yzm, password, repassword, yqm;
    private Button huoquyzm, register_btn;
    private LinearLayout saomiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initToolbar();
        initView();
        initData();
        initListener();
        bindData();
        setStatusBar();
    }

    @Override
    public void initView() {
        sjh = (EditText) findViewById(R.id.activity_register_sjh);
        yzm = (EditText) findViewById(R.id.activity_register_yzm);
        password = (EditText) findViewById(R.id.activity_register_password);
        repassword = (EditText) findViewById(R.id.activity_register_qrpassword);
        yqm = (EditText) findViewById(R.id.activity_register_yqm);
        huoquyzm = (Button) findViewById(R.id.activity_register_fsyzm);
        register_btn = (Button) findViewById(R.id.activity_register_register);
        saomiao = (LinearLayout) findViewById(R.id.activity_register_saomiao);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        huoquyzm.setOnClickListener(this);
        saomiao.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    @Override
    public void bindData() {

    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText(R.string.yhzc);
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.activity_register_fsyzm:
                getRegisterSMS();
                break;
            case R.id.activity_register_saomiao:
                openActivityForResult(ScanActivity.class, 1);
                break;
            case R.id.activity_register_register:
                if (password.getText().toString().trim().equals(repassword.getText().toString().trim()))
                    register();
                else
                    Toast.makeText(RegisterActivity.this, "两次密码不相同！", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                String inviteCode = data.getStringExtra("inviteCode");
                yqm.setText(inviteCode);
            }
        }
    }

    private void register() {
        String photo = sjh.getText().toString().trim();
        if (!Config.isMobileNO(photo)) {
            showToasts("请输入正确的手机号码！");
        } else
            OKHttpManager.postRegister(Config.REGISTER, sjh.getText().toString().trim(), password.getText().toString().trim(), yzm.getText().toString().trim(), yqm.getText().toString().trim(), 2, new SpotsCallBack<Register>(this) {
                @Override
                public void onSuccess(Call call, Response response, Register register) {
                    if (register.getStateCode() == 0) {
                        Intent intent = getIntent();
                        intent.putExtra("username", sjh.getText().toString().trim());
                        intent.putExtra("password", password.getText().toString().trim());
                        setResult(1, intent);
                        finish();
                    }
                    showToasts(register.getMsg());
                    call.cancel();
                }

                @Override
                public void onError(Call call, Response response) {
                    showErrorToasts(call, response);
                }
            });
    }

    private void getRegisterSMS() {
        String photo = sjh.getText().toString().trim();
        if (!Config.isMobileNO(photo)) {
            showToasts("请输入正确的手机号码！");
        } else
            OKHttpManager.postGetRegisterSMS(Config.GET_REGISTER_SMS, sjh.getText().toString().trim(), 2, new SpotsCallBack<GetRegisterSMS>(this) {
                @Override
                public void onSuccess(Call call, Response response, GetRegisterSMS getRegisterSMS) {
                    showToasts(getRegisterSMS.getMsg());
                    call.cancel();
                }

                @Override
                public void onError(Call call, Response response) {
                    showErrorToasts(call, response);
                }
            });
    }
}
