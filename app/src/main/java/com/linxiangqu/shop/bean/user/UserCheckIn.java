package com.linxiangqu.shop.bean.user;

import java.io.Serializable;


public class UserCheckIn implements Serializable {

    /**
     * stateCode : 2202
     * msg : 用户已签到
     * serviceTime : 1472175246806
     */

    private int stateCode;
    private String msg;
    private long serviceTime;

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

    public long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(long serviceTime) {
        this.serviceTime = serviceTime;
    }
}
