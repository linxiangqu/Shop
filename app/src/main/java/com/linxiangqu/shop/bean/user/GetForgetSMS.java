package com.linxiangqu.shop.bean.user;

import java.io.Serializable;


public class GetForgetSMS implements Serializable {

    /**
     * stateCode : 2203
     * msg : 该电话还未注册，请先去注册
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
