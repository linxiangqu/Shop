package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.platform.GetMallDynamicDetail;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.squareup.picasso.Picasso;

import okhttp3.Call;
import okhttp3.Response;

public class DongTaiActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private String title;
    private int dynamicId;
    private ImageView img;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongtai);
        initData();
        initToolbar();
        initView();
        bindData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initData() {
        title = getIntent().getStringExtra("title");
        dynamicId = getIntent().getIntExtra("dynamicId", -1);
    }

    @Override
    public void initView() {
        img = (ImageView) findViewById(R.id.activity_dongtai_img);
        content = (TextView) findViewById(R.id.activity_dongtai_content);
    }

    @Override
    public void bindData() {
        OKHttpManager.postGetMallDynamicDetail(Config.GET_MALL_DYNAMIC_DETAIL, dynamicId, new SpotsCallBack<GetMallDynamicDetail>(this) {
            @Override
            public void onSuccess(Call call, Response response, GetMallDynamicDetail getMallDynamicDetail) {
                if (getMallDynamicDetail.getStateCode() == 0) {
                    Picasso.with(DongTaiActivity.this).load(Config.IP + getMallDynamicDetail.getDynamicImg()).error(R.mipmap.example_about_us_sj).into(img);
                    content.setText(getMallDynamicDetail.getDynamicContent());
                } else
                    showToasts(getMallDynamicDetail.getMsg());
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
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText(title);
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
