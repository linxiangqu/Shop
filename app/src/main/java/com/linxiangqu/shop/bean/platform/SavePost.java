package com.linxiangqu.shop.bean.platform;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/9/20.
 */
public class SavePost implements Serializable {

    /**
     * stateCode : 2201
     * msg : 发帖成功
     */

    private int stateCode;
    private String msg;

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
