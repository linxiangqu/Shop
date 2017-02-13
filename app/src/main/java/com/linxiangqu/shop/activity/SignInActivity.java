package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.SignInRecyclerAdapter;
import com.linxiangqu.shop.bean.user.QueryUserCheckInLogList;
import com.linxiangqu.shop.bean.user.UserCheckIn;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.utils.DateUtil;

import okhttp3.Call;
import okhttp3.Response;

public class SignInActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private LinearLayout mToolbar;
    private TextView jf, rq, title;
    private ImageButton btnOk;
    private RecyclerView recyclerView;
    private SignInRecyclerAdapter adapter;
    private boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initData();
        initToolbar();
        initView();
        bindData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initView() {
        jf = (TextView) findViewById(R.id.sign_in_jf);
        rq = (TextView) findViewById(R.id.sign_in_rq);
        title = (TextView) findViewById(R.id.sign_in_title);
        btnOk = (ImageButton) findViewById(R.id.sign_in_btnOk);
        recyclerView = (RecyclerView) findViewById(R.id.sign_in_recycler);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        btnOk.setOnClickListener(this);
    }

    @Override
    public void bindData() {
        OKHttpManager.postQueryUserCheckInLogList(Config.QUERY_USER_CHECK_IN_LOG_LIST, Config.getCacheUserId(this), new SpotsCallBack<QueryUserCheckInLogList>(this) {
            @Override
            public void onSuccess(Call call, Response response, QueryUserCheckInLogList queryUserCheckInLogList) {
                if (queryUserCheckInLogList.getStateCode() == 0) {
                    adapter = new SignInRecyclerAdapter(SignInActivity.this, queryUserCheckInLogList.getMallUserCheckInLogDTOs());
                    recyclerView.setLayoutManager(new GridLayoutManager(SignInActivity.this, 7));
                    recyclerView.setAdapter(adapter);
                    jf.setText("已获得积分：" + queryUserCheckInLogList.getScore());
                    rq.setText(DateUtil.fromTimestamp(queryUserCheckInLogList.getServiceTime(), DateUtil.Format.YEAR2DAY));
                    title.setText(queryUserCheckInLogList.getTitle());
                    isChecked = queryUserCheckInLogList.isIsChecked();
                    if (isChecked) {
                        btnOk.setBackgroundResource(R.mipmap.chk_sign_1);
                    }
                } else
                    showToasts(queryUserCheckInLogList.getMsg());
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
        mToolbar.setBackground(getResources().getDrawable(R.color.sign_in));
        mToolbar_tv.setText("签到");
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.sign_in_btnOk:
                signIn();
                break;
            default:
                break;
        }
    }

    private void signIn() {
        if (!isChecked) {
            OKHttpManager.postUserCheckIn(Config.USER_CHECK_IN, Config.getCacheUserId(this), new SpotsCallBack<UserCheckIn>(this) {
                @Override
                public void onSuccess(Call call, Response response, UserCheckIn userCheckIn) {
                    if (userCheckIn.getStateCode() == 0) {
                        isChecked = true;
                        bindData();
                    }
                }

                @Override
                public void onError(Call call, Response response) {
                    showErrorToasts(call, response);
                }
            });
        }
    }
}
