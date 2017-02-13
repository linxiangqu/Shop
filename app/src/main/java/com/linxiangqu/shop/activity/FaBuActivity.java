package com.linxiangqu.shop.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.platform.SaveMallGoodsZone;
import com.linxiangqu.shop.bean.platform.SavePost;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.photopicker.widget.MultiPickResultView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class FaBuActivity extends BaseActivity {

    @BindView(R.id.toolbar_back)
    ImageButton toolbarBack;
    @BindView(R.id.toolbar_tv)
    TextView toolbarTv;
    @BindView(R.id.activity_fabu_fbz)
    EditText activityFabuFbz;
    @BindView(R.id.activity_fabu_lxfs)
    EditText activityFabuLxfs;
    @BindView(R.id.activity_fabu_bt)
    EditText activityFabuBt;
    @BindView(R.id.activity_fabu_nr)
    EditText activityFabuNr;
    @BindView(R.id.activity_fabu_img_recycler)
    MultiPickResultView activityFabuImgRecycler;
    @BindView(R.id.activity_fabu_submit)
    Button activityFabuSubmit;

    private List<File> pics;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu);
        ButterKnife.bind(this);
        toolbarTv.setText("发布");
        setStatusBar();
        activityFabuImgRecycler.init(this, MultiPickResultView.ACTION_SELECT, null);
    }

    private void initRequest() {
        if (activityFabuFbz.getText().toString().trim().isEmpty() || activityFabuLxfs.getText().toString().trim().isEmpty() || activityFabuBt.getText().toString().trim().isEmpty() || activityFabuNr.getText().toString().trim().isEmpty()) {
            showToasts("请填写相关内容！！！");
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("goodsZoneTitle", activityFabuBt.getText().toString().trim());
        params.put("goodsZoneContent", activityFabuNr.getText().toString().trim());
        params.put("userId", Config.getCacheUserId(this) + "");
        params.put("goodsZoneTypeId", 0 + "");
        params.put("goodsZoneTypeName", "男女服装");
        params.put("goodsZoneMobile", activityFabuLxfs.getText().toString().trim() + "");
        params.put("goodsZoneLinkMan", activityFabuFbz.getText().toString().trim() + "");
        if (pics == null)
            pics = new ArrayList<>();
        OKHttpManager.postSaveMallGoodsZone(Config.SAVE_MALL_GOODS_ZONE, params, pics, new SpotsCallBack<SaveMallGoodsZone>(this) {
            @Override
            public void onSuccess(Call call, Response response, SaveMallGoodsZone saveMallGoodsZone) {
                if (saveMallGoodsZone.getStateCode() == 2201) {
                    EventBus.getDefault().post("shangPinQuFragment");
                    finish();
                } else
                    showToasts(saveMallGoodsZone.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @OnClick({R.id.toolbar_back, R.id.activity_fabu_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                exit();
                break;
            case R.id.activity_fabu_submit:
                initRequest();
                break;
        }
    }

    private void exit() {
        if (alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("注意");
            builder.setMessage("返回将不保存发布内容，是否继续返回？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("取消", null);
            alertDialog = builder.create();
        }
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        activityFabuImgRecycler.onActivityResult(requestCode, resultCode, data);
        pics = new ArrayList<>();
        for (int i = 0; i < activityFabuImgRecycler.getPhotos().size(); i++) {
            File file = new File(activityFabuImgRecycler.getPhotos().get(i));
            pics.add(file);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return false;
    }
}
