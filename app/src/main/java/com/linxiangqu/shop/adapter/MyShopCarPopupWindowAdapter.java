package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.shopcar.GetUserCartList;
import com.linxiangqu.shop.bean.SpecInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/8.
 */
public class MyShopCarPopupWindowAdapter extends RecyclerView.Adapter<MyShopCarPopupWindowAdapter.MyViewHolder> {
    private Context mContext;
    private List<GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean> mList;
    private MyShopCarPopupWindowAdapterTwo adapterTwo;
    private OnItemClickListener mOnItemClickListener;
    private GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean mGoodsSpecDTO;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean.GoodsSpecValueListDTOBean specInfo);
    }

    public MyShopCarPopupWindowAdapter(Context mContext, List<GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean> mList, GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean mGoodsSpecDTO) {
        this.mContext = mContext;
        this.mList = mList;
        this.mGoodsSpecDTO = mGoodsSpecDTO;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_myshopcar_bianji, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mList.get(position).getSpName());
        adapterTwo = new MyShopCarPopupWindowAdapterTwo(mContext, mList.get(position).getGoodsSpecValueListDTO(), mGoodsSpecDTO);
        holder.recycler.setLayoutManager(new GridLayoutManager(mContext, 2));
        holder.recycler.setAdapter(adapterTwo);
        adapterTwo.setmOnMyItemClickListener(new MyShopCarPopupWindowAdapterTwo.OnMyItemClickListener() {
            @Override
            public void OnMyItemClick(View view, int positions, GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean.GoodsSpecValueListDTOBean specInfo) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClick(view, positions, specInfo);
                }
            }
        });
    }

    public void show(List<GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        private RecyclerView recycler;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.fragment_myshopcar_bianji);
            recycler = (RecyclerView) itemView.findViewById(R.id.fragment_myshopcar_bianji_recyclerview);
        }
    }

    static class MyShopCarPopupWindowAdapterTwo extends RecyclerView.Adapter<MyShopCarPopupWindowAdapterTwo.MyViewHolder> {
        private Context mContext;
        private List<GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean.GoodsSpecValueListDTOBean> mList;
        private OnMyItemClickListener mOnMyItemClickListener;
        private GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean mGoodsSpecDTO;
        private int positions;
        private List<Boolean> isCheck;
        private int lastPosition = -1;
        private Gson gson = new Gson();

        public void setmOnMyItemClickListener(OnMyItemClickListener mOnMyItemClickListener) {
            this.mOnMyItemClickListener = mOnMyItemClickListener;
        }

        public interface OnMyItemClickListener {
            void OnMyItemClick(View view, int positions, GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean.GoodsSpecValueListDTOBean specInfo);
        }

        public MyShopCarPopupWindowAdapterTwo(Context mContext, List<GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean.GoodsSpecValueListDTOBean> mList, GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean mGoodsSpecDTO) {
            this.mContext = mContext;
            this.mList = mList;
            this.mGoodsSpecDTO = mGoodsSpecDTO;
            isCheck = new ArrayList<>();
            for (int i = 0; i < mList.size(); i++) {
                isCheck.add(i, false);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_myshopcar_bianji_item, parent, false);
            return new MyViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mList.get(position).getSpValue());
            List<SpecInfo> specInfo = gson.fromJson(mGoodsSpecDTO.getSpecGoodsSpec(), new TypeToken<List<SpecInfo>>() {
            }.getType());
            if (lastPosition == -1) {
                for (int i = 0; i < specInfo.size(); i++)
                    if (specInfo.get(i).getSpValue().equals(mList.get(position).getSpValue())) {
                        positions = i;
                        lastPosition = position;
                        isCheck.set(position, true);
                    }
            }
            if (isCheck.get(position)) {
                holder.tv.setBackgroundResource(R.mipmap.param_child_selected);
                lastPosition = position;
            } else
                holder.tv.setBackgroundResource(R.mipmap.paramchild_bg);
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.fragment_myshopcar_bianji_item_tv);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < isCheck.size(); i++) {
                            isCheck.set(i, false);
                        }
                        if (lastPosition != getLayoutPosition()) {
                            isCheck.set(getLayoutPosition(), true);
                            notifyItemChanged(lastPosition);
                            notifyItemChanged(getLayoutPosition());
                        }
                        if (mOnMyItemClickListener != null)
                            mOnMyItemClickListener.OnMyItemClick(tv, positions, mList.get(getLayoutPosition()));
                    }
                });
            }
        }
    }
}
