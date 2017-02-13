package com.linxiangqu.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.AppIndex;
import com.linxiangqu.shop.widget.verticalbannerview.BaseBannerAdapter;
import com.linxiangqu.shop.widget.verticalbannerview.VerticalBannerView;

import java.util.List;

/**
 * Created by linxiangqu on 2016/7/31.
 */
public class TouTiaoAdapter extends BaseBannerAdapter<AppIndex.GoodsGoodsTodayListDTOBean> {
    private Context mContext;

    public TouTiaoAdapter(Context context, List<AppIndex.GoodsGoodsTodayListDTOBean> datas) {
        super(datas);
        this.mContext = context;
    }

    @Override
    public void setData(List<AppIndex.GoodsGoodsTodayListDTOBean> datas) {
        super.setData(datas);
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.fragment_main_item_verticalbannerview, null);
    }

    @Override
    public void setItem(View view, final AppIndex.GoodsGoodsTodayListDTOBean data) {
        TextView tag = (TextView) view.findViewById(R.id.fragment_main_item_verticalbannerview_tag);
        TextView title = (TextView) view.findViewById(R.id.fragment_main_item_verticalbannerview_title);
        tag.setText("荐：");
        title.setSingleLine();
        title.setText(data.getGoodsName());
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, data.getGoodsName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
