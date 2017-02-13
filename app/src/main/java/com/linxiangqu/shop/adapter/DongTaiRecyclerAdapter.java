package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.DongTaiActivity;
import com.linxiangqu.shop.bean.platform.GetMallDynamicList;
import com.linxiangqu.shop.utils.PicassoTransformationUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/9/21.
 */
public class DongTaiRecyclerAdapter extends RecyclerView.Adapter<DongTaiRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<GetMallDynamicList.MallDynamicListInfoDTOBean> mList;

    public DongTaiRecyclerAdapter(Context mContext, List<GetMallDynamicList.MallDynamicListInfoDTOBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_myplatform_dongtai_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mList.get(position).getDynamicImg() == null) {
            holder.img.setBackgroundResource(R.mipmap.icon_others);
        } else
            Picasso.with(mContext).load(Config.IP + mList.get(position).getDynamicImg()).error(R.mipmap.example_about_us_sj).fit().centerInside().into(holder.img);
        holder.title.setText(mList.get(position).getDynamicTitle());
        holder.sj.setText(mList.get(position).getDynamicTime());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout linear;
        private ImageView img;
        private TextView title, sj;

        public MyViewHolder(View itemView) {
            super(itemView);
            linear = (RelativeLayout) itemView.findViewById(R.id.dongtai_item_linear);
            img = (ImageView) itemView.findViewById(R.id.dongtai_item_img);
            title = (TextView) itemView.findViewById(R.id.dongtai_item_title);
            sj = (TextView) itemView.findViewById(R.id.dongtai_item_time);
            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DongTaiActivity.class);
                    intent.putExtra("title", mList.get(getLayoutPosition()).getDynamicTitle());
                    intent.putExtra("dynamicId", mList.get(getLayoutPosition()).getDynamicId());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
