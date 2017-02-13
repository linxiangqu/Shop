package com.linxiangqu.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.FaBuActivity;
import com.linxiangqu.shop.adapter.ShangPinQuRecyclerAdapter;
import com.linxiangqu.shop.bean.platform.GetMallGoodsZoneList;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by linxiangqu on 2016/9/20.
 */

@SuppressLint("ValidFragment")
public class ShangPinQuFragment extends BaseFragment implements IDefineView, View.OnClickListener {

    private View mView;
    private Context mContext;
    private RecyclerView recyclerView;
    private TextView ss;
    private Button fb;
    private ShangPinQuRecyclerAdapter adapter;

    public ShangPinQuFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_myplatform_shangpinqu, container, false);
            initView();
            initData();
            bindData();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) mView.findViewById(R.id.shangpinqu_recycler);
        ss = (TextView) mView.findViewById(R.id.shangpinqu_ss);
        fb = (Button) mView.findViewById(R.id.shangpinqu_fb);
    }

    @Override
    public void initData() {
        OKHttpManager.postGetMallGoodsZoneList(Config.GET_MALL_GOODS_ZONE_LIST, Config.getCacheUserId(mActivity), 1, 20, 0, new SpotsCallBack<GetMallGoodsZoneList>(mActivity) {
            @Override
            public void onSuccess(Call call, Response response, GetMallGoodsZoneList getMallGoodsZoneList) {
                if (getMallGoodsZoneList.getStateCode() == 0) {
                    adapter = new ShangPinQuRecyclerAdapter(mActivity, getMallGoodsZoneList.getMallGoodsZoneListinfoDTO());
                    recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                    recyclerView.setAdapter(adapter);
                } else
                    showToasts(getMallGoodsZoneList.getMsg());
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
        ss.setOnClickListener(this);
        fb.setOnClickListener(this);
    }

    @Override
    public void bindData() {

    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shangpinqu_ss:
                break;
            case R.id.shangpinqu_fb:
                openActivity(FaBuActivity.class);
                break;
            default:
                break;
        }
    }
}