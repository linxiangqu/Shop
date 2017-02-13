package com.linxiangqu.shop.bean.order;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/9/13.
 */
public class ConfirmBuyerGoods implements Serializable {

    /**
     * stateCode : 2201
     * msg : 订单不存在
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
