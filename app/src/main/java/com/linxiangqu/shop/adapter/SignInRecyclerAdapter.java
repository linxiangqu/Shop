package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.user.QueryUserCheckInLogList;
import com.linxiangqu.shop.utils.CalendarUtil;
import com.linxiangqu.shop.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linxiangqu on 2016/9/18.
 */
public class SignInRecyclerAdapter extends RecyclerView.Adapter<SignInRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Boolean> isSignIn;
    private List<QueryUserCheckInLogList.MallUserCheckInLogDTOsBean> mList;
    private int day = 1;

    public SignInRecyclerAdapter(Context mContext, List<QueryUserCheckInLogList.MallUserCheckInLogDTOsBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        isSignIn = new ArrayList<>();
        int lastDay, a;
        if (mList.size() == 0) {
            lastDay = -1;
            a = -1;
        } else {
            lastDay = DateUtil.getDay(mList.get(0).getLogTime() + "") + CalendarUtil.getWeek() + 6;
            a = mList.size() - 1;
        }
        for (int i = 0; i < CalendarUtil.getMaxDay() + CalendarUtil.getWeek() + 6; i++) {
            if (i < CalendarUtil.getWeek() + 6)
                isSignIn.add(i, false);
            else {
                if (lastDay >= i) {
                    int day = DateUtil.getDay(mList.get(a).getLogTime() + "") + CalendarUtil.getWeek() + 6;
                    if (day == i) {
                        isSignIn.add(i, true);
                        a--;
                    } else
                        isSignIn.add(i, false);
                } else
                    isSignIn.add(i, false);
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_signin_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == 0)
            holder.tv.setText("日");
        if (position == 1)
            holder.tv.setText("一");
        if (position == 2)
            holder.tv.setText("二");
        if (position == 3)
            holder.tv.setText("三");
        if (position == 4)
            holder.tv.setText("四");
        if (position == 5)
            holder.tv.setText("五");
        if (position == 6)
            holder.tv.setText("六");
        if (position >= CalendarUtil.getWeek() + 6 && position < CalendarUtil.getMaxDay() + CalendarUtil.getWeek() + 6) {
            holder.tv.setText(day + "");
            day++;
            if (isSignIn.get(position))
                holder.img.setVisibility(View.VISIBLE);
            else
                holder.img.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        int count = CalendarUtil.getMaxDay() + CalendarUtil.getWeek() + 7;
        if (count % 7 == 0)
            return count;
        else
            return ((count / 7) + 1) * 7;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.activity_sign_item_date);
            img = (ImageView) itemView.findViewById(R.id.activity_sign_item_img);
        }
    }
}
