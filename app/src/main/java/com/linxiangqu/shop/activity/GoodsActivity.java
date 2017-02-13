package com.linxiangqu.shop.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.MyFragmentPagerAdapter;
import com.linxiangqu.shop.bean.shopcar.GetUserCartList;
import com.linxiangqu.shop.bean.good.GetGoodsInfo;
import com.linxiangqu.shop.bean.shopcar.SaveGoodsToCart;
import com.linxiangqu.shop.bean.user.AddCollection;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.fragment.CommentFragment;
import com.linxiangqu.shop.fragment.GoodsImgTextInfoFragment;
import com.linxiangqu.shop.fragment.GoodsInfoFragment;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.widget.MyPopupWindowGoodsInfo;
import com.linxiangqu.shop.widget.vertical.VerticalScrollView;
import com.linxiangqu.shop.widget.vertical.VerticalSlide;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class GoodsActivity extends AppCompatActivity implements IDefineView, View.OnClickListener {

    private Toast mToast;
    private int goodsId, fHeight;
    private VerticalSlide slide;
    private VerticalScrollView scrollView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentPagerAdapter adapter;
    private SliderLayout sliderLayout;
    private LinearLayout sc, jrgwc, fx, pl;
    private RelativeLayout fl, wypl, main;
    private TextView ljgm, goodsname, goodsprice_one, goodsprice_two, yx, kdf, xx_tv, pl_tv, pl_name, pl_sj, pl_nothing, song;
    private ImageView back, totop, xx_img, pl_img;
    private GoodsImgTextInfoFragment goodsImgTextInfoFragment;
    private GoodsInfoFragment goodsInfoFragment;
    private CommentFragment commentFragment;
    private MyPopupWindowGoodsInfo myPopupWindow = null;
    private GetGoodsInfo goodsInfo;
    private AnimatorSet showAnimEnter, showAnimExit;
    private long first, end;
    private final int time = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        main = (RelativeLayout) findViewById(R.id.relativelayout);
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                fHeight = main.getHeight();
                main.getViewTreeObserver()
                        .removeOnGlobalLayoutListener(this);
            }
        });
        bindData();
        initView();
        initData();
        initListener();
    }

    @Override
    public void bindData() {
        goodsId = getIntent().getIntExtra("goodsId", -1);
        OKHttpManager.getGetGoodsInfo(Config.GET_GOODS_INFO, goodsId, new SpotsCallBack<GetGoodsInfo>(this) {
            @Override
            public void onSuccess(Call call, Response response, GetGoodsInfo getGoodsInfo) {
                if (getGoodsInfo.getStateCode() == 0) {
                    goodsInfo = getGoodsInfo;
                    initSliderLayout(getGoodsInfo.getGoodsImageMore());
                    goodsname.setText(getGoodsInfo.getGoodsName());
                    goodsprice_one.setText("￥ " + getGoodsInfo.getGoodsStorePrice() + "");
                    goodsprice_two.setText("￥ " + getGoodsInfo.getGoodsStorePriceInterval());
                    yx.setText(getGoodsInfo.getSalenum() + "");
                    kdf.setText(getGoodsInfo.getGoodsClick() + "");
                    if (getGoodsInfo.isGoodsIsbuy()) {
                        song.setVisibility(View.VISIBLE);
                        song.setText("【赠送】" + getGoodsInfo.getSongName());
                    }
                    if (getGoodsInfo.getGoodsEvaluationDTO().size() == 0)
                        pl_nothing.setVisibility(View.VISIBLE);
                    else if (getGoodsInfo.getGoodsEvaluationDTO().size() > 0) {
                        pl.setVisibility(View.VISIBLE);
                        Picasso.with(GoodsActivity.this).load(Config.IP + getGoodsInfo.getGoodsEvaluationDTO().get(0).getUserImg()).fit().into(pl_img);
                        pl_name.setText(getGoodsInfo.getGoodsEvaluationDTO().get(0).getUserName());
                        pl_tv.setText(getGoodsInfo.getGoodsEvaluationDTO().get(0).getDesF());
                        pl_sj.setText(getGoodsInfo.getGoodsEvaluationDTO().get(0).getTime());
                    }

                    tabLayout = (TabLayout) findViewById(R.id.activity_goods_tablayout);
                    viewPager = (ViewPager) findViewById(R.id.activity_goods_viewpager);
                    goodsImgTextInfoFragment = new GoodsImgTextInfoFragment(getGoodsInfo.getGoodsImageText());
                    goodsInfoFragment = new GoodsInfoFragment(getGoodsInfo.getGoodsIssueAttributeDTO());
//                    goodsInfoFragment = new GoodsInfoFragment();
                    commentFragment = new CommentFragment(getGoodsInfo.getGoodsEvaluationDTO());
                    adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), GoodsActivity.this);
                    adapter.addFragment(goodsImgTextInfoFragment, "图文详情");
                    adapter.addFragment(goodsInfoFragment, "产品参数");
//                    adapter.addFragment(goodsInfoFragment.newInstance(GoodsActivity.this, "产品参数", getGoodsInfo.getGoodsIssueAttributeDTO()), "产品参数");
                    adapter.addFragment(commentFragment, "用户评价");
                    tabLayout.setTabMode(TabLayout.MODE_FIXED);
                    viewPager.setAdapter(adapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                call.cancel();
            }
        });
    }

    @Override
    public void initView() {
        slide = (VerticalSlide) findViewById(R.id.activity_goods_verticallslide);
        scrollView = (VerticalScrollView) findViewById(R.id.activity_goods_page_one);
        back = (ImageView) findViewById(R.id.activity_goods_back);
        totop = (ImageView) findViewById(R.id.activity_goods_totop);

        totop.setVisibility(View.GONE);

        sc = (LinearLayout) findViewById(R.id.activity_goods_sc);
        jrgwc = (LinearLayout) findViewById(R.id.activity_goods_jrgwc);
        ljgm = (TextView) findViewById(R.id.activity_goods_ljgm);

        fx = (LinearLayout) findViewById(R.id.activity_goods_fx);
        fl = (RelativeLayout) findViewById(R.id.activity_goods_cs);
        wypl = (RelativeLayout) findViewById(R.id.activity_goods_wypl);
        goodsname = (TextView) findViewById(R.id.activity_goods_goodname);
        goodsprice_one = (TextView) findViewById(R.id.activity_goods_price_one);
        goodsprice_two = (TextView) findViewById(R.id.activity_goods_price_two);
        yx = (TextView) findViewById(R.id.activity_goods_yx);
        kdf = (TextView) findViewById(R.id.activity_goods_kdf);
        xx_tv = (TextView) findViewById(R.id.activity_goods_xx_tv);
        xx_img = (ImageView) findViewById(R.id.activity_goods_xx_img);
        song = (TextView) findViewById(R.id.goods_isbuywithsend);

        pl = (LinearLayout) findViewById(R.id.activity_goods_pl);
        pl_nothing = (TextView) findViewById(R.id.activity_goods_pl_no_comment);
        pl_img = (ImageView) findViewById(R.id.activity_goods_pl_img);
        pl_name = (TextView) findViewById(R.id.activity_goods_pl_name);
        pl_tv = (TextView) findViewById(R.id.activity_goods_pl_tv);
        pl_sj = (TextView) findViewById(R.id.activity_goods_pl_sj);

        slide.setOnShowNextPageListener(new VerticalSlide.OnShowNextPageListener() {
            @Override
            public void onShowNextPage() {
                xx_tv.setText("向下拖动返回宝贝详情");
                xx_img.setBackgroundResource(R.mipmap.arrow_buttom);
                totop.setVisibility(View.VISIBLE);
            }
        });

        slide.setOnShowLastPageListener(new VerticalSlide.OnShowLastPageListener() {
            @Override
            public void onShowLastPage() {
                xx_tv.setText("向上拖动查看图文详情");
                xx_img.setBackgroundResource(R.mipmap.arrow_top);
                totop.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        totop.setOnClickListener(this);

        sc.setOnClickListener(this);
        jrgwc.setOnClickListener(this);
        ljgm.setOnClickListener(this);

        fx.setOnClickListener(this);
        fl.setOnClickListener(this);
        wypl.setOnClickListener(this);
    }

    @Override
    public void initToolbar() {

    }

    public void initSliderLayout(final List<String> img) {
        if (sliderLayout == null) {
            sliderLayout = (SliderLayout) findViewById(R.id.activity_goods_slider);
            for (int i = 0; i < img.size(); i++) {
                TextSliderView mTextSliderView = new TextSliderView(this);
                mTextSliderView.image(Config.IP + img.get(i)).setScaleType(BaseSliderView.ScaleType.CenterInside);
                sliderLayout.addSlider(mTextSliderView);
            }
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(GoodsActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(GoodsActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(GoodsActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_goods_back:
                finish();
                break;
            case R.id.activity_goods_totop:
                if (viewPager.getCurrentItem() == 0) {
                    goodsImgTextInfoFragment.goTop();
                }
                if (viewPager.getCurrentItem() == 1) {
                    goodsInfoFragment.goTop();
                }
                if (viewPager.getCurrentItem() == 2) {
                    commentFragment.goTop();
                }
                viewPager.setCurrentItem(0);
                slide.goTop(new VerticalSlide.OnGoTopListener() {
                    @Override
                    public void goTop() {
                        xx_tv.setText("向上拖动查看图文详情");
                        xx_img.setBackgroundResource(R.mipmap.arrow_top);
                        totop.setVisibility(View.GONE);
                        scrollView.goTop();
                    }
                });
                break;
            case R.id.activity_goods_sc:
                collection();
                break;
            case R.id.activity_goods_jrgwc:
                first = Calendar.getInstance().getTimeInMillis();
                jrgwc.setClickable(false);
                initShowAnim();
                initPopupWindow(goodsInfo, 0);
                break;
            case R.id.activity_goods_ljgm:
                first = Calendar.getInstance().getTimeInMillis();
                ljgm.setClickable(false);
                initShowAnim();
                initPopupWindow(goodsInfo, 1);
                break;
            case R.id.activity_goods_fx:
                new ShareAction(this).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withTitle(Config.title)
                        .withText(Config.text + "——来自友盟分享面板")
                        .withMedia(new UMImage(GoodsActivity.this, Config.imageurl))
                        .withTargetUrl("https://wsq.umeng.com/")
                        .setCallback(umShareListener)
                        .open();
                break;
            case R.id.activity_goods_cs:
                slide.goTop(new VerticalSlide.OnGoTopListener() {
                    @Override
                    public void goTop() {
                        xx_tv.setText("向下拖动查看图文详情");
                        xx_img.setBackgroundResource(R.mipmap.arrow_buttom);
                        totop.setVisibility(View.VISIBLE);
                        viewPager.setCurrentItem(1);
                    }
                });
                break;
            case R.id.activity_goods_wypl:
                slide.goTop(new VerticalSlide.OnGoTopListener() {
                    @Override
                    public void goTop() {
                        xx_tv.setText("向下拖动查看图文详情");
                        xx_img.setBackgroundResource(R.mipmap.arrow_buttom);
                        totop.setVisibility(View.VISIBLE);
                        viewPager.setCurrentItem(2);
                    }
                });
                break;
            default:
                break;
        }
    }

    private void initPopupWindow(final GetGoodsInfo getGoodsInfo, final int str) {
        if (getGoodsInfo != null) {
            myPopupWindow = new MyPopupWindowGoodsInfo(this, getGoodsInfo).showPop();
            myPopupWindow.setOnMediaClick(new MyPopupWindowGoodsInfo.OnMediaClick() {
                @Override
                public void onOkClick(GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTO, PopupWindow mPopupWindow, int goodnumber) {
                    if (str == 0) {
                        OKHttpManager.postSaveGoodsToCart(Config.SAVE_GOODS_TO_CART, Config.getCacheUserId(GoodsActivity.this), goodsId, goodsSpecDTO.getSpecId(), goodnumber, new SpotsCallBack<SaveGoodsToCart>(GoodsActivity.this) {
                            @Override
                            public void onSuccess(Call call, Response response, SaveGoodsToCart saveGoodsToCart) {
                                if (saveGoodsToCart.getStateCode() == 2204)
                                    showToasts(saveGoodsToCart.getMsg());
                                call.cancel();
                            }

                            @Override
                            public void onError(Call call, Response response) {
                                showErrorToasts(call, response);
                            }
                        });
                    } else if (str == 1) {
                        Intent intent = new Intent(GoodsActivity.this, CreatGoodsOrderActivity.class);
                        intent.putExtra("goodsInfo", new Gson().toJson(goodsSpecDTO));
                        intent.putExtra("goodsnumber", goodnumber);
                        intent.putExtra("goodsname", getGoodsInfo.getGoodsName());
                        startActivity(intent);
                    }
                    initHiddenAnim();
                    mPopupWindow.dismiss();
                    myPopupWindow = null;
                    jrgwc.setClickable(true);
                    ljgm.setClickable(true);
                }
            });
            myPopupWindow.setmOnTouchBack(new MyPopupWindowGoodsInfo.OnTouchBack() {
                @Override
                public void onTouch(View v, MotionEvent event, PopupWindow mPopupWindow) {
                    end = Calendar.getInstance().getTimeInMillis();
                    if ((end - first) > time) {
                        if (null != mPopupWindow && mPopupWindow.isShowing()) {
                            initHiddenAnim();
                            mPopupWindow.dismiss();
                            myPopupWindow = null;
                        }
                        jrgwc.setClickable(true);
                        ljgm.setClickable(true);
                    }
                }
            });
        }
    }

    private void collection() {
        OKHttpManager.postAddCollection(Config.ADD_COLLECTION, Config.getCacheUserId(GoodsActivity.this), goodsId, new SpotsCallBack<AddCollection>(GoodsActivity.this) {
            @Override
            public void onSuccess(Call call, Response response, AddCollection addCollection) {
                showToasts(addCollection.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    public void showToasts(final CharSequence text) {
        if (mToast != null) {
            mToast.setText(text);
            mToast.show();
            return;
        }
        mToast = mToast.makeText(this, text, mToast.LENGTH_LONG);
        mToast.show();
    }

    public void showErrorToasts(Call call, Response response) {
        CharSequence text = Config.getCodeMsg(this, response.code());
        CharSequence content = TextUtils.isEmpty(text) ? "程序出错，请稍候再试!" : text;
        if (mToast != null) {
            mToast.setText(content);
            mToast.show();
            return;
        }
        mToast = mToast.makeText(this, content, mToast.LENGTH_LONG);
        mToast.show();
        call.cancel();
    }

    private void initShowAnim() {
        ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(main, "scaleX", 1.0f, 0.8f);
        fViewScaleXAnim.setDuration(550);
        ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(main, "scaleY", 1.0f, 0.8f);
        fViewScaleYAnim.setDuration(550);
        ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(main, "alpha", 1.0f, 0.5f);
        fViewAlphaAnim.setDuration(550);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(main, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(400);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(main, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(350);
        fViewResumeAnim.setStartDelay(400);
        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(main, "translationY", 0, -0.1f * fHeight);
        fViewTransYAnim.setDuration(550);
//        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(secondView, "translationY", sHeight, 0);
//        sViewTransYAnim.setDuration(350);
//        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                secondView.setVisibility(View.VISIBLE);
//            }
//        });
        showAnimEnter = new AnimatorSet();
        showAnimEnter.playTogether(fViewScaleXAnim, fViewRotationXAnim, fViewResumeAnim, fViewTransYAnim, fViewAlphaAnim, fViewScaleYAnim);
        showAnimEnter.start();
    }

    private void initHiddenAnim() {
        ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(main, "scaleX", 0.8f, 1.0f);
        fViewScaleXAnim.setDuration(400);
        ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(main, "scaleY", 0.8f, 1.0f);
        fViewScaleYAnim.setDuration(400);
        ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(main, "alpha", 0.5f, 1.0f);
        fViewAlphaAnim.setDuration(400);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(main, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(250);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(main, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(200);
        fViewResumeAnim.setStartDelay(250);
        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(main, "translationY", -0.1f * fHeight, 0);
        fViewTransYAnim.setDuration(400);
//        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(secondView, "translationY", 0, sHeight);
//        sViewTransYAnim.setDuration(350);
//        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                secondView.setVisibility(View.INVISIBLE);
//            }
//        });
        showAnimExit = new AnimatorSet();
        showAnimExit.playTogether(fViewScaleXAnim, fViewRotationXAnim, fViewResumeAnim, fViewTransYAnim, fViewAlphaAnim, fViewScaleYAnim);
        showAnimExit.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }

}
