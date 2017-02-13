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
import com.linxiangqu.shop.activity.JiaoLiuQuActivity;
import com.linxiangqu.shop.bean.platform.GetMallBbsList;
import com.linxiangqu.shop.utils.PicassoTransformationUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/9/21.
 */
public class JiaoLiuQuRecyclerAdapter extends RecyclerView.Adapter<JiaoLiuQuRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<GetMallBbsList.MallBbsDTOBean> mList;

    public JiaoLiuQuRecyclerAdapter(Context mContext, List<GetMallBbsList.MallBbsDTOBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_myplatform_jiaoliuqu_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mList.get(position).getUserImg() == null) {
            holder.user_img.setBackgroundResource(R.mipmap.icon_others);
        } else
            Picasso.with(mContext).load(Config.IP + mList.get(position).getUserImg()).transform(new PicassoTransformationUtils()).error(R.mipmap.icon_others).fit().into(holder.user_img);
        holder.name.setText(mList.get(position).getUserName());
        holder.sj.setText(mList.get(position).getCreateTime());
        holder.pl_number.setText(mList.get(position).getTotalCountBbs() + "");
        holder.title.setText(mList.get(position).getTitle());
        holder.content.setText(mList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linear;
        private ImageView user_img;
        private TextView name, sj, pl_number, title, content;

        public MyViewHolder(View itemView) {
            super(itemView);
            linear = (LinearLayout) itemView.findViewById(R.id.jiaoliuqu_item_linear);
            user_img = (ImageView) itemView.findViewById(R.id.jiaoliuqu_item_img);
            name = (TextView) itemView.findViewById(R.id.jiaoliuqu_item_name);
            sj = (TextView) itemView.findViewById(R.id.jiaoliuqu_item_time);
            pl_number = (TextView) itemView.findViewById(R.id.jiaoliuqu_item_pl_number);
            title = (TextView) itemView.findViewById(R.id.jiaoliuqu_item_title);
            content = (TextView) itemView.findViewById(R.id.jiaoliuqu_item_content);
            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, JiaoLiuQuActivity.class);
                    intent.putExtra("bbsId", mList.get(getLayoutPosition()).getBbsId());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
