package com.linxiangqu.shop.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.platform.SavePost;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.photopicker.widget.MultiPickResultView;
import com.linxiangqu.shop.utils.Compressor;

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

public class FaTieActivity extends BaseActivity {

    @BindView(R.id.toolbar_back)
    ImageButton toolbarBack;
    @BindView(R.id.toolbar_tv)
    TextView toolbarTv;
    @BindView(R.id.activity_fatie_submit)
    Button activityFatieSubmit;
    @BindView(R.id.activity_fatie_title)
    EditText activityFatieTitle;
    @BindView(R.id.activity_fatie_content)
    EditText activityFatieContent;
    @BindView(R.id.activity_fatie_img_recycler)
    MultiPickResultView activityFatieImgRecycler;
    @BindView(R.id.activity_fatie_isPublic)
    CheckBox activityFatieIsPublic;
    @BindView(R.id.activity_fatie_isAnonymity)
    CheckBox activityFatieIsAnonymity;

    private boolean isPublic, isAnonymity;
    private List<File> pics;
    private AlertDialog alertDialog;
    private Compressor compressor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatie);
        ButterKnife.bind(this);
        compressor = Compressor.getDefault(this);
        toolbarTv.setText("发帖子");
        setStatusBar();
        activityFatieImgRecycler.init(this, MultiPickResultView.ACTION_SELECT, null);
        activityFatieIsPublic.setChecked(true);
        activityFatieIsAnonymity.setChecked(false);
        initListener();
    }

    private void initListener() {
        activityFatieIsPublic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    isPublic = isChecked;
                else
                    isPublic = isChecked;
            }
        });
        activityFatieIsAnonymity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    isAnonymity = isChecked;
                else
                    isAnonymity = isChecked;
            }
        });
    }

    private void initRequest() {
        if (activityFatieTitle.getText().toString().trim().isEmpty() || activityFatieContent.getText().toString().trim().isEmpty()) {
            showToasts("请输入标题和内容！！！");
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("title", activityFatieTitle.getText().toString().trim());
        params.put("content", activityFatieContent.getText().toString().trim());
        params.put("userId", Config.getCacheUserId(this) + "");
        params.put("isPublic", isPublic + "");
        params.put("isAnonymity", isAnonymity + "");
        if (pics == null)
            pics = new ArrayList<>();
        OKHttpManager.postSavePost(Config.SAVE_POST, params, pics, new SpotsCallBack<SavePost>(this) {
            @Override
            public void onSuccess(Call call, Response response, SavePost savePost) {
                if (savePost.getStateCode() == 2201) {
                    EventBus.getDefault().post("jiaoLiuQuFragment");
                    finish();
                } else
                    showToasts(savePost.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @OnClick({R.id.toolbar_back, R.id.activity_fatie_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                exit();
                break;
            case R.id.activity_fatie_submit:
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
        activityFatieImgRecycler.onActivityResult(requestCode, resultCode, data);
        pics = new ArrayList<>();
        for (int i = 0; i < activityFatieImgRecycler.getPhotos().size(); i++) {
            File file = new File(activityFatieImgRecycler.getPhotos().get(i));
            Log.i("图片压缩前大小：", file.length() + "");
            pics.add(compressor.compressToFile(file));
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
