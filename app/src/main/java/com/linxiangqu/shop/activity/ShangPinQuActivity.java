package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.platform.GetMallGoodsZoneInfo;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.utils.PicassoTransformationUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ShangPinQuActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private int goodsZoneId;
    private String name;
    private ImageView img;
    private TextView typename, sj, time, content;
    private SliderLayout sliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpinqu);
        initData();
        initToolbar();
        initView();
        bindData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initData() {
        goodsZoneId = getIntent().getIntExtra("goodsZoneId", -1);
        name = getIntent().getStringExtra("name");
    }

    @Override
    public void initView() {
        img = (ImageView) findViewById(R.id.activity_shangpinqu_img);
        typename = (TextView) findViewById(R.id.activity_shangpinqu_typename);
        sj = (TextView) findViewById(R.id.activity_shangpinqu_sj);
        time = (TextView) findViewById(R.id.activity_shangpinqu_time);
        content = (TextView) findViewById(R.id.activity_shangpinqu_content);
    }

    @Override
    public void bindData() {
        OKHttpManager.postGetMallGoodsZoneInfo(Config.GET_MALL_GOODS_ZONE_INFO, goodsZoneId, new SpotsCallBack<GetMallGoodsZoneInfo>(this) {
            @Override
            public void onSuccess(Call call, Response response, GetMallGoodsZoneInfo getMallGoodsZoneInfo) {
                if (getMallGoodsZoneInfo.getStateCode() == 0) {
                    Picasso.with(ShangPinQuActivity.this).load(Config.IP + getMallGoodsZoneInfo.getUserImg()).transform(new PicassoTransformationUtils()).error(R.mipmap.icon_others).fit().into(img);
                    typename.setText(getMallGoodsZoneInfo.getGoodsZoneTypeName());
                    sj.setText(getMallGoodsZoneInfo.getGoodsZoneMobile());
                    time.setText(getMallGoodsZoneInfo.getGoodsZoneTime());
                    content.setText(getMallGoodsZoneInfo.getGoodsZoneContent());
                    initSliderLayout(getMallGoodsZoneInfo.getPicture());
                } else
                    showToasts(getMallGoodsZoneInfo.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    private void initSliderLayout(List<GetMallGoodsZoneInfo.PictureBean> picture) {
        if (picture.size() == 0) {
            sliderLayout = (SliderLayout) findViewById(R.id.activity_shangpinqu_slider);
            sliderLayout.setVisibility(View.GONE);
        } else {
            if (sliderLayout == null) {
                sliderLayout = (SliderLayout) findViewById(R.id.activity_shangpinqu_slider);
                for (int i = 0; i < picture.size(); i++) {
                    TextSliderView mTextSliderView = new TextSliderView(this);
                    mTextSliderView.image(Config.IP + picture.get(i).getImgPath()).setScaleType(BaseSliderView.ScaleType.CenterInside);
                    sliderLayout.addSlider(mTextSliderView);
                }
            }
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText(name);
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
