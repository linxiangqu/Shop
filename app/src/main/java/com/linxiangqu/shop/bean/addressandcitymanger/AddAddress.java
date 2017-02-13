package com.linxiangqu.shop.bean.addressandcitymanger;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/8/26.
 */
public class AddAddress implements Serializable {

    /**
     * stateCode : 2203
     * msg : 地址添加成功
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
