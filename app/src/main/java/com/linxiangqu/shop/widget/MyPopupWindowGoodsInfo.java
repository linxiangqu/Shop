package com.linxiangqu.shop.widget;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.MyShopCarPopupWindowAdapter;
import com.linxiangqu.shop.bean.shopcar.GetUserCartList;
import com.linxiangqu.shop.bean.SpecInfo;
import com.linxiangqu.shop.bean.good.GetGoodsInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linxiangqu on 2016/8/8.
 */
public class MyPopupWindowGoodsInfo implements View.OnClickListener, View.OnTouchListener {
    private Activity mContext;
    private OnMediaClick mOnMediaClick;
    private OnTouchBack mOnTouchBack;
    private PopupWindow mPopupWindow;
    private int goodnumber;
    private ImageView img, remove, add;
    private TextView title, price, num, number;
    private Button ok;
    private View convertView;
    private RecyclerView goodsSpec;
    private MyShopCarPopupWindowAdapter adapterOne;
    private List<GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean> mList;
    private GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTO;
    private int w, h;
    private Gson gson = new Gson();
    private GetGoodsInfo getGoodsInfo;
    private GetUserCartList.UserCartDTOsBean userCartDTOs = new GetUserCartList.UserCartDTOsBean();

    public interface OnMediaClick {
        void onOkClick(GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTO, PopupWindow mPopupWindow,int goodnumber);
    }

    public interface OnTouchBack {
        void onTouch(View v, MotionEvent event, PopupWindow mPopupWindow);
    }

    public void setOnMediaClick(OnMediaClick onMediaClick) {
        this.mOnMediaClick = onMediaClick;
    }

    public void setmOnTouchBack(OnTouchBack mOnTouchBack) {
        this.mOnTouchBack = mOnTouchBack;
    }

    public MyPopupWindowGoodsInfo(Activity context, GetGoodsInfo getGoodsInfo) {
        this.mContext = context;
        this.getGoodsInfo = getGoodsInfo;
        mList = gson.fromJson(gson.toJson(this.getGoodsInfo.getGoodsSpecListDTO()), new TypeToken<List<GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean>>() {
        }.getType());
        goodsSpecDTO = gson.fromJson(gson.toJson(this.getGoodsInfo.getGoodsSpecDTO().get(0)), GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean.class);
        userCartDTOs.setGoodsSpecListDTO(mList);
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (mOnTouchBack != null)
            mOnTouchBack.onTouch(v, event, mPopupWindow);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_myshopcar_popupwindow_remove:
                if (goodnumber != 1) {
                    goodnumber--;
                    number.setText(goodnumber + "");
                    price.setText(Double.valueOf(goodsSpecDTO.getSpecGoodsPrice() * goodnumber) + "");
                }
                break;
            case R.id.fragment_myshopcar_popupwindow_add:
                if (goodnumber < goodsSpecDTO.getSpecGoodsStorage()) {
                    goodnumber++;
                    number.setText(goodnumber + "");
                    price.setText(Double.valueOf(goodsSpecDTO.getSpecGoodsPrice() * goodnumber) + "");
                }
                break;
            case R.id.fragment_myshopcar_popupwindow_ok:
                if (mOnMediaClick != null)
                    mOnMediaClick.onOkClick(goodsSpecDTO, mPopupWindow,goodnumber);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化控件 , 实现控件点击事件
     */
    private void init() {
        initView();
        bindData();
        initData();
        initListener();
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        h = metrics.heightPixels;
        w = metrics.widthPixels;
        mPopupWindow = new PopupWindow(convertView, w, h / 3 * 2, true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        convertView.setOnTouchListener(this);//触摸事件 , 在其他区域触摸屏幕 , 取消popupwindow.
//        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//保证popupwindow响应返回按钮事件.
        mPopupWindow.showAtLocation(LayoutInflater.from(mContext).inflate(R.layout.activity_goods, null), Gravity.BOTTOM, 0, 0);//在底部显示popupwindow.
    }

    private void initView() {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_myshopcar_popupwindow, null, false);
        goodsSpec = (RecyclerView) convertView.findViewById(R.id.fragment_myshopcar_popupwindow_recyclerview);
        img = (ImageView) convertView.findViewById(R.id.fragment_myshopcar_popupwindow_img);
        title = (TextView) convertView.findViewById(R.id.fragment_myshopcar_popupwindow_title);
        price = (TextView) convertView.findViewById(R.id.fragment_myshopcar_popupwindow_price);
        num = (TextView) convertView.findViewById(R.id.fragment_myshopcar_popupwindow_num);
        remove = (ImageView) convertView.findViewById(R.id.fragment_myshopcar_popupwindow_remove);
        number = (TextView) convertView.findViewById(R.id.fragment_myshopcar_popupwindow_number);
        add = (ImageView) convertView.findViewById(R.id.fragment_myshopcar_popupwindow_add);
        ok = (Button) convertView.findViewById(R.id.fragment_myshopcar_popupwindow_ok);
    }

    private void bindData() {
        for (int i = 0; i < mList.size(); i++) {
            adapterOne = new MyShopCarPopupWindowAdapter(mContext, userCartDTOs.getGoodsSpecListDTO(), goodsSpecDTO);
            goodsSpec.setLayoutManager(new LinearLayoutManager(mContext));
            goodsSpec.setAdapter(adapterOne);
            adapterOne.setmOnItemClickListener(new MyShopCarPopupWindowAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int positions, GetUserCartList.UserCartDTOsBean.GoodsSpecListDTOBean.GoodsSpecValueListDTOBean specInfo) {
                    view.setBackgroundResource(R.mipmap.param_child_selected);
                    List<SpecInfo> goodSpecGoodsSpec = gson.fromJson(goodsSpecDTO.getSpecGoodsSpec(), new TypeToken<List<SpecInfo>>() {
                    }.getType());
                    goodSpecGoodsSpec.get(positions).setSpValue(specInfo.getSpValue());
                    goodSpecGoodsSpec.get(positions).setSpValueId(specInfo.getSpValueId());
                    for (GetGoodsInfo.GoodsSpecDTOBean goodsSpecDTOBean : getGoodsInfo.getGoodsSpecDTO()) {
                        if (goodsSpecDTOBean.getSpecGoodsSpec().equals(gson.toJson(goodSpecGoodsSpec))) {
                            Picasso.with(mContext).load(Config.IP + goodsSpecDTOBean.getSpecGoodsColor()).fit().into(img);
                            price.setText(goodsSpecDTOBean.getSpecGoodsPrice() * goodnumber + "");
                            num.setText(goodsSpecDTOBean.getSpecGoodsStorage() + "");
                            goodsSpecDTO = gson.fromJson(gson.toJson(goodsSpecDTOBean), GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean.class);
                            if (goodnumber > goodsSpecDTOBean.getSpecGoodsStorage()) {
                                number.setText(goodsSpecDTOBean.getSpecGoodsStorage() + "");
                                goodnumber = goodsSpecDTOBean.getSpecGoodsStorage();
                            }
                        }
                    }
                }
            });
        }
    }

    private void initData() {
        goodnumber = 1;
        Picasso.with(mContext).load(Config.IP + getGoodsInfo.getGoodsImage()).fit().into(img);
        title.setText(getGoodsInfo.getGoodsName());
        price.setText(getGoodsInfo.getGoodsStorePrice() + "");
        num.setText(goodsSpecDTO.getSpecGoodsStorage() + "");
        number.setText(goodnumber + "");
    }

    private void initListener() {
        remove.setOnClickListener(this);
        add.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    public MyPopupWindowGoodsInfo showPop() {
        if (null != mPopupWindow) {
            mPopupWindow.dismiss();
        } else {
            init();
        }
        return this;
    }

    public boolean disMissPop() {
        boolean isshowing = false;
        if (null != mPopupWindow) {
            isshowing = mPopupWindow.isShowing();
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        return isshowing;
    }
}