package com.linxiangqu.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.good.GetGoodsInfo;
import com.linxiangqu.shop.common.base.BaseFragment;
import com.linxiangqu.shop.widget.vertical.VerticalRecyclerView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class GoodsInfoFragment extends BaseFragment {

    private View mView;
    private List<GetGoodsInfo.GoodsIssueAttributeDTOBean> mList = new ArrayList<>();
    private VerticalRecyclerView recyclerView;
    private Context context;
    private String title;

//    public static GoodsInfoFragment newInstance(Context context, String title, List<GetGoodsInfo.GoodsIssueAttributeDTOBean> mList) {
//        GoodsInfoFragment fragment = new GoodsInfoFragment();
//        fragment.context = context;
//        fragment.mList = mList;
//        for (int i = 0; i < 20; i++) {
//            GetGoodsInfo.GoodsIssueAttributeDTOBean a = new GetGoodsInfo.GoodsIssueAttributeDTOBean();
//            a.setAttrValueName(i + "11");
//            a.setAttrName(i + "12");
//            mList.add(a);
//        }
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            title = getArguments().getString(Config.KEY_TITLE);
//        }
//    }

    public GoodsInfoFragment(List<GetGoodsInfo.GoodsIssueAttributeDTOBean> mList) {
        this.mList = mList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_goods_info_spxq, container, false);
            recyclerView = (VerticalRecyclerView) mView.findViewById(R.id.fragment_goods_info_spxq_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            recyclerView.setAdapter(new MyAdapter(mList));
        }
        return mView;
    }

    public void goTop() {
        recyclerView.goTop();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.SimpleViewHolder> {

        private List<GetGoodsInfo.GoodsIssueAttributeDTOBean> mList;

        public MyAdapter(List<GetGoodsInfo.GoodsIssueAttributeDTOBean> mList) {
            this.mList = mList;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(mActivity).inflate(R.layout.fragment_goods_info_spxq_item, parent, false);
            return new SimpleViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.spxq_attrName.setText(mList.get(position).getAttrName());
            holder.spxq_attrValueName.setText(mList.get(position).getAttrValueName());
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        protected class SimpleViewHolder extends RecyclerView.ViewHolder {

            private TextView spxq_attrName, spxq_attrValueName;

            public SimpleViewHolder(View itemView) {
                super(itemView);
                spxq_attrName = (TextView) itemView.findViewById(R.id.fragment_goods_info_spxq_item_attrName);
                spxq_attrValueName = (TextView) itemView.findViewById(R.id.fragment_goods_info_spxq_item_attrValueName);
            }
        }
    }
}
