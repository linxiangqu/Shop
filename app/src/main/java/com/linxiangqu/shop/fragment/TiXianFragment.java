package com.linxiangqu.shop.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.AddBankOrAlipayActivity;
import com.linxiangqu.shop.activity.TiXianHistoryActivity;
import com.linxiangqu.shop.activity.UserBankAndAlipayActivity;
import com.linxiangqu.shop.bean.good.QueryCashOutIndex;
import com.linxiangqu.shop.bean.good.SubmitCashOut;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by linxiangqu on 2016/8/12.
 */
public class TiXianFragment extends BaseFragment implements View.OnClickListener, IDefineView {
    private Context mContext;
    private String money;
    private View mView;
    private TextView money_tx, name, zh, nothing;
    private LinearLayout bankoralipay;
    private RelativeLayout addBand, tx, lsjv;
    private EditText money_edt;
    private int selector;
    private AlertDialog alertDialog = null;
    private ImageView img;
    private boolean haveCard = false;
    private int cardId = -1;

    public static TiXianFragment newInstance(Context context, String money) {
        TiXianFragment tiXianFragment = new TiXianFragment();
        tiXianFragment.mContext = context;
        Bundle bundle = new Bundle();
        bundle.putString(Config.KEY_TITLE, money);
        tiXianFragment.setArguments(bundle);
        return tiXianFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            money = getArguments().getString(Config.KEY_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.from(mActivity).inflate(R.layout.fragment_myassets_tixian, container, false);
            initView();
            initData();
            bindData();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        money_tx = (TextView) mView.findViewById(R.id.fragment_myassets_tixian_money);
        addBand = (RelativeLayout) mView.findViewById(R.id.fragment_myassets_tixian_relativelayout_bank);
        tx = (RelativeLayout) mView.findViewById(R.id.fragment_myassets_tixian_relativelayout_tx);
        lsjv = (RelativeLayout) mView.findViewById(R.id.fragment_myassets_tixian_relativelayout_lsjl);
        money_edt = (EditText) mView.findViewById(R.id.fragment_myassets_tixian_money_edt);
        bankoralipay = (LinearLayout) mView.findViewById(R.id.bankoralipay);
        img = (ImageView) mView.findViewById(R.id.fragment_myassets_tixian_addbank);
        name = (TextView) mView.findViewById(R.id.name);
        zh = (TextView) mView.findViewById(R.id.zh);
        nothing = (TextView) mView.findViewById(R.id.nothing_bankoralipay);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        addBand.setOnClickListener(this);
        tx.setOnClickListener(this);
        lsjv.setOnClickListener(this);
    }

    @Override
    public void bindData() {
        bankoralipay.setVisibility(View.GONE);
        nothing.setVisibility(View.GONE);
        OKHttpManager.postQueryCashOutIndex(Config.QUERY_CASH_OUT_INDEX, Config.getCacheUserId(mActivity), new SpotsCallBack<QueryCashOutIndex>(mActivity) {
            @Override
            public void onSuccess(Call call, Response response, QueryCashOutIndex queryCashOutIndex) {
                if (queryCashOutIndex.getStateCode() == 0) {
                    if (queryCashOutIndex.getCardName() != null) {
                        haveCard = true;
                        cardId = queryCashOutIndex.getCardId();
                        bankoralipay.setVisibility(View.VISIBLE);
                        money_tx.setText(queryCashOutIndex.getCut() + "");
                        if (queryCashOutIndex.getCashOutType() == 1) {
                            name.setText(queryCashOutIndex.getCardCompany());
                            zh.setText("尾号" + queryCashOutIndex.getCardNo());
                            img.setBackgroundResource(R.mipmap.yinghang_sj);
                        } else if (queryCashOutIndex.getCashOutType() == 2) {
                            zh.setText(queryCashOutIndex.getCardNo());
                            img.setBackgroundResource(R.mipmap.icon_alipay);
                            name.setText(queryCashOutIndex.getCardName());
                        }
                        money_edt.setHint("提现为" + queryCashOutIndex.getCashLimit() + "的倍数");
                    } else {
                        money_edt.setHint("提现为10.0的倍数");
                        img.setBackgroundResource(R.mipmap.icon_bank_add);
                        nothing.setVisibility(View.VISIBLE);
                    }
                } else
                    showToasts(queryCashOutIndex.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
                money_edt.setHint("提现为10.0的倍数");
                img.setBackgroundResource(R.mipmap.icon_bank_add);
                nothing.setVisibility(View.VISIBLE);
                money_tx.setText("0.0");
            }

            @Override
            public void onFailure(Call call, IOException e) {
                super.onFailure(call, e);
                money_edt.setHint("提现为10.0的倍数");
                img.setBackgroundResource(R.mipmap.icon_bank_add);
                nothing.setVisibility(View.VISIBLE);
                money_tx.setText("0.0");
            }
        });
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_myassets_tixian_relativelayout_bank:
                if (haveCard)
                    startActivityForResult(new Intent(mActivity, UserBankAndAlipayActivity.class), 1);
                else
                    initDialog();
                break;
            case R.id.fragment_myassets_tixian_relativelayout_tx:
                csahOut();
                break;
            case R.id.fragment_myassets_tixian_relativelayout_lsjl:
                openActivity(TiXianHistoryActivity.class);
                break;
            default:
                break;
        }
    }

    private void csahOut() {
        OKHttpManager.postSubmitCashOut(Config.SUBMIT_CASH_OUT, Config.getCacheUserId(mActivity), money_edt.getText().toString().trim(), cardId, new SpotsCallBack<SubmitCashOut>(mActivity) {
            @Override
            public void onSuccess(Call call, Response response, SubmitCashOut submitCashOut) {
                money_edt.setText("");
                showToasts(submitCashOut.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    private void initDialog() {
        final String string[] = {"银行卡", "支付宝"};
        if (alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setTitle("请选择绑定类型！");
            builder.setSingleChoiceItems(string, selector, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    selector = i;
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(mActivity, AddBankOrAlipayActivity.class);
                    intent.putExtra("selector", selector + 1);
                    startActivityForResult(intent, 1);
                    selector = 0;
                    alertDialog = null;
                }
            });
            alertDialog = builder.create();
        }
        alertDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
            if (resultCode == 1 || resultCode == 2 || resultCode == 3) {
                bindData();
            }
    }
}
