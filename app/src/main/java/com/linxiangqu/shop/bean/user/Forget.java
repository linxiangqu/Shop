package com.linxiangqu.shop.bean.user;

import java.io.Serializable;


public class Forget implements Serializable {

    /**
     * stateCode : 2204
     * msg : 验证码错误
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
