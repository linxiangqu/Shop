package com.linxiangqu.shop.bean.shopcar;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/9/27.
 */
public class DeleteCart implements Serializable {

    /**
     * stateCode : 2205
     * msg : 成功删除购物车内商品
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
