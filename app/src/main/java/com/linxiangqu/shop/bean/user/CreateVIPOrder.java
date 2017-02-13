package com.linxiangqu.shop.bean.user;

import java.io.Serializable;

public class CreateVIPOrder implements Serializable {

    /**
     * stateCode : 2203
     * msg : 该用户不是普通消费者，不能购买vip订单
     * orderNo : 123
     * callBackUrl : 123
     */

    private int stateCode;
    private String msg;
    private String orderNo;
    private String callBackUrl;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }
}
