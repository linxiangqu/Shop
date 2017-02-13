package com.linxiangqu.shop.bean.user;

import java.io.Serializable;


public class GetAdvInfo implements Serializable {

    /**
     * stateCode : 2201
     * msg : 广告ID不能为空
     * advId : asd
     * advTitle : aasd
     * advContent : asdf
     * advImg : asdf
     */

    private int stateCode;
    private String msg;
    private String advId;
    private String advTitle;
    private String advContent;
    private String advImg;

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

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public String getAdvContent() {
        return advContent;
    }

    public void setAdvContent(String advContent) {
        this.advContent = advContent;
    }

    public String getAdvImg() {
        return advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }
}
