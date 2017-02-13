package com.linxiangqu.shop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.MyOrderRecyclerAdapter;
import com.linxiangqu.shop.bean.order.CancelOrder;
import com.linxiangqu.shop.bean.order.ConfirmBuyerGoods;
import com.linxiangqu.shop.bean.order.DeleteOrder;
import com.linxiangqu.shop.bean.order.QueryOrderList;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by linxiangqu on 2016/8/1.
 */
public class OrderFrament extends BaseFragment implements IDefineView, View.OnClickListener {

    private View mView;
    private int orderState;
    private RecyclerView fragment_myorder_recyclerview;
    private LinearLayout fragment_myorder_linearlayout;
    private TextView fragment_myorder_linearlayout_tv;
    private MyOrderRecyclerAdapter myOrderRecyclerAdapter;
    private List<Object> mList = new ArrayList<>();
    private Context context;
    private int x, Tag;
    private Gson gson;
    private DeleteOrder deleteOrder = new DeleteOrder();
    private CancelOrder cancelOrder = new CancelOrder();
    private ConfirmBuyerGoods confirmBuyerGoods = new ConfirmBuyerGoods();

    public static OrderFrament newInstance(Context context, int orderState) {
        OrderFrament fragment = new OrderFrament();
        fragment.context = context;
        Bundle args = new Bundle();
        args.putInt(Config.KEY_TITLE, orderState);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderState = getArguments().getInt(Config.KEY_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_myorder, container, false);
            gson = new Gson();
            initView();
            initData();
            bindData();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        fragment_myorder_recyclerview = (RecyclerView) mView.findViewById(R.id.fragment_myorder_recyclerview);
        fragment_myorder_linearlayout = (LinearLayout) mView.findViewById(R.id.fragment_myorder_linearlayout);
        fragment_myorder_linearlayout_tv = (TextView) mView.findViewById(R.id.fragment_myorder_linearlayout_tv);

    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        OKHttpManager.postQueryOrderList(Config.QUERY_ORDER_LIST, Config.getCacheUserId(mActivity), 1, 20, orderState, new SpotsCallBack<QueryOrderList>(mActivity) {
            @Override
            public void onSuccess(Call call, Response response, QueryOrderList queryOrderList) {
                int i = 0, k = 0;
                if (queryOrderList.getMallOrderGoodsListDTOs().size() != 0) {
                    for (QueryOrderList.MallOrderGoodsListDTOsBean mallOrderGoodsListDTOsBean : queryOrderList.getMallOrderGoodsListDTOs()) {
                        i = i + mallOrderGoodsListDTOsBean.getMallOrderGoodsInfoDTOs().size() + 1;
                        for (int i1 = 0; i1 <= mallOrderGoodsListDTOsBean.getMallOrderGoodsInfoDTOs().size(); i1++) {
                            if (k == i - 1)
                                mList.add(mallOrderGoodsListDTOsBean);
                            else {
                                mList.add(mallOrderGoodsListDTOsBean.getMallOrderGoodsInfoDTOs().get(i1));
                            }
                            k++;
                        }
                    }
                } else
                    fragment_myorder_linearlayout.setVisibility(View.VISIBLE);

                call.cancel();

                myOrderRecyclerAdapter = new MyOrderRecyclerAdapter(getActivity(), mList);
                fragment_myorder_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                fragment_myorder_recyclerview.setAdapter(myOrderRecyclerAdapter);
                myOrderRecyclerAdapter.setmOnItemChildClickListener(new MyOrderRecyclerAdapter.OnItemChildClickListener() {
                    @Override
                    public void OnItenChild(View view, Object object, int position) {
                        switch (view.getId()) {
                            case R.id.fragment_myorder_item:
                                Toast.makeText(context, ((QueryOrderList.MallOrderGoodsListDTOsBean) object).getOrderAllAmount() + "", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.fragment_myorder_item_item:
                                Toast.makeText(context, ((QueryOrderList.MallOrderGoodsListDTOsBean.MallOrderGoodsInfoDTOsBean) object).getGoodsId() + "", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.fragment_myorder_item_scdd:
                            case R.id.fragment_myorder_item_ckwl:
                            case R.id.fragment_myorder_item_pj:
                            case R.id.fragment_myorder_item_qxdd:
                            case R.id.fragment_myorder_item_fk:
                            case R.id.fragment_myorder_item_tk:
                            case R.id.fragment_myorder_item_tkth:
                            case R.id.fragment_myorder_item_qrsh:
                            case R.id.fragment_myorder_item_tkthck:
                                orderRequest(((QueryOrderList.MallOrderGoodsListDTOsBean) object), position, Integer.valueOf(view.getTag().toString()));
                                break;
                            default:
                                break;
                        }
                    }
                });
            }

            @Override
            public void onError(Call call, Response response) {
                fragment_myorder_linearlayout.setVisibility(View.VISIBLE);
                showErrorToasts(call, response);
            }
        });
    }

    private void orderRequest(final QueryOrderList.MallOrderGoodsListDTOsBean orders, int position, int tag) {
        x = position;
        String url = "";
        if (tag == 0) {
            //删除订单
            url = Config.DELETE_ORDER;
        }
        if (tag == 1) {
            //获取订单物流单号
            url = Config.QUERY_ORDER_SHIPPING_CODE;
        }
        if (tag == 2) {
            //立即评价
            url = Config.ADD_EVALUATION;
        }
        if (tag == 3) {
            //订单取消
            url = Config.CANCEL_ORDER;
        }
        if (tag == 4) {
            //订单支付
            url = Config.ORDER_PAY;
        }
        if (tag == 5 || tag == 6) {
            //退款退货申请
            url = Config.APPLY_REFUND_OR_RETURN;
        }
        if (tag == 7) {
            //订单确认收货
            url = Config.CONFIRM_BUYER_GOODS;
        }
        if (tag == 8) {
            //退快退货详情及协商详情
            url = Config.GET_REFUND_OR_RETURN_INFO;
        }
        Tag = tag;
        if (tag == 0 || tag == 3 || Tag == 7)
            OKHttpManager.postOrder(url, orders.getOrderId(), new SpotsCallBack<String>(mActivity) {
                @Override
                public void onSuccess(Call call, Response response, String s) {
                    Log.i("asdasdasd", Tag + "------" + s);
                    showToasts(Tag + "------" + s);
                    if (Tag == 0) {
                        deleteOrder = gson.fromJson(s, DeleteOrder.class);
                        if (deleteOrder.getStateCode() == 2201) {
                            for (int i = 0; i <= orders.getOrderAllAmount(); i++) {
                                mList.remove(x);
                                x--;
                            }
                            myOrderRecyclerAdapter.setmList(mList);
                            EventBus.getDefault().post("Order");
                        }
                        showToasts(deleteOrder.getMsg());
                    }
                    if (Tag == 3) {
                        cancelOrder = gson.fromJson(s, CancelOrder.class);
                        if (cancelOrder.getStateCode() == 2205) {
                            for (int i = 0; i <= orders.getOrderAllAmount(); i++) {
                                mList.remove(x);
                                x--;
                            }
                            myOrderRecyclerAdapter.setmList(mList);
                            EventBus.getDefault().post("Order");
                        }
                        showToasts(cancelOrder.getMsg());
                    }
                    if (Tag == 7) {
                        confirmBuyerGoods = gson.fromJson(s, ConfirmBuyerGoods.class);
                        if (confirmBuyerGoods.getStateCode() == 0) {
                            for (int i = 0; i <= orders.getOrderAllAmount(); i++) {
                                mList.remove(x);
                                x--;
                            }
                            myOrderRecyclerAdapter.setmList(mList);
                            EventBus.getDefault().post("Order");
                        }
                        showToasts(confirmBuyerGoods.getMsg());
                    }
                    call.cancel();
                }

                @Override
                public void onError(Call call, Response response) {
                    showErrorToasts(call, response);
                }
            });
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
