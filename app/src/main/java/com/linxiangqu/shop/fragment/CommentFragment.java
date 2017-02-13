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
import android.widget.ImageView;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.good.GetGoodsInfo;
import com.linxiangqu.shop.common.base.BaseFragment;
import com.linxiangqu.shop.widget.vertical.VerticalRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class CommentFragment extends BaseFragment {

    private View mView;
    private List<GetGoodsInfo.GoodsEvaluationDTOBean> mList = new ArrayList<>();
    private VerticalRecyclerView recyclerView;

    public CommentFragment() {
    }

    public CommentFragment(List<GetGoodsInfo.GoodsEvaluationDTOBean> mList) {
        this.mList = mList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_goods_info_comment, container, false);
            recyclerView = (VerticalRecyclerView) mView.findViewById(R.id.fragment_goods_info_comment_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            recyclerView.setAdapter(new MyAdapter(mList));
        }
        return mView;
    }

    public void goTop() {
        recyclerView.goTop();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.SimpleViewHolder> {

        private List<GetGoodsInfo.GoodsEvaluationDTOBean> mList;

        public MyAdapter(List<GetGoodsInfo.GoodsEvaluationDTOBean> mList) {
            this.mList = mList;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(mActivity).inflate(R.layout.fragment_goods_info_comment_item, parent, false);
            return new SimpleViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.comment_name.setText(mList.get(position).getUserName());
            holder.comment_pj.setText(mList.get(position).getDesF());
            holder.comment_sj.setText(mList.get(position).getTime());
            Picasso.with(mActivity).load(Config.IP + mList.get(position).getUserImg()).fit().into(holder.comment_img);
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        protected class SimpleViewHolder extends RecyclerView.ViewHolder {

            private ImageView comment_img;
            private TextView comment_name, comment_pj, comment_sj;

            public SimpleViewHolder(View itemView) {
                super(itemView);
                comment_img = (ImageView) itemView.findViewById(R.id.fragment_goods_info_comment_item_img);
                comment_name = (TextView) itemView.findViewById(R.id.fragment_goods_info_comment_item_name);
                comment_pj = (TextView) itemView.findViewById(R.id.fragment_goods_info_comment_item_pl);
                comment_sj = (TextView) itemView.findViewById(R.id.fragment_goods_info_comment_item_tiem);
            }
        }
    }
}
