package com.linxiangqu.shop.bean.shopcar;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/9/12.
 */
public class SaveGoodsToCart implements Serializable {

    /**
     * stateCode : 2204
     * msg : 成功添加到购物车
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
