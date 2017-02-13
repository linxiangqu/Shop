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
import com.linxiangqu.shop.bean.user.CollectionList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/8/25.
 */
public class MyFavoritesRecyclerAdapter extends RecyclerView.Adapter<MyFavoritesRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<CollectionList> mList;
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void OnClick(View v, CollectionList collectionList, int position);
    }

    public MyFavoritesRecyclerAdapter(Context mContext, List<CollectionList> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void delGoodsCollection(int position) {
        mList.remove(position);
        notifyItemChanged(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_favorites_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(mContext).load(Config.IP + mList.get(position).getGoodsImg()).fit().into(holder.photo);
        holder.title.setText(mList.get(position).getGoodsName());
        holder.price.setText("￥ " + mList.get(position).getGoodsStorePrice());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;
        private TextView title, price, qxsc;

        public MyViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.activity_favorites_item_img);
            title = (TextView) itemView.findViewById(R.id.activity_favorites_item_title);
            price = (TextView) itemView.findViewById(R.id.activity_favorites_item_price);
            qxsc = (TextView) itemView.findViewById(R.id.activity_favorites_item_qxsc);
            qxsc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null)
                        mOnItemClickListener.OnClick(v, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
        }
    }
}
