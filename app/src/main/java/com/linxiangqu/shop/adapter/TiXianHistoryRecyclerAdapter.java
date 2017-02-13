package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.good.QueryCashOutList;
import com.linxiangqu.shop.utils.DateUtil;

import java.util.List;

/**
 * Created by linxiangqu on 2016/8/24.
 */
public class TiXianHistoryRecyclerAdapter extends RecyclerView.Adapter<TiXianHistoryRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<QueryCashOutList.MallCashOutDTOsBean> mList;

    public TiXianHistoryRecyclerAdapter(Context mContext, List<QueryCashOutList.MallCashOutDTOsBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_tixianhistory_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.rq.setText(DateUtil.fromTimestamp(mList.get(position).getCreateTime(), DateUtil.Format.YEAR2DAY));
        switch (mList.get(position).getState()) {
            case 1:
                holder.zt.setText("提现申请中");
                break;
            case 2:
                holder.zt.setText("提现成功");
                break;
            case 3:
                holder.zt.setText("提现失败");
                break;
        }
        holder.je.setText(mList.get(position).getMoney() + "");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView rq, zt, je;

        public MyViewHolder(View itemView) {
            super(itemView);
            rq = (TextView) itemView.findViewById(R.id.activity_tixianhistory_item_rq);
            zt = (TextView) itemView.findViewById(R.id.activity_tixianhistory_item_zt);
            je = (TextView) itemView.findViewById(R.id.activity_tixianhistory_item_je);
        }
    }
}
