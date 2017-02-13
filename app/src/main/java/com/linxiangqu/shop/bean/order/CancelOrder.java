package com.linxiangqu.shop.bean.order;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/9/13.
 */
public class CancelOrder implements Serializable {

    /**
     * stateCode : 2205
     * msg : 订单取消成功
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
