package com.linxiangqu.shop.bean.addressandcitymanger;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/8/26.
 */
public class UpdateAddress implements Serializable {

    /**
     * stateCode : 2205
     * msg : 地址更新成功
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
