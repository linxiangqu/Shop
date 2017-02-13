package com.linxiangqu.shop.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.linxiangqu.shop.bean.platform.SaveReply;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class ReplyActivity extends BaseActivity {

    @BindView(R.id.toolbar_back)
    ImageButton toolbarBack;
    @BindView(R.id.toolbar_tv)
    TextView toolbarTv;
    @BindView(R.id.activity_reply_content)
    EditText activityReplyContent;
    @BindView(R.id.activity_reply_isanonymity)
    CheckBox activityReplyIsanonymity;
    @BindView(R.id.activity_reply_submit)
    Button activityReplySubmit;
    private int bbsId, replyId;
    private AlertDialog alertDialog;
    private boolean isAnonymity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        ButterKnife.bind(this);
        activityReplyIsanonymity.setChecked(false);
        initData();
        initToolbar();
        initListener();
        setStatusBar();
    }

    public void initData() {
        bbsId = getIntent().getIntExtra("bbsId", -1);
        replyId = getIntent().getIntExtra("replyId", -1);
    }

    public void initToolbar() {
        toolbarTv.setText("发评论");
    }


    private void initListener() {
        activityReplyIsanonymity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    isAnonymity = isChecked;
                else
                    isAnonymity = isChecked;
            }
        });
    }

    private void bindData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("bbsId", bbsId + "");
        params.put("userId", Config.getCacheUserId(this) + "");
        params.put("replyContent", activityReplyContent.getText().toString().trim());
        params.put("isAnonymity", isAnonymity + "");
        if (activityReplyContent.getText().toString().trim().isEmpty()) {
            showToasts("请填写内容！！！");
            return;
        }
        if (replyId != -1) {
            params.put("replyId", replyId + "");
        }
        OKHttpManager.postSaveReply(Config.SAVE_REPLY, params, new SpotsCallBack<SaveReply>(this) {
            @Override
            public void onSuccess(Call call, Response response, SaveReply saveReply) {
                if (saveReply.getStateCode() == 2203) {
                    EventBus.getDefault().post("reply");
                    EventBus.getDefault().post("replyed");
                    finish();
                } else
                    showToasts(saveReply.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @OnClick({R.id.toolbar_back, R.id.activity_reply_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                exit();
                break;
            case R.id.activity_reply_submit:
                bindData();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return false;
    }
}
