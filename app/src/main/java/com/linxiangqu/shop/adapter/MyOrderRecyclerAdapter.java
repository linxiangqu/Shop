package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.order.QueryOrderList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/8/3.
 */
public class MyOrderRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Object> mList;
    private OnItemChildClickListener mOnItemChildClickListener;

    public interface OnItemChildClickListener {

        void OnItenChild(View view, Object object, int position);
    }

    public void setmOnItemChildClickListener(OnItemChildClickListener mOnItemChildClickListener) {
        this.mOnItemChildClickListener = mOnItemChildClickListener;
    }

    public void setmList(List<Object> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public MyOrderRecyclerAdapter(Context mContext, List<Object> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public enum ITEM_TYPE {
        GOODS, NOGOODS
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.GOODS.ordinal()) {
            return new goodDetailedInformation(LayoutInflater.from(mContext).inflate(R.layout.fragment_myorder_item_item, parent, false));
        } else {
            return new goodType(LayoutInflater.from(mContext).inflate(R.layout.fragment_myorder_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof goodDetailedInformation) {
            Picasso.with(mContext).load(Config.IP + ((QueryOrderList.MallOrderGoodsListDTOsBean.MallOrderGoodsInfoDTOsBean) mList.get(position)).getGoodsImage()).fit().into(((goodDetailedInformation) holder).img);
            ((goodDetailedInformation) holder).title.setText(((QueryOrderList.MallOrderGoodsListDTOsBean.MallOrderGoodsInfoDTOsBean) mList.get(position)).getGoodsName());
            ((goodDetailedInformation) holder).size_color.setText(((QueryOrderList.MallOrderGoodsListDTOsBean.MallOrderGoodsInfoDTOsBean) mList.get(position)).getGoodsAllSpecValue());
            ((goodDetailedInformation) holder).price.setText("￥" + ((QueryOrderList.MallOrderGoodsListDTOsBean.MallOrderGoodsInfoDTOsBean) mList.get(position)).getGoodsPrice());
            ((goodDetailedInformation) holder).number.setText("X " + ((QueryOrderList.MallOrderGoodsListDTOsBean.MallOrderGoodsInfoDTOsBean) mList.get(position)).getGoodsNum());
        } else {
            ((goodType) holder).number.setText("共件" + ((QueryOrderList.MallOrderGoodsListDTOsBean) mList.get(position)).getOrderAllAmount() + "商品，共");
            ((goodType) holder).price.setText("￥ " + ((QueryOrderList.MallOrderGoodsListDTOsBean) mList.get(position)).getOrderPrice());
            ((goodType) holder).scdd.setVisibility(View.GONE);
            ((goodType) holder).ckwl.setVisibility(View.GONE);
            ((goodType) holder).pj.setVisibility(View.GONE);
            ((goodType) holder).qxdd.setVisibility(View.GONE);
            ((goodType) holder).fk.setVisibility(View.GONE);
            ((goodType) holder).tk.setVisibility(View.GONE);
            ((goodType) holder).tkth.setVisibility(View.GONE);
            ((goodType) holder).qrsh.setVisibility(View.GONE);
            ((goodType) holder).tkthck.setVisibility(View.GONE);
            switch (((QueryOrderList.MallOrderGoodsListDTOsBean) mList.get(position)).getOrderState()) {
                case 10:
                    ((goodType) holder).qxdd.setVisibility(View.VISIBLE);
                    ((goodType) holder).fk.setVisibility(View.VISIBLE);
                    break;
                case 20:
                    ((goodType) holder).tk.setVisibility(View.VISIBLE);
                    break;
                case 30:
                    ((goodType) holder).tkth.setVisibility(View.VISIBLE);
                    ((goodType) holder).ckwl.setVisibility(View.VISIBLE);
                    ((goodType) holder).qrsh.setVisibility(View.VISIBLE);
                    break;
                case 40:
                case 70:
                    ((goodType) holder).scdd.setVisibility(View.VISIBLE);
                    ((goodType) holder).ckwl.setVisibility(View.VISIBLE);
                    ((goodType) holder).pj.setVisibility(View.VISIBLE);
                    break;
                case 80:
                case 90:
                    ((goodType) holder).tkthck.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof QueryOrderList.MallOrderGoodsListDTOsBean)
            return ITEM_TYPE.NOGOODS.ordinal();
        else
            return ITEM_TYPE.GOODS.ordinal();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class goodDetailedInformation extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView title, size_color, price, number;
        private LinearLayout linearLayout;

        public goodDetailedInformation(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.fragment_myorder_item_item);
            img = (ImageView) itemView.findViewById(R.id.fragment_myorder_item_item_img);
            title = (TextView) itemView.findViewById(R.id.fragment_myorder_item_item_titel);
            size_color = (TextView) itemView.findViewById(R.id.fragment_myorder_item_item_size_color);
            price = (TextView) itemView.findViewById(R.id.fragment_myorder_item_item_price);
            number = (TextView) itemView.findViewById(R.id.fragment_myorder_item_item_number);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(linearLayout, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });

        }
    }

    public class goodType extends RecyclerView.ViewHolder {

        private TextView number, price, scdd, ckwl, pj, qxdd, fk, tk, tkth, qrsh, tkthck;
        private LinearLayout linearLayout;

        public goodType(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.fragment_myorder_item);
            number = (TextView) itemView.findViewById(R.id.fragment_myorder_item_number);
            price = (TextView) itemView.findViewById(R.id.fragment_myorder_item_price);
            scdd = (TextView) itemView.findViewById(R.id.fragment_myorder_item_scdd);
            ckwl = (TextView) itemView.findViewById(R.id.fragment_myorder_item_ckwl);
            pj = (TextView) itemView.findViewById(R.id.fragment_myorder_item_pj);
            qxdd = (TextView) itemView.findViewById(R.id.fragment_myorder_item_qxdd);
            fk = (TextView) itemView.findViewById(R.id.fragment_myorder_item_fk);
            tk = (TextView) itemView.findViewById(R.id.fragment_myorder_item_tk);
            tkth = (TextView) itemView.findViewById(R.id.fragment_myorder_item_tkth);
            qrsh = (TextView) itemView.findViewById(R.id.fragment_myorder_item_qrsh);
            tkthck = (TextView) itemView.findViewById(R.id.fragment_myorder_item_tkthck);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(linearLayout, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            scdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(scdd, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            ckwl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(ckwl, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            pj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(pj, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            qxdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(qxdd, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            fk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(fk, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            tk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(tk, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            tkth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(tkth, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            qrsh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(qrsh, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            tkthck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemChildClickListener != null)
                        mOnItemChildClickListener.OnItenChild(tkthck, mList.get(getLayoutPosition()), getLayoutPosition());
                }
            });
        }
    }
}
