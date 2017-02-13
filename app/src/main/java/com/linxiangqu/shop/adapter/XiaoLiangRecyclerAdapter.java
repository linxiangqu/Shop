package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.good.GetGoodsList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/8/5.
 */
public class XiaoLiangRecyclerAdapter extends RecyclerView.Adapter<XiaoLiangRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<GetGoodsList.MallGoodsDTOBean> mList;
    private OnItemClickListener onItemClickListener;

    public XiaoLiangRecyclerAdapter(Context mContext, List<GetGoodsList.MallGoodsDTOBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public interface OnItemClickListener {
        void onItemChild(View view, int position, GetGoodsList.MallGoodsDTOBean guessYouLikeList);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void show(List<GetGoodsList.MallGoodsDTOBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_myshopcar_item_user_like, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(mContext).load(Config.IP + mList.get(position).getGoodsImage()).fit().into(holder.img);
        holder.name.setText(mList.get(position).getGoodsName());
        holder.price.setText("￥" + mList.get(position).getGoodsStorePrice());
        holder.number.setText(mList.get(position).getSalenum() + "");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name, price, number;

        public MyViewHolder(final View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.fragment_myshopcar_item_user_like_img);
            name = (TextView) itemView.findViewById(R.id.fragment_myshopcar_item_user_like_name);
            price = (TextView) itemView.findViewById(R.id.fragment_myshopcar_item_user_like_price);
            number = (TextView) itemView.findViewById(R.id.fragment_myshopcar_item_user_like_number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemChild(itemView, getLayoutPosition(), mList.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
