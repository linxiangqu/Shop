package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.good.QueryCustomerCardList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linxiangqu on 2016/9/14.
 */
public class ChooseCustomerCardRecyclerAdapter extends RecyclerView.Adapter<ChooseCustomerCardRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<QueryCustomerCardList.MallCustomerCardDTOsBean> mList;
    private ChangeListener listener;
    private int lastPosition;
    private List<Boolean> isCheck;

    public ChooseCustomerCardRecyclerAdapter(Context mContext, List<QueryCustomerCardList.MallCustomerCardDTOsBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        isCheck = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            isCheck.add(i, false);
        }
    }

    public interface ChangeListener {
        void ItemChangeListener(View view, QueryCustomerCardList.MallCustomerCardDTOsBean mallCustomerCardDTOsBean, int position);
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public void setDefaultCard(int position) {
        mList.get(position).setIsUse(true);
        mList.get(lastPosition).setIsUse(false);
        notifyItemChanged(position);
        notifyItemChanged(lastPosition);
        lastPosition = position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_userbankandalipay_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mList.get(position).getCashOutType() == 1) {
            holder.img.setBackgroundResource(R.mipmap.yinghang_sj);
            holder.name.setText(mList.get(position).getCardCompany());
            holder.number.setText("尾数" + mList.get(position).getCardNo());
        }
        if (mList.get(position).getCashOutType() == 2) {
            holder.img.setBackgroundResource(R.mipmap.icon_alipay);
            holder.name.setText(mList.get(position).getCardName());
            holder.number.setText(mList.get(position).getCardNo());
        }
        if (mList.get(position).isIsUse()) {
            lastPosition = position;
            holder.button.setBackgroundResource(R.mipmap.icon_crsh_out_account_1);
        } else
            holder.button.setBackgroundResource(R.mipmap.icon_crsh_out_account_0);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView name, number;
        private ImageView button;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.choose_img);
            name = (TextView) itemView.findViewById(R.id.choose_name);
            number = (TextView) itemView.findViewById(R.id.choose_number);
            button = (ImageView) itemView.findViewById(R.id.check_select);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.ItemChangeListener(button, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
        }
    }
}
