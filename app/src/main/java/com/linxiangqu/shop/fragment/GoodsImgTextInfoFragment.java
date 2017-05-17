package com.linxiangqu.shop.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.common.base.BaseFragment;
import com.linxiangqu.shop.widget.vertical.VerticalWebView;

@SuppressLint("ValidFragment")
public class GoodsImgTextInfoFragment extends BaseFragment {

    private VerticalWebView webview;
    private View mView;
    private String goodsImageText;
    private boolean hasInited = false;

    public GoodsImgTextInfoFragment(String goodsImageText) {
        this.goodsImageText = goodsImageText;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_goods_info_webview, container, false);
            webview = (VerticalWebView) mView.findViewById(R.id.webView);
            initView();
        }
        return mView;
    }

    public void initView() {
        if (null != webview) {
            hasInited = true;
            webview.getSettings().setDefaultFontSize(17);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setSupportZoom(true);
            webview.getSettings().setBuiltInZoomControls(false);
            webview.getSettings().setUseWideViewPort(true);
            webview.getSettings().setLoadWithOverviewMode(true);
            webview.loadDataWithBaseURL(Config.IP, goodsImageText, "text/html", "utf-8", "");
        }
    }

    public void goTop() {
        webview.goTop();
    }
}
