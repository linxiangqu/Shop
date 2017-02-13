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
import com.linxiangqu.shop.bean.AppIndex;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/7/26.
 */
public class MainFenLeiRecyclerAdapter extends RecyclerView.Adapter<MainFenLeiRecyclerAdapter.MyViewHolder> {

    private List<AppIndex.GoodsClassListDTOBean> mList;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, AppIndex.GoodsClassListDTOBean goodsClassListDTO, int position);
    }

    public MainFenLeiRecyclerAdapter(Context mContext, List<AppIndex.GoodsClassListDTOBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_main_item_fenlei, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position).getGcName());
        Picasso.with(mContext).load(Config.IP + mList.get(position).getGcImage()).into(holder.mImageView);
    }

    public void show(List<AppIndex.GoodsClassListDTOBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.fragment_main_item_fenlei_img);
            mTextView = (TextView) itemView.findViewById(R.id.fragment_main_item_fenlei_tv);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, mList.get(getLayoutPosition()), getLayoutPosition());
                    }
                }
            });
        }
    }
}
