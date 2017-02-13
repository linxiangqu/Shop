package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.ReplyActivity;
import com.linxiangqu.shop.bean.platform.GetMallBbsReplyList;
import com.linxiangqu.shop.utils.PicassoTransformationUtils;
import com.linxiangqu.shop.widget.vertical.VerticalRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/9/22.
 */
public class ReplyRecyclerAdapter extends RecyclerView.Adapter<ReplyRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<GetMallBbsReplyList.MallBbsReplyDTOListBean> mList;
    private ReplyRecyclerAdapterTwo adapterTwo;
    private int bbsId;

    public ReplyRecyclerAdapter(Context mContext, List<GetMallBbsReplyList.MallBbsReplyDTOListBean> mList, int bbsId) {
        this.mContext = mContext;
        this.mList = mList;
        this.bbsId = bbsId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_jiaoliuqu_reply_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(mContext).load(Config.IP + mList.get(position).getUserImg()).transform(new PicassoTransformationUtils()).error(R.mipmap.icon_others).fit().into(holder.img);
        if (mList.get(position).isIsAnonymity()) {
            holder.name.setText("匿名用户");
        } else
            holder.name.setText(mList.get(position).getUserName());
        holder.time.setText(mList.get(position).getRepleTime());
        holder.content.setText(mList.get(position).getReplyContent());
        adapterTwo = new ReplyRecyclerAdapterTwo(mContext, mList.get(position).getMallBbsSecondReplyDTO(), mList.get(position).getReplyId(), mList.get(position).getUserId(), mList.get(position).isIsAnonymity(), mList.get(position).getUserName());
        holder.recycler.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recycler.setAdapter(adapterTwo);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recycler;
        private ImageView img, reply;
        private TextView name, time, content;

        public MyViewHolder(View itemView) {
            super(itemView);
            recycler = (RecyclerView) itemView.findViewById(R.id.activity_jiaoliuqu_reply_item_recycler);
            img = (ImageView) itemView.findViewById(R.id.activity_jiaoliuqu_reply_item_img);
            reply = (ImageView) itemView.findViewById(R.id.activity_jiaoliuqu_reply_item_reply);
            name = (TextView) itemView.findViewById(R.id.activity_jiaoliuqu_reply_item_name);
            time = (TextView) itemView.findViewById(R.id.activity_jiaoliuqu_reply_item_time);
            content = (TextView) itemView.findViewById(R.id.activity_jiaoliuqu_reply_item_content);
            reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ReplyActivity.class);
                    intent.putExtra("bbsId", bbsId);
                    intent.putExtra("replyId", mList.get(getLayoutPosition()).getReplyId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class ReplyRecyclerAdapterTwo extends RecyclerView.Adapter<ReplyRecyclerAdapterTwo.MyViewHolder> {

        private Context mContext;
        private List<GetMallBbsReplyList.MallBbsReplyDTOListBean.MallBbsSecondReplyDTOBean> mList;
        private int replyId, userId;
        private boolean isAnonymity;
        private String userName;

        public ReplyRecyclerAdapterTwo(Context mContext, List<GetMallBbsReplyList.MallBbsReplyDTOListBean.MallBbsSecondReplyDTOBean> mList, int replyId, int userId, boolean isAnonymity, String userName) {
            this.mContext = mContext;
            this.mList = mList;
            this.replyId = replyId;
            this.userId = userId;
            this.isAnonymity = isAnonymity;
            this.userName = userName;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.activity_jiaoliuqu_secondreply_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            StringBuffer sb = new StringBuffer();
            if (mList.get(position).getUserId() == userId) {
                if (mList.get(position).getUserId() == Config.getCacheUserId(mContext)) {
                    sb.append("我说：");
                } else {
                    if (mList.get(position).isIsAnonymity()) {
                        sb.append("匿名用户说：");
                    } else {
                        sb.append(mList.get(position).getUserName() + "说：");
                    }
                }
            } else {
                if (mList.get(position).getUserId() == Config.getCacheUserId(mContext)) {
                    if (isAnonymity)
                        sb.append("我对匿名用户说：");
                    else
                        sb.append("我对" + userName + "说：");
                } else {
                    if (isAnonymity)
                        if (mList.get(position).isIsAnonymity())
                            sb.append("匿名用户对匿名用户说：");
                        else
                            sb.append(mList.get(position).getUserName() + "对匿名用户说：");
                    else {
                        if (mList.get(position).isIsAnonymity())
                            if (Config.getCacheUserId(mContext) == userId) {
                                sb.append("匿名用户" + "对我说：");
                            } else {
                                sb.append("匿名用户对" + userName + "说：");
                            }
                        else {
                            if (Config.getCacheUserId(mContext) == userId) {
                                sb.append(mList.get(position).getUserName() + "对我说：");
                            } else {
                                sb.append(mList.get(position).getUserName() + "对" + userName + "说：");
                            }
                        }
                    }
                }
            }
            String sbb = sb.toString();
            String content = mList.get(position).getReplyContent().trim();
            SpannableStringBuilder style = new SpannableStringBuilder(sbb + content);
            style.setSpan(new ForegroundColorSpan(Color.BLUE), 0, sbb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tv.setText(style);
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.secondreply_tv);
            }
        }
    }
}
