package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.GoodsActivity;
import com.linxiangqu.shop.bean.good.GetBuySongPage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/9/13.
 */
public class BuyWithSendRecyclerAdapter extends RecyclerView.Adapter<BuyWithSendRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<GetBuySongPage.BuySongListContentDTOBean> mList;

    public BuyWithSendRecyclerAdapter(Context mContext, List<GetBuySongPage.BuySongListContentDTOBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.activity_buywithsend_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.send.setText(mList.get(position).getBuysongName());
        Picasso.with(mContext).load(Config.IP + mList.get(position).getGoodsImage()).fit().into(holder.img);
        holder.price.setText("￥ " + mList.get(position).getGoodsStorePrice());
        holder.title.setText(mList.get(position).getGoodsName());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linear;
        private ImageView img;
        private TextView send, price, title;

        public MyViewHolder(View itemView) {
            super(itemView);
            linear = (LinearLayout) itemView.findViewById(R.id.activity_buywithsend_linearlayout);
            send = (TextView) itemView.findViewById(R.id.activity_buywithsend_send);
            img = (ImageView) itemView.findViewById(R.id.activity_buywithsend_img);
            title = (TextView) itemView.findViewById(R.id.activity_buywithsend_title);
            price = (TextView) itemView.findViewById(R.id.activity_buywithsend_price);

            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodsActivity.class);
                    intent.putExtra("goodsId", mList.get(getLayoutPosition()).getGoodsId());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
