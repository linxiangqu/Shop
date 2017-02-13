package com.linxiangqu.shop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;

/**
 * Created by linxiangqu on 2016/8/12.
 */
public class TiChengFragment extends BaseFragment implements View.OnClickListener, IDefineView {
    private Context mContext;
    private View mView;
    private String money;
    private TextView money_tc;

    public static TiChengFragment newInstance(Context context, String money) {
        TiChengFragment fragment = new TiChengFragment();
        fragment.mContext = context;
        Bundle bundle = new Bundle();
        bundle.putString(Config.KEY_TITLE, money);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            money = getArguments().getString(Config.KEY_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.from(mActivity).inflate(R.layout.fragment_myassets_ticheng, container, false);
            initView();
            initData();
            bindData();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        money_tc = (TextView) mView.findViewById(R.id.fragment_myassets_ticheng_money);
    }

    @Override
    public void initData() {
        money_tc.setText("￥" + money);
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
    public void onClick(View view) {

    }
}
