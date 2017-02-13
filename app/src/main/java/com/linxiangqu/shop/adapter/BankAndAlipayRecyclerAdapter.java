package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.good.QueryCustomerCardList;

import java.util.List;

/**
 * Created by linxiangqu on 2016/9/14.
 */
public class BankAndAlipayRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<QueryCustomerCardList.MallCustomerCardDTOsBean> mList;

    public enum ITEM_TYPE {
        BANK, ALIPAY
    }

    public BankAndAlipayRecyclerAdapter(Context mContext, List<QueryCustomerCardList.MallCustomerCardDTOsBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.BANK.ordinal()) {
            return new BankItem(LayoutInflater.from(mContext).inflate(R.layout.activity_bankandalipay_bank_item, parent, false));
        } else {
            return new AlipayItem(LayoutInflater.from(mContext).inflate(R.layout.activity_bankandalipay_alipay_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BankItem) {
            ((BankItem) holder).band_name.setText(mList.get(position).getCardCompany());
            ((BankItem) holder).bank_user.setText(mList.get(position).getCardName());
            ((BankItem) holder).bank_tv.setText(mList.get(position).getCardNo());
        } else {
            ((AlipayItem) holder).alipay_name.setText(mList.get(position).getCardName());
            ((AlipayItem) holder).alipay_zhanghao.setText(mList.get(position).getCardNo());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).getCashOutType() == 2) {
            return ITEM_TYPE.ALIPAY.ordinal();
        } else
            return ITEM_TYPE.BANK.ordinal();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class BankItem extends RecyclerView.ViewHolder {

        private TextView band_name, bank_user, bank_tv;

        public BankItem(View itemView) {
            super(itemView);
            band_name = (TextView) itemView.findViewById(R.id.bank_name);
            bank_user = (TextView) itemView.findViewById(R.id.bank_user);
            bank_tv = (TextView) itemView.findViewById(R.id.bank_tv);
        }
    }

    class AlipayItem extends RecyclerView.ViewHolder {

        private TextView alipay_name, alipay_zhanghao;

        public AlipayItem(View itemView) {
            super(itemView);
            alipay_name = (TextView) itemView.findViewById(R.id.zfb_name);
            alipay_zhanghao = (TextView) itemView.findViewById(R.id.zfb_zhanghao);
        }
    }
}
