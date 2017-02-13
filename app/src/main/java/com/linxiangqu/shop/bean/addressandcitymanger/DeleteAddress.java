package com.linxiangqu.shop.bean.addressandcitymanger;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/9/28.
 */
public class DeleteAddress implements Serializable {

    /**
     * stateCode : 2201
     * msg : 删除成功
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
