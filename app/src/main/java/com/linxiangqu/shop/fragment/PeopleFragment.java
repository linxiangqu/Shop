package com.linxiangqu.shop.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.activity.FavoritesActivity;
import com.linxiangqu.shop.activity.InviteCodeActivity;
import com.linxiangqu.shop.activity.MyAssetsActivity;
import com.linxiangqu.shop.activity.MyPlatformActivity;
import com.linxiangqu.shop.activity.OrderActivity;
import com.linxiangqu.shop.activity.SettingActivity;
import com.linxiangqu.shop.activity.UserActivity;
import com.linxiangqu.shop.bean.user.GetUserMainMsg;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.utils.PicassoTransformationUtils;
import com.linxiangqu.shop.utils.UnreadMsgUtils;
import com.linxiangqu.shop.widget.MsgView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by linxiangqu on 2016/7/25.
 */
public class PeopleFragment extends BaseFragment implements IDefineView, View.OnClickListener {

    private View mView;
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private ImageView fragment_people_photo;
    private TextView fragment_people_name, fragment_people_wdtc_tv, fragment_people_wdjf_tv, fragment_people_sqtx_tv;
    private RelativeLayout fragment_people_wdzc, fragment_people_wddd, fragment_people_wdpt, fragment_people_wdyqm, fragment_people_wdscj;
    private LinearLayout mToolbar, fragment_people_wdtc, fragment_people_wdjf, fragment_people_sqtx, fragment_people_dzf, fragment_people_dfh, fragment_people_dsh, fragment_people_dpj, fragment_people_tkth;
    private MsgView dzf, dfh, dsh, dpj, tkth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_people, null);
            EventBus.getDefault().register(this);
            initView();
            initToolbar();
            initListener();
            initData();
            bindData();
            setStatusBar(mToolbar);
        }
        return mView;
    }

    @Override
    public void initView() {
        fragment_people_photo = (ImageView) mView.findViewById(R.id.fragment_people_photo);
        fragment_people_name = (TextView) mView.findViewById(R.id.fragment_people_name);

        fragment_people_wdpt = (RelativeLayout) mView.findViewById(R.id.fragment_people_wdpt);
        fragment_people_wdyqm = (RelativeLayout) mView.findViewById(R.id.fragment_people_wdyqm);
        fragment_people_wdscj = (RelativeLayout) mView.findViewById(R.id.fragment_people_wdscj);

        fragment_people_wdzc = (RelativeLayout) mView.findViewById(R.id.fragment_people_wdzc);
        fragment_people_wdtc = (LinearLayout) mView.findViewById(R.id.fragment_people_wdtc);
        fragment_people_wdjf = (LinearLayout) mView.findViewById(R.id.fragment_people_wdjf);
        fragment_people_sqtx = (LinearLayout) mView.findViewById(R.id.fragment_people_sqtx);

        fragment_people_wdtc_tv = (TextView) mView.findViewById(R.id.fragment_people_wdtc_tv);
        fragment_people_wdjf_tv = (TextView) mView.findViewById(R.id.fragment_people_wdjf_tv);
        fragment_people_sqtx_tv = (TextView) mView.findViewById(R.id.fragment_people_sqtx_tv);

        fragment_people_wddd = (RelativeLayout) mView.findViewById(R.id.fragment_people_wddd);
        fragment_people_dzf = (LinearLayout) mView.findViewById(R.id.fragment_people_dzf);
        fragment_people_dfh = (LinearLayout) mView.findViewById(R.id.fragment_people_dfh);
        fragment_people_dsh = (LinearLayout) mView.findViewById(R.id.fragment_people_dsh);
        fragment_people_dpj = (LinearLayout) mView.findViewById(R.id.fragment_people_dpj);
        fragment_people_tkth = (LinearLayout) mView.findViewById(R.id.fragment_people_tkth);

        dzf = (MsgView) mView.findViewById(R.id.fragment_people_dzf_tv);
        dfh = (MsgView) mView.findViewById(R.id.fragment_people_dfh_tv);
        dsh = (MsgView) mView.findViewById(R.id.fragment_people_dsh_tv);
        dpj = (MsgView) mView.findViewById(R.id.fragment_people_dpj_tv);
        tkth = (MsgView) mView.findViewById(R.id.fragment_people_tkth_tv);
    }

    @Override
    public void initData() {
        fragment_people_name.setText(Config.getCacheUsername(mActivity));
    }

    @Override
    public void initListener() {
        fragment_people_photo.setOnClickListener(this);

        fragment_people_wdpt.setOnClickListener(this);
        fragment_people_wdyqm.setOnClickListener(this);
        fragment_people_wdscj.setOnClickListener(this);

        fragment_people_wdzc.setOnClickListener(this);
        fragment_people_wdtc.setOnClickListener(this);
        fragment_people_wdjf.setOnClickListener(this);
        fragment_people_sqtx.setOnClickListener(this);

        fragment_people_wddd.setOnClickListener(this);
        fragment_people_dzf.setOnClickListener(this);
        fragment_people_dfh.setOnClickListener(this);
        fragment_people_dsh.setOnClickListener(this);
        fragment_people_dpj.setOnClickListener(this);
        fragment_people_tkth.setOnClickListener(this);
    }

    /**
     * @param msg== “Login” 登陆
     *              “Photo” 头像修改
     */
    @Subscribe
    public void onEventMainThread(String msg) {
        if (msg.equals("Login") || msg.equals("Photo") || msg.equals("Order")) {
            bindData();
        }
    }

    @Override
    public void bindData() {
        if (Config.getCacheUserId(mActivity) == 33)
            fragment_people_photo.setBackgroundResource(R.mipmap.dengluzhuce_sj);
        OKHttpManager.postGetUserMainMsg(Config.GET_USER_MAIN_MAS, Config.getCacheUserId(mActivity), new SpotsCallBack<GetUserMainMsg>(mActivity) {
            @Override
            public void onSuccess(Call call, Response response, GetUserMainMsg getUserMainMsg) {
                if (getUserMainMsg.getStateCode() == 0) {
                    Picasso.with(mActivity).load(Config.IP + getUserMainMsg.getUserImg()).fit().centerCrop().error(R.mipmap.denglubackground_sj).transform(new PicassoTransformationUtils()).into(fragment_people_photo);
                    fragment_people_wdtc_tv.setText(getUserMainMsg.getCut() + "");
                    fragment_people_wdjf_tv.setText(getUserMainMsg.getScore() + "");
                    fragment_people_sqtx_tv.setText(getUserMainMsg.getApplyCashCount() + "");

                    UnreadMsgUtils.show(dzf, getUserMainMsg.getNotPayCount());
                    UnreadMsgUtils.show(dfh, getUserMainMsg.getWaitSellerSendGoods());
                    UnreadMsgUtils.show(dsh, getUserMainMsg.getWaitBuyerConfirmGoods());
                    UnreadMsgUtils.show(dpj, getUserMainMsg.getBuyerConfirmGoods());
                    UnreadMsgUtils.show(tkth, getUserMainMsg.getReturnCheckOrReturning());
                } else
                    showToasts(getUserMainMsg.getMsg());
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
        mToolbar = (LinearLayout) mView.findViewById(R.id.toolbar);
        mToolbar_back = (ImageButton) mView.findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) mView.findViewById(R.id.toolbar_tv);
        mToolbar_tv.setVisibility(View.GONE);
        mToolbar_back.setBackgroundResource(R.mipmap.shezhi_sj);
        mToolbar.setBackground(getResources().getDrawable(R.color.bg));
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                openActivityForResult(SettingActivity.class, 1);
                break;
            case R.id.fragment_people_photo:
                openActivity(UserActivity.class);
                break;
            case R.id.fragment_people_wdzc:
            case R.id.fragment_people_wdtc:
            case R.id.fragment_people_wdjf:
            case R.id.fragment_people_sqtx:
                Intent wdzcIntent = new Intent(mActivity, MyAssetsActivity.class);
                wdzcIntent.putExtra("page", Integer.valueOf(view.getTag().toString()));
                wdzcIntent.putExtra("money_tc", fragment_people_wdtc_tv.getText().toString().trim());
                wdzcIntent.putExtra("money_jf", fragment_people_wdjf_tv.getText().toString().trim());
                wdzcIntent.putExtra("money_tx", fragment_people_sqtx_tv.getText().toString().trim());
                startActivity(wdzcIntent);
                break;
            case R.id.fragment_people_wddd:
            case R.id.fragment_people_dzf:
            case R.id.fragment_people_dfh:
            case R.id.fragment_people_dsh:
            case R.id.fragment_people_dpj:
            case R.id.fragment_people_tkth:
                Intent intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtra("page", Integer.valueOf(view.getTag().toString()));
                startActivity(intent);
                break;
            case R.id.fragment_people_wdpt:
                openActivity(MyPlatformActivity.class);
                break;
            case R.id.fragment_people_wdyqm:
                openActivity(InviteCodeActivity.class);
                break;
            case R.id.fragment_people_wdscj:
                openActivity(FavoritesActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
