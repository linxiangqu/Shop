package com.linxiangqu.shop.bean.good;

import java.io.Serializable;


public class ChoseCustomerCard implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
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
