package com.linxiangqu.shop.widget;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by linxiangqu on 2016/8/23.
 */
public class DialogButtonHandler extends Handler {
    private WeakReference<DialogInterface> mDialog;

    public DialogButtonHandler(DialogInterface dialog) {
        mDialog = new WeakReference<>(dialog);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case DialogInterface.BUTTON_POSITIVE:
            case DialogInterface.BUTTON_NEGATIVE:
            case DialogInterface.BUTTON_NEUTRAL:
                ((DialogInterface.OnClickListener) msg.obj).onClick(mDialog
                        .get(), msg.what);
                break;
        }
    }
}
