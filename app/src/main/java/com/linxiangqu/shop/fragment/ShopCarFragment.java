package com.linxiangqu.shop.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.GoodsActivity;
import com.linxiangqu.shop.adapter.MyShopCarRecyclerAdapter;
import com.linxiangqu.shop.adapter.YourLikeRecyclerAdapter;
import com.linxiangqu.shop.bean.shopcar.DeleteCart;
import com.linxiangqu.shop.bean.shopcar.GetUserCartList;
import com.linxiangqu.shop.bean.shopcar.UpdateCart;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.widget.MyPopupWindowShopCar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by linxiangqu on 2016/7/25.
 */

@SuppressLint("ValidFragment")
public class ShopCarFragment extends BaseFragment implements IDefineView, View.OnClickListener {

    private View mView;
    private TextView price, delete, jiesuan, fragment_myshopcar_tv;
    private TextView mToolbar_tv;
    private ImageButton mToolbar_back;
    private LinearLayout mToolbar_qx, mToolbar;
    private CheckBox mToolbar_qx_checkbox;
    private LinearLayout fragment_myshopcar_nothing;
    private RecyclerView order, yourLike;
    private MyShopCarRecyclerAdapter myShopCarRecyclerAdapter;
    private YourLikeRecyclerAdapter yourLikeRecyclerAdapter;
    private MyPopupWindowShopCar popupwindow = null;
    private int fHeight;
    private AnimatorSet showAnimEnter, showAnimExit;
    private long first, end;
    private final int time = 800;
    private Double d = 0.0, allPrice;
    private boolean qx = false;
    private HashMap<String, String> cartId = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_myshopcar, container, false);
            mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    fHeight = mView.getHeight();
                    mView.getViewTreeObserver()
                            .removeOnGlobalLayoutListener(this);
                }
            });
            initView();
            initToolbar();
            bindData();
            initData();
            initListener();
            setStatusBar(mToolbar);
        }
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        bindData();
    }

    @Override
    public void initView() {
        fragment_myshopcar_tv = (TextView) mView.findViewById(R.id.fragment_myshopcar_tv);
        price = (TextView) mView.findViewById(R.id.fragment_myshopcar_price);
        delete = (TextView) mView.findViewById(R.id.fragment_myshopcar_delete);
        jiesuan = (TextView) mView.findViewById(R.id.fragment_myshopcar_jiesuan);
        fragment_myshopcar_nothing = (LinearLayout) mView.findViewById(R.id.fragment_myshopcar_nothing);
        order = (RecyclerView) mView.findViewById(R.id.fragment_myshopcar_order);
        yourLike = (RecyclerView) mView.findViewById(R.id.fragment_myshopcar_your_like);
    }

    @Override
    public void initToolbar() {
        mToolbar = (LinearLayout) mView.findViewById(R.id.toolbar);
        mToolbar_back = (ImageButton) mView.findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) mView.findViewById(R.id.toolbar_tv);
        mToolbar_qx = (LinearLayout) mView.findViewById(R.id.toolbar_qx);
        mToolbar_qx_checkbox = (CheckBox) mView.findViewById(R.id.toolbar_qx_checkbox);
        mToolbar_back.setVisibility(View.GONE);
        mToolbar_qx.setVisibility(View.VISIBLE);
        mToolbar_tv.setText(R.string.gwc);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_myshopcar_delete:
                deleteCartGoods();
                break;
            case R.id.fragment_myshopcar_jiesuan:
                showToasts("fragment_myshopcar_jiesuan");
                break;
            default:
                break;
        }
    }

    private void deleteCartGoods() {
        cartId = myShopCarRecyclerAdapter.getCartId();
        OKHttpManager.postDeleteCart(Config.DELETE_CART, cartId, new SpotsCallBack<DeleteCart>(mActivity) {
            @Override
            public void onSuccess(Call call, Response response, DeleteCart deleteCart) {
                if (deleteCart.getStateCode() == 2205) {
                    bindData();
                    price.setText("0.0");
                    cartId.clear();
                }
                showToasts(deleteCart.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        delete.setOnClickListener(this);
        jiesuan.setOnClickListener(this);
    }

    public void listener(final GetUserCartList getUserCartList) {
        mToolbar_qx_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    qx = b;
                    d = 0.0;
                    myShopCarRecyclerAdapter.setChoose(b, -1);
                    List<GetUserCartList.UserCartDTOsBean> mList = getUserCartList.getUserCartDTOs();
                    for (int i = 0; i < mList.size(); i++) {
                        d = d + mList.get(i).getGoodsStorePrice() * Double.valueOf(mList.get(i).getGoodsNum());
                    }
                    allPrice = d;
                    price.setText(String.valueOf(d));
                } else {
                    qx = b;
                    myShopCarRecyclerAdapter.setChoose(b, -1);
                    d = 0.0;
                    price.setText(String.valueOf(d));
                }
            }
        });
        myShopCarRecyclerAdapter.setOnItemClickListener(new MyShopCarRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemChild(View view, int position, GetUserCartList.UserCartDTOsBean userCartDTOs, GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTO, boolean flag, boolean allChoose) {
                switch (view.getId()) {
                    case R.id.fragment_myshopcar_item_user_order:
                        Intent intent = new Intent(mActivity, GoodsActivity.class);
                        intent.putExtra("goodsId", userCartDTOs.getGoodsId());
                        startActivity(intent);
                        mToolbar_qx_checkbox.setChecked(false);
                        break;
                    case R.id.fragment_myshopcar_item_user_order_checkbox:
                        if (qx) {
                            if (!flag) {
                                mToolbar_qx_checkbox.setChecked(false);
                                myShopCarRecyclerAdapter.setChoose(true, position);
                                d = allPrice - userCartDTOs.getGoodsStorePrice() * Double.valueOf(userCartDTOs.getGoodsNum());
                                price.setText(String.valueOf(d));
                            }
                        } else {
                            if (flag) {
                                d = d + userCartDTOs.getGoodsStorePrice() * Double.valueOf(userCartDTOs.getGoodsNum());
                                price.setText(String.valueOf(d));
                                if (allChoose)
                                    mToolbar_qx_checkbox.setChecked(true);
                            } else {
                                if (d != 0.0) {
                                    mToolbar_qx_checkbox.setChecked(flag);
                                    d = d - userCartDTOs.getGoodsStorePrice() * Double.valueOf(userCartDTOs.getGoodsNum());
                                    price.setText(String.valueOf(d));
                                }
                            }
                        }
                        break;
                    case R.id.fragment_myshopcar_item_user_order_bianji:
                        first = Calendar.getInstance().getTimeInMillis();
                        TextView textView = (TextView) view.findViewById(R.id.fragment_myshopcar_item_user_order_bianji);
                        textView.setClickable(false);
                        if (popupwindow == null) {
                            initShowAnim();
                            initPopupWindow(userCartDTOs, goodsSpecDTO, position, textView);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        yourLikeRecyclerAdapter.setOnItemClickListener(new YourLikeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemChild(View view, int position, GetUserCartList.GuessYouLikeListBean guessYouLikeList) {
                Intent intent = new Intent(mActivity, GoodsActivity.class);
                intent.putExtra("goodsId", guessYouLikeList.getGoodsId());
                startActivity(intent);
                mToolbar_qx_checkbox.setChecked(false);
            }
        });
    }

    private void initPopupWindow(GetUserCartList.UserCartDTOsBean userCartDTOs, GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTO, int position, final TextView textView) {
        popupwindow = new MyPopupWindowShopCar(mActivity, userCartDTOs, goodsSpecDTO, position).showPop();
        popupwindow.setOnMediaClick(new MyPopupWindowShopCar.OnMediaClick() {
            @Override
            public void onOkClick(GetUserCartList.UserCartDTOsBean userCartDTOs, GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTO, int position, boolean flags, PopupWindow mPopupWindow) {
                end = Calendar.getInstance().getTimeInMillis();
                if ((end - first) > time) {
                    if (null != mPopupWindow && mPopupWindow.isShowing()) {
                        if (flags)
                            updateCart(userCartDTOs, goodsSpecDTO, position);
                        initHiddenAnim();
                        mPopupWindow.dismiss();
                        popupwindow = null;
                        textView.setClickable(true);
                    }
                }
            }
        });

        popupwindow.setmOnTouchBack(new MyPopupWindowShopCar.OnTouchBack() {
            @Override
            public void onTouch(View v, MotionEvent event, PopupWindow mPopupWindow) {
                end = Calendar.getInstance().getTimeInMillis();
                if ((end - first) > time)
                    if (null != mPopupWindow && mPopupWindow.isShowing()) {
                        initHiddenAnim();
                        mPopupWindow.dismiss();
                        popupwindow = null;
                        textView.setClickable(true);
                    }
            }
        });
    }

    private void updateCart(final GetUserCartList.UserCartDTOsBean userCartDTOs, final GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTO, final int position) {
        OKHttpManager.postUpdateCart(Config.UPDATE_CART, Config.getCacheUserId(mActivity), userCartDTOs.getCartId(), userCartDTOs.getGoodsStorePrice(), userCartDTOs.getSpecId(), userCartDTOs.getGoodsId(), userCartDTOs.getGoodsNum(), userCartDTOs.getStoreId(), userCartDTOs.getSpecGoodsColor(), new SpotsCallBack<UpdateCart>(mActivity) {
            @Override
            public void onSuccess(Call call, Response response, UpdateCart updateCart) {
                if (updateCart.getStateCode() == 0) {
                    myShopCarRecyclerAdapter.upData(userCartDTOs, goodsSpecDTO, position);
                    mToolbar_qx_checkbox.setChecked(false);
                }
                showToasts(updateCart.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @Override
    public void bindData() {
        fragment_myshopcar_tv.setVisibility(View.GONE);
        fragment_myshopcar_nothing.setVisibility(View.GONE);
        OKHttpManager.postGetUserCartList(Config.GET_USER_CART_LIST, Config.getCacheUserId(getActivity()), new SpotsCallBack<GetUserCartList>(getActivity()) {
            @Override
            public void onSuccess(Call call, Response response, GetUserCartList getUserCartList) {
                List<GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean> goodsSpecDTOs = new ArrayList<>();
                if (getUserCartList.getStateCode() == 0) {
                    for (GetUserCartList.UserCartDTOsBean userCartDTOsBean : getUserCartList.getUserCartDTOs())
                        for (GetUserCartList.UserCartDTOsBean.GoodsSpecDTOBean goodsSpecDTOBean : userCartDTOsBean.getGoodsSpecDTO())
                            if (goodsSpecDTOBean.getSpecId() == userCartDTOsBean.getSpecId())
                                goodsSpecDTOs.add(goodsSpecDTOBean);
                    if (getUserCartList.getUserCartDTOs().size() == 0)
                        fragment_myshopcar_nothing.setVisibility(View.VISIBLE);
                    else
                        fragment_myshopcar_nothing.setVisibility(View.GONE);
                    myShopCarRecyclerAdapter = new MyShopCarRecyclerAdapter(getActivity(), getUserCartList.getUserCartDTOs(), goodsSpecDTOs);
                    order.setLayoutManager(new LinearLayoutManager(getActivity()));
                    order.setAdapter(myShopCarRecyclerAdapter);
                    yourLikeRecyclerAdapter = new YourLikeRecyclerAdapter(getActivity(), getUserCartList.getGuessYouLikeList());
                    yourLike.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    yourLike.setAdapter(yourLikeRecyclerAdapter);
                    listener(getUserCartList);
                } else
                    showToasts(getUserCartList.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                fragment_myshopcar_tv.setVisibility(View.VISIBLE);
                showErrorToasts(call, response);
            }
        });
    }

    private void initShowAnim() {
        ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(mView, "scaleX", 1.0f, 0.8f);
        fViewScaleXAnim.setDuration(550);
        ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(mView, "scaleY", 1.0f, 0.8f);
        fViewScaleYAnim.setDuration(550);
        ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(mView, "alpha", 1.0f, 0.5f);
        fViewAlphaAnim.setDuration(550);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(mView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(400);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(mView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(350);
        fViewResumeAnim.setStartDelay(400);
        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(mView, "translationY", 0, -0.1f * fHeight);
        fViewTransYAnim.setDuration(550);
//        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(secondView, "translationY", sHeight, 0);
//        sViewTransYAnim.setDuration(350);
//        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                secondView.setVisibility(View.VISIBLE);
//            }
//        });
        showAnimEnter = new AnimatorSet();
        showAnimEnter.playTogether(fViewScaleXAnim, fViewRotationXAnim, fViewResumeAnim, fViewTransYAnim, fViewAlphaAnim, fViewScaleYAnim);
        showAnimEnter.start();
    }

    private void initHiddenAnim() {
        ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(mView, "scaleX", 0.8f, 1.0f);
        fViewScaleXAnim.setDuration(400);
        ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(mView, "scaleY", 0.8f, 1.0f);
        fViewScaleYAnim.setDuration(400);
        ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(mView, "alpha", 0.5f, 1.0f);
        fViewAlphaAnim.setDuration(400);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(mView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(250);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(mView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(200);
        fViewResumeAnim.setStartDelay(250);
        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(mView, "translationY", -0.1f * fHeight, 0);
        fViewTransYAnim.setDuration(400);
//        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(secondView, "translationY", 0, sHeight);
//        sViewTransYAnim.setDuration(350);
//        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                secondView.setVisibility(View.INVISIBLE);
//            }
//        });
        showAnimExit = new AnimatorSet();
        showAnimExit.playTogether(fViewScaleXAnim, fViewRotationXAnim, fViewResumeAnim, fViewTransYAnim, fViewAlphaAnim, fViewScaleYAnim);
        showAnimExit.start();
    }
}
