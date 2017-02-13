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
import com.linxiangqu.shop.bean.shopcar.GetUserCartList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/7/27.
 */
public class YourLikeRecyclerAdapter extends RecyclerView.Adapter<YourLikeRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<GetUserCartList.GuessYouLikeListBean> mList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onItemChild(View view, int position, GetUserCartList.GuessYouLikeListBean guessYouLikeList);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void show(List<GetUserCartList.GuessYouLikeListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public YourLikeRecyclerAdapter(Context mContext, List<GetUserCartList.GuessYouLikeListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
        return mList.size();
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
