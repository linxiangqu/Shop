package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.LoginActivity;
import com.linxiangqu.shop.activity.ShangPinQuActivity;
import com.linxiangqu.shop.bean.platform.GetMallGoodsZoneList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/9/20.
 */
public class ShangPinQuRecyclerAdapter extends RecyclerView.Adapter<ShangPinQuRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<GetMallGoodsZoneList.MallGoodsZoneListinfoDTOBean> mList;

    public ShangPinQuRecyclerAdapter(Context mContext, List<GetMallGoodsZoneList.MallGoodsZoneListinfoDTOBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_myplatform_shangpinqu_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mList.get(position).getPicture().size() == 0)
            holder.img.setImageResource(R.mipmap.loading_sj);
        else
            Picasso.with(mContext).load(Config.IP + mList.get(position).getPicture().get(0).getImgPath()).fit().centerInside().into(holder.img);
        holder.title.setText(mList.get(position).getGoodsZoneTitle());
        holder.name.setText(mList.get(position).getUserName());
        holder.sj.setText(mList.get(position).getGoodsZoneTime());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linear;
        private ImageView img;
        private TextView title, name, sj;
        private ImageButton call;

        public MyViewHolder(View itemView) {
            super(itemView);
            linear = (LinearLayout) itemView.findViewById(R.id.shangpinqu_item_linear);
            img = (ImageView) itemView.findViewById(R.id.shangpinqu_item_img);
            title = (TextView) itemView.findViewById(R.id.shangpinqu_item_title);
            name = (TextView) itemView.findViewById(R.id.shangpinqu_item_name);
            sj = (TextView) itemView.findViewById(R.id.shangpinqu_item_sj);
            call = (ImageButton) itemView.findViewById(R.id.shangpinqu_item_call);

            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShangPinQuActivity.class);
                    intent.putExtra("goodsZoneId", mList.get(getLayoutPosition()).getGoodsZoneId());
                    intent.putExtra("name", mList.get(getLayoutPosition()).getGoodsZoneTitle());
                    mContext.startActivity(intent);
                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + mList.get(getLayoutPosition()).getGoodsZoneMobile());
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
