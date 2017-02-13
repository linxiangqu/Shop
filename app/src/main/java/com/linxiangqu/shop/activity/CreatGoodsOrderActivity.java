package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.addressandcitymanger.GetDefaultAddress;
import com.linxiangqu.shop.bean.SpecInfo;
import com.linxiangqu.shop.bean.good.GetGoodsInfo;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class CreatGoodsOrderActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private TextView mToolbartv;
    private ImageButton mToolbarback;

    private LinearLayout liner_one;
    private TextView shr, sjh, dz, goodsname, goodsparams, goodsprice, goodsnumber, goodsfee, buyermessage, sum_price, ok, nothing_address;
    private ImageView goodsimg, remove, add;
    private GetGoodsInfo.GoodsSpecDTOBean goodsSpecDTOBean;
    private String goodsInfo;
    private int goodnumber;
    private String goodname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatgoodsorder);
        initToolbar();
        initView();
        bindData();
        initData();
        initListener();
        setStatusBar();
    }

    @Override
    public void bindData() {
        goodsInfo = getIntent().getStringExtra("goodsInfo");
        goodsSpecDTOBean = new Gson().fromJson(goodsInfo, GetGoodsInfo.GoodsSpecDTOBean.class);
        goodnumber = getIntent().getIntExtra("goodsnumber", 1);
        goodname = getIntent().getStringExtra("goodsname");
        Picasso.with(this).load(Config.IP + goodsSpecDTOBean.getSpecGoodsColor()).fit().into(goodsimg);
        goodsname.setText(goodname);
        List<SpecInfo> specInfo = new Gson().fromJson(goodsSpecDTOBean.getSpecGoodsSpec(), new TypeToken<List<SpecInfo>>() {
        }.getType());
        String size_color = "";
        if (specInfo.size() != 0)
            for (int i = 0; i < specInfo.size(); i++) {
                if (i == 0)
                    size_color = size_color + specInfo.get(i).getSpValue();
                else
                    size_color = size_color + " " + specInfo.get(i).getSpValue();
            }
        goodsparams.setText(size_color);
        goodsnumber.setText(goodnumber + "");
        goodsprice.setText(Double.valueOf(goodnumber * goodsSpecDTOBean.getSpecGoodsPrice()) + "");
    }

    @Override
    public void initView() {
        liner_one = (LinearLayout) findViewById(R.id.linearlayout_sh);
        shr = (TextView) findViewById(R.id.linearlayout_sh_shr);
        sjh = (TextView) findViewById(R.id.linearlayout_sh_sjh);
        dz = (TextView) findViewById(R.id.linearlayout_dz);
        goodsname = (TextView) findViewById(R.id.goods_name);
        goodsparams = (TextView) findViewById(R.id.goods_params);
        goodsprice = (TextView) findViewById(R.id.goods_price);
        goodsnumber = (TextView) findViewById(R.id.goods_number);
        goodsfee = (TextView) findViewById(R.id.goods_fee);
        buyermessage = (TextView) findViewById(R.id.buyer_message);
        sum_price = (TextView) findViewById(R.id.price_sum);
        ok = (TextView) findViewById(R.id.submit);
        goodsimg = (ImageView) findViewById(R.id.goods_img);
        remove = (ImageView) findViewById(R.id.img_remove);
        add = (ImageView) findViewById(R.id.img_add);
        nothing_address = (TextView) findViewById(R.id.nothing_address);
    }

    @Override
    public void initData() {
        OKHttpManager.postGetDefaultAddress(Config.GET_DEFAULT_ADDRESS, Config.getCacheUserId(this), new SpotsCallBack<GetDefaultAddress>(this) {
            @Override
            public void onSuccess(Call call, Response response, GetDefaultAddress getDefaultAddress) {
                if (getDefaultAddress == null) {
                    liner_one.setVisibility(View.GONE);
                    nothing_address.setVisibility(View.VISIBLE);
                } else {
                    liner_one.setVisibility(View.VISIBLE);
                    nothing_address.setVisibility(View.GONE);
                    shr.setText(getDefaultAddress.getTrueName());
                    sjh.setText(getDefaultAddress.getMobPhone());
                    dz.setText(getDefaultAddress.getProvinceName() + getDefaultAddress.getCityName() + getDefaultAddress.getDistrictName() + getDefaultAddress.getAddress());
                }
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
        liner_one.setOnClickListener(this);
        remove.setOnClickListener(this);
        add.setOnClickListener(this);
        ok.setOnClickListener(this);
        nothing_address.setOnClickListener(this);
    }

    @Override
    public void initToolbar() {
        mToolbartv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbarback = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbartv.setText("确认订单");
        mToolbarback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.img_remove:
                if (goodnumber != 1) {
                    goodnumber--;
                    goodsnumber.setText(goodnumber + "");
                    goodsprice.setText(Double.valueOf(goodnumber * goodsSpecDTOBean.getSpecGoodsPrice()) + "");
                }
                break;
            case R.id.img_add:
                if (goodnumber < goodsSpecDTOBean.getSpecGoodsStorage()) {
                    goodnumber++;
                    goodsnumber.setText(goodnumber + "");
                    goodsprice.setText(Double.valueOf(goodnumber * goodsSpecDTOBean.getSpecGoodsPrice()) + "");
                }
                break;
            case R.id.submit:
                break;
            case R.id.linearlayout_sh:
                openActivity(AddressActivity.class);
                break;
            case R.id.nothing_address:
                openActivity(AddressActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
