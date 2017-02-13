package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.shopcar.GetUserCartList;
import com.linxiangqu.shop.bean.SpecInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linxiangqu on 2016/7/27.
 */
public class MyShopCarRecyclerAdapter extends RecyclerView.Adapter<MyShopCarRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<GetUserCartList.UserCartDTOsBean> userCartDTOses;
    private List<GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean> mList;
    private int num;
    private Gson gson = new Gson();
    private List<Boolean> isChoose;
    private OnItemClickListener onItemClickListener;
    private HashMap<String, String> cartId = new HashMap<>();

    public HashMap<String, String> getCartId() {
        return cartId;
    }

    public interface OnItemClickListener {
        void onItemChild(View view, int position, GetUserCartList.UserCartDTOsBean userCartDTOs, GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTO, boolean flag, boolean allChoose);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void upData(GetUserCartList.UserCartDTOsBean userCartDTOs, GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTO, int positions) {
        this.userCartDTOses.set(positions, userCartDTOs);
        this.mList.set(positions, goodsSpecDTO);
        notifyItemChanged(positions);
    }

    public void setChoose(boolean choose, int position) {
        if (position == -1) {
            if (isChoose.size() != 0)
                for (int i = 0; i < isChoose.size(); i++) {
                    if (choose) {
                        cartId.put(i + "", userCartDTOses.get(i).getCartId() + "");
                    } else {
                        cartId.remove(i + "");
                    }
                    isChoose.set(i, choose);
                }
        } else {
            for (int i = 0; i < isChoose.size(); i++) {
                if (i == position)
                    isChoose.set(position, false);
                else {
                    cartId.put(i + "", userCartDTOses.get(i).getCartId() + "");
                    isChoose.set(i, choose);
                }
            }
        }
        notifyDataSetChanged();
    }

    public MyShopCarRecyclerAdapter(Context mContext, List<GetUserCartList.UserCartDTOsBean> userCartDTOses, List<GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean> mList) {
        this.mContext = mContext;
        this.userCartDTOses = userCartDTOses;
        this.mList = mList;
        isChoose = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            isChoose.add(i, false);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_myshopcar_item_user_order, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        num = userCartDTOses.get(position).getGoodsNum();
        holder.name.setText(userCartDTOses.get(position).getGoodsName());
        holder.number.setText("X" + num);
        Picasso.with(mContext).load(Config.IP + mList.get(position).getSpecGoodsColor()).fit().into(holder.img);
        holder.price.setText("￥" + mList.get(position).getSpecGoodsPrice() * Double.valueOf(num) + "");
        List<SpecInfo> specInfo = gson.fromJson(mList.get(position).getSpecGoodsSpec(), new TypeToken<List<SpecInfo>>() {
        }.getType());
        String size_color = "";
        if (specInfo.size() != 0)
            for (int i = 0; i < specInfo.size(); i++) {
                if (i == 0)
                    size_color = size_color + specInfo.get(i).getSpValue();
                else
                    size_color = size_color + " " + specInfo.get(i).getSpValue();
            }
        holder.size_color.setText(size_color);
        if (isChoose.get(position))
            holder.mCheckBox.setChecked(true);
        else
            holder.mCheckBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox mCheckBox;
        private ImageView img;
        private TextView name, size_color, number, price, bianji;
        private RelativeLayout relativeLayout;

        public MyViewHolder(final View itemView) {
            super(itemView);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.fragment_myshopcar_item_user_order_checkbox);
            img = (ImageView) itemView.findViewById(R.id.fragment_myshopcar_item_user_order_img);
            name = (TextView) itemView.findViewById(R.id.fragment_myshopcar_item_user_order_name);
            size_color = (TextView) itemView.findViewById(R.id.fragment_myshopcar_item_user_order_size_color);
            number = (TextView) itemView.findViewById(R.id.fragment_myshopcar_item_user_order_number);
            price = (TextView) itemView.findViewById(R.id.fragment_myshopcar_item_user_order_price);
            bianji = (TextView) itemView.findViewById(R.id.fragment_myshopcar_item_user_order_bianji);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.fragment_myshopcar_item_user_order);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemChild(relativeLayout, getLayoutPosition(), userCartDTOses.get(getLayoutPosition()), mList.get(getLayoutPosition()), false, false);
                    }
                }
            });

            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Boolean allChoose = true;
                    if (b) {
                        isChoose.set(getLayoutPosition(), true);
                        cartId.put(getLayoutPosition() + "", userCartDTOses.get(getLayoutPosition()).getCartId() + "");
                    } else {
                        isChoose.set(getLayoutPosition(), false);
                        cartId.remove(getLayoutPosition() + "");
                    }
                    for (int i = 0; i < isChoose.size(); i++) {
                        if (!isChoose.get(i))
                            allChoose = false;
                    }
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemChild(mCheckBox, getLayoutPosition(), userCartDTOses.get(getLayoutPosition()), mList.get(getLayoutPosition()), b, allChoose);
                    }
                }
            });
            bianji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemChild(bianji, getLayoutPosition(), userCartDTOses.get(getLayoutPosition()), mList.get(getLayoutPosition()), false, false);
                    }
                }
            });
        }
    }
}
