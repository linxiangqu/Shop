package com.linxiangqu.shop.bean.user;

import java.io.Serializable;

public class QueryCustomerVIPpayPrice implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * payPrice : 123
     */

    private int stateCode;
    private String msg;
    private double payPrice;

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

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }
}
