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
public class MainBottomRecyclerAdapter extends RecyclerView.Adapter<MainBottomRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<AppIndex.MallTopicDTOsBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onChildClick(View v, int position, AppIndex.MallTopicDTOsBean mallTopicDTOs);
    }

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public MainBottomRecyclerAdapter(Context mContext, List<AppIndex.MallTopicDTOsBean> mallTopicDTOses) {
        this.mContext = mContext;
        this.mList = mallTopicDTOses;
    }

    public void show(List<AppIndex.MallTopicDTOsBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_item_bottom_fenlei, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getTitle());
        holder.subTitle.setText(mList.get(position).getSubTitle());
        Picasso.with(mContext).load(Config.IP + mList.get(position).getImage()).fit().into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView title, subTitle;

        public MyViewHolder(final View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.fragment_main_item_bottom_fenlei_img);
            title = (TextView) itemView.findViewById(R.id.fragment_main_item_bottom_fenlei_title);
            subTitle = (TextView) itemView.findViewById(R.id.fragment_main_item_bottom_fenlei_subtitle);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onChildClick(itemView, getLayoutPosition(), mList.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

}
