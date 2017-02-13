package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.addressandcitymanger.GetAddressListByCustomer;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/25.
 */
public class AddressRecyclerAdapter extends SwipeMenuAdapter<AddressRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<GetAddressListByCustomer> mList;
    private List<Boolean> isCheck = new ArrayList<>();
    private boolean isBianJi = false;
    private ItemOnClickListener mItemOnClickListener;
    private int defaultAddressPosition = -1;

    public AddressRecyclerAdapter(Context mContext, List<GetAddressListByCustomer> mList) {
        this.mContext = mContext;
        this.mList = mList;
        for (int i = 0; i < mList.size(); i++) {
            isCheck.add(i, false);
        }
    }

    public void setmItemOnClickListener(ItemOnClickListener mItemOnClickListener) {
        this.mItemOnClickListener = mItemOnClickListener;
    }

    public interface ItemOnClickListener {
        void ItemClick(View v, GetAddressListByCustomer getAddressListByCustomer, int position);
    }

    public void bianJi(boolean isBianJi) {
        this.isBianJi = isBianJi;
        if (isCheck.size() != 0)
            notifyItemRangeChanged(0, isCheck.size());
    }

    public void setDefaultAddress(int position) {
        mList.get(position).setIsDefault(1);
        if (defaultAddressPosition != -1) {
            mList.get(defaultAddressPosition).setIsDefault(0);
            notifyItemChanged(defaultAddressPosition);
        }
        notifyItemChanged(position);
        defaultAddressPosition = position;
    }

    public void deleteAddress(int position) {
        mList.remove(position);
        notifyItemChanged(position);
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(mContext).inflate(R.layout.activity_address_item, parent, false);
    }

    @Override
    public MyViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new MyViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.shr.setText("收货人：" + mList.get(position).getTrueName());
        holder.sjh.setText(mList.get(position).getMobPhone());
        if (mList.get(position).getIsDefault() == 1) {
            holder.address.setText("(默认地址)" + mList.get(position).getProvinceName() + mList.get(position).getCityName() + mList.get(position).getDistrictName() + mList.get(position).getAddress());
            defaultAddressPosition = position;
        } else
            holder.address.setText(mList.get(position).getProvinceName() + mList.get(position).getCityName() + mList.get(position).getDistrictName() + mList.get(position).getAddress());
        if (!isBianJi) {
            holder.bianji.setVisibility(View.GONE);
            holder.noCheck.setVisibility(View.VISIBLE);
            if (mList.get(position).getIsDefault() == 1)
                holder.noCheck.setBackgroundResource(R.mipmap.gouxuan_sj);
            else
                holder.noCheck.setBackgroundResource(R.mipmap.goubuxuan_sj);
        } else {
            holder.noCheck.setVisibility(View.GONE);
            holder.bianji.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView shr, sjh, address;
        private ImageView noCheck, bianji;


        public MyViewHolder(View itemView) {
            super(itemView);
            shr = (TextView) itemView.findViewById(R.id.activity_address_item_shr);
            sjh = (TextView) itemView.findViewById(R.id.activity_address_item_sjh);
            address = (TextView) itemView.findViewById(R.id.activity_address_item_address);
            noCheck = (ImageView) itemView.findViewById(R.id.activity_address_item_nocheck);
            bianji = (ImageView) itemView.findViewById(R.id.activity_address_item_bianji);
            noCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemOnClickListener != null)
                        mItemOnClickListener.ItemClick(v, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            bianji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemOnClickListener != null)
                        mItemOnClickListener.ItemClick(v, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
        }
    }
}
