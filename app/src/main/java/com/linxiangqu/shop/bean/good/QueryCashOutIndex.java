package com.linxiangqu.shop.bean.good;

import java.io.Serializable;


public class QueryCashOutIndex implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * cut : 17.0
     * cardId : 131
     * cashOutType : 2
     * cardNo : ****
     * cardName : 发地方
     * cardCompany : asd
     * cashLimit : 10.0
     */

    private int stateCode;
    private String msg;
    private double cut;
    private int cardId;
    private int cashOutType;
    private String cardNo;
    private String cardName;
    private String cardCompany;
    private double cashLimit;

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

    public double getCut() {
        return cut;
    }

    public void setCut(double cut) {
        this.cut = cut;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCashOutType() {
        return cashOutType;
    }

    public void setCashOutType(int cashOutType) {
        this.cashOutType = cashOutType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardCompany() {
        return cardCompany;
    }

    public void setCardCompany(String cardCompany) {
        this.cardCompany = cardCompany;
    }

    public double getCashLimit() {
        return cashLimit;
    }

    public void setCashLimit(double cashLimit) {
        this.cashLimit = cashLimit;
    }
}
