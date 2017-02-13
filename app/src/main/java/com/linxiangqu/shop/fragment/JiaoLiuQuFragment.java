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

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.JiaoLiuQuRecyclerAdapter;
import com.linxiangqu.shop.bean.platform.GetMallBbsList;
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
public class JiaoLiuQuFragment extends BaseFragment implements IDefineView, View.OnClickListener {

    private View mView;
    private Context mContext;
    private RecyclerView recyclerView;
    private JiaoLiuQuRecyclerAdapter adapter;

    public JiaoLiuQuFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_myplatform_jiaoliuqu, container, false);
            initView();
            initData();
            bindData();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) mView.findViewById(R.id.jiaoliuqu_recyler);
    }

    @Override
    public void initData() {
        OKHttpManager.postGetMallBbsList(Config.GET_MALL_BBS_LIST, 1, 20, new SpotsCallBack<GetMallBbsList>(mActivity) {
            @Override
            public void onSuccess(Call call, Response response, GetMallBbsList getMallBbsList) {
                if (getMallBbsList.getStateCode() == 0) {
                    adapter = new JiaoLiuQuRecyclerAdapter(mActivity, getMallBbsList.getMallBbsDTO());
                    recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                    recyclerView.setAdapter(adapter);
                } else
                    showToasts(getMallBbsList.getMsg());
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
    public void bindData() {

    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void onClick(View v) {

    }
}
