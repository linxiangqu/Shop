package com.linxiangqu.shop.okhttp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import okhttp3.Call;

/**
 * Created by linxiangqu on 2016/8/18.
 */
public abstract class SpotsCallBack<T> extends BaseCallBack<T> {

    private SpotsDialog dialog;
    private Context mContext;

    public SpotsCallBack(Context context) {
        mContext = context;
        dialog = new SpotsDialog(mContext);
    }

    public void showDialog() {
        dialog.setMessage("................");
        if (dialog != null)
            dialog.show();
    }

    public void closeDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onBeforeRequest(String url, HashMap<String, String> params) {
        Log.i("onBeforeRequest", "------------------------------------------------------------------");
        Log.i("onBeforeRequest", "请求URL：" + url);
        for (Map.Entry<String, String> entry : params.entrySet())
            Log.i("onBeforeRequest", entry.getKey() + "：" + entry.getValue());
        Log.i("onBeforeRequest", "------------------------------------------------------------------");
        showDialog();
    }

    @Override
    public void onResponse() {
        closeDialog();
    }

    @Override
    public void onFailure(final Call call, final IOException e) {
        closeDialog();
        Log.e("onFailure", call + "---" + e);
        Toast.makeText(mContext, "网络发生异常！", Toast.LENGTH_SHORT).show();
        call.cancel();
    }
}
