package com.linxiangqu.shop.bean.user;

import java.io.Serializable;


public class GetCustomerCode implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * code : 985D69BA
     * qrcode : /XRMall/user/qrcode/34.jpg
     */

    private int stateCode;
    private String msg;
    private String code;
    private String qrcode;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
