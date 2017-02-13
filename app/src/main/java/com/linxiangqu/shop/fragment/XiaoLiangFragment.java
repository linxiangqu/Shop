package com.linxiangqu.shop.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.GoodsActivity;
import com.linxiangqu.shop.adapter.XiaoLiangRecyclerAdapter;
import com.linxiangqu.shop.bean.good.GetGoodsList;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/4.
 */
public class XiaoLiangFragment extends BaseFragment implements IDefineView, View.OnClickListener {

    private View mView;
    private String title;
    private Context context;
    private RecyclerView mRecyclerView;
    private XiaoLiangRecyclerAdapter mXiaoLiangRecyclerAdapter;
    private List<GetGoodsList.MallGoodsDTOBean> guessYouLikeLists;

    public static XiaoLiangFragment newInstance(Context context, String title, List<GetGoodsList.MallGoodsDTOBean> guessYouLikeLists) {
        XiaoLiangFragment fragment = new XiaoLiangFragment();
        fragment.context = context;
        fragment.guessYouLikeLists = guessYouLikeLists;
        Bundle args = new Bundle();
        args.putString(Config.KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(Config.KEY_TITLE);
        }
        if (guessYouLikeLists == null) {
            guessYouLikeLists = new ArrayList<>();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.from(mActivity).inflate(R.layout.fragment_fenlei_xiaoliang, container, false);
            initView();
            initData();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_fenlei_xiaoliang_recyclerview);
    }

    @Override
    public void initData() {
        mXiaoLiangRecyclerAdapter = new XiaoLiangRecyclerAdapter(getActivity(), guessYouLikeLists);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRecyclerView.setAdapter(mXiaoLiangRecyclerAdapter);
    }

    @Override
    public void initListener() {
        mXiaoLiangRecyclerAdapter.setOnItemClickListener(new XiaoLiangRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemChild(View view, int position, GetGoodsList.MallGoodsDTOBean guessYouLikeList) {
                Intent intent = new Intent(mActivity, GoodsActivity.class);
                intent.putExtra("goodsId", guessYouLikeList.getGoodsId());
                startActivity(intent);
            }
        });
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
