package com.linxiangqu.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.BuyWithSendActivity;
import com.linxiangqu.shop.activity.FenLeiActivity;
import com.linxiangqu.shop.activity.LoginActivity;
import com.linxiangqu.shop.activity.SignInActivity;
import com.linxiangqu.shop.activity.TopicGoodsActivity;
import com.linxiangqu.shop.adapter.MainBottomRecyclerAdapter;
import com.linxiangqu.shop.adapter.MainFenLeiRecyclerAdapter;
import com.linxiangqu.shop.adapter.TouTiaoAdapter;
import com.linxiangqu.shop.bean.AppIndex;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.widget.verticalbannerview.VerticalBannerView;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class MainFragment extends BaseFragment implements IDefineView, View.OnClickListener {

    private View mView;
    private SliderLayout mSliderLayout;
    private PagerIndicator mPagerIndicator;
    private ImageButton mToolbar_back;
    private RelativeLayout mToolbar_RelativeLayout;
    private LinearLayout mToolbar_LinearLayout, mToolbar;
    private TextView mToolbar_tv;
    private LinearLayout cx, xsdz, mjs;
    private VerticalBannerView mVerticalBannerView;
    private TouTiaoAdapter mTouTiaoAdapter;
    private RecyclerView mRecyclerView, bottomRecyclerView;
    private MainFenLeiRecyclerAdapter mMainFenLeiRecyclerAdapter;
    private MainBottomRecyclerAdapter mMainBottomRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_main, container, false);
            initView();
            bindData();
            initToolbar();
            initData();
            initListener();
            setStatusBar(mToolbar);
        }
        return mView;
    }

    @Override
    public void initView() {
        mPagerIndicator = (PagerIndicator) mView.findViewById(R.id.custom_indicator);
        mVerticalBannerView = (VerticalBannerView) mView.findViewById(R.id.fragment_main_verticalbannerview);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_main_recyclerview);
        bottomRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_main_bottom_recyclerview);
        cx = (LinearLayout) mView.findViewById(R.id.fragment_main_cx);
        xsdz = (LinearLayout) mView.findViewById(R.id.fragment_main_xsdz);
        mjs = (LinearLayout) mView.findViewById(R.id.fragment_main_mjs);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        cx.setOnClickListener(this);
        xsdz.setOnClickListener(this);
        mjs.setOnClickListener(this);
    }

    @Override
    public void bindData() {
        OKHttpManager.getAppIndex(Config.APPINDEX, Config.getCacheUserId(mActivity), new SpotsCallBack<AppIndex>(mActivity) {
            @Override
            public void onSuccess(Call call, Response response, AppIndex appIndex) {
                if (appIndex.getStateCode() == 0) {
                    initSliderLayout(appIndex.getAdvListDTO());
                    mTouTiaoAdapter = new TouTiaoAdapter(getActivity(), appIndex.getGoodsGoodsTodayListDTO());
                    mVerticalBannerView.setAdapter(mTouTiaoAdapter);
                    mVerticalBannerView.start();
                    mMainBottomRecyclerAdapter = new MainBottomRecyclerAdapter(getActivity(), appIndex.getMallTopicDTOs());
                    bottomRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    bottomRecyclerView.setAdapter(mMainBottomRecyclerAdapter);
                    mMainFenLeiRecyclerAdapter = new MainFenLeiRecyclerAdapter(getActivity(), appIndex.getGoodsClassListDTO());
                    mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
                    mRecyclerView.setAdapter(mMainFenLeiRecyclerAdapter);

                    mMainFenLeiRecyclerAdapter.setmOnItemClickListener(new MainFenLeiRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, AppIndex.GoodsClassListDTOBean goodsClassListDTO, int position) {
                            if (Config.getCacheUserId(mActivity) == 33) {
                                openActivity(LoginActivity.class);
                            } else {
                                Intent intent = new Intent(getActivity(), FenLeiActivity.class);
                                intent.putExtra("id", goodsClassListDTO.getGcId());
                                startActivity(intent);
                            }
                        }
                    });
                    mMainBottomRecyclerAdapter.setmOnItemClickListener(new MainBottomRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onChildClick(View v, int position, AppIndex.MallTopicDTOsBean mallTopicDTOs) {
                            if (Config.getCacheUserId(mActivity) == 33) {
                                openActivity(LoginActivity.class);
                            } else {
                                Intent intent = new Intent(getActivity(), TopicGoodsActivity.class);
                                intent.putExtra("topicId", mallTopicDTOs.getTopicId());
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    showToasts(appIndex.getMsg());
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
    public void initToolbar() {
        mToolbar = (LinearLayout) mView.findViewById(R.id.toolbar);
        mToolbar.setBackground(getResources().getDrawable(R.color.main));
        mToolbar_back = (ImageButton) mView.findViewById(R.id.toolbar_back);
        mToolbar_back.setVisibility(View.GONE);
        mToolbar_RelativeLayout = (RelativeLayout) mView.findViewById(R.id.toolbar_qd);
        mToolbar_RelativeLayout.setVisibility(View.VISIBLE);
        mToolbar_tv = (TextView) mView.findViewById(R.id.toolbar_tv);
        mToolbar_tv.setVisibility(View.GONE);
        mToolbar_LinearLayout = (LinearLayout) mView.findViewById(R.id.toolbar_ss);
        mToolbar_LinearLayout.setVisibility(View.VISIBLE);
        mToolbar_RelativeLayout.setOnClickListener(this);
        mToolbar_LinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_qd:
                if (Config.getCacheUserId(mActivity) == 33) {
                    openActivity(LoginActivity.class);
                } else
                    openActivity(SignInActivity.class);
                break;
            case R.id.toolbar_ss:
                Toast.makeText(mActivity, "toolbar_ss", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_main_cx:
                Toast.makeText(mActivity, "fragment_main_cx", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_main_xsdz:
                Toast.makeText(mActivity, "fragment_main_xsdz", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_main_mjs:
                if (Config.getCacheUserId(mActivity) == 33) {
                    openActivity(LoginActivity.class);
                } else
                    openActivity(BuyWithSendActivity.class);
                break;
            default:
                break;
        }
    }

    public void initSliderLayout(final List<AppIndex.AdvListDTOBean> advListDTOs) {
        if (mSliderLayout == null) {
            mSliderLayout = (SliderLayout) mView.findViewById(R.id.slider);
            for (int i = 0; i < advListDTOs.size(); i++) {
                TextSliderView mTextSliderView = new TextSliderView(getActivity());
                mTextSliderView.description(advListDTOs.get(i).getAdvId() + "");
                mTextSliderView.image(Config.IP + advListDTOs.get(i).getAdvImg()).setScaleType(BaseSliderView.ScaleType.CenterInside);
                final int ii = i;
                mTextSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        showToasts(advListDTOs.get(ii).getAdvId() + "");
                    }
                });
                mSliderLayout.addSlider(mTextSliderView);
            }
        }
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setCustomIndicator(mPagerIndicator);
        mSliderLayout.setDuration(3000);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mSliderLayout != null)
            mSliderLayout.startAutoCycle();
//        if (mVerticalBannerView != null)
//            mVerticalBannerView.start();
    }

    @Override
    public void onStop() {
        if (mSliderLayout != null)
            mSliderLayout.stopAutoCycle();
//        if (mVerticalBannerView != null)
//            mVerticalBannerView.stop();
        super.onStop();
    }
}
