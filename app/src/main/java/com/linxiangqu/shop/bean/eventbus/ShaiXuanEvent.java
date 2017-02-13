package com.linxiangqu.shop.bean.eventbus;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/8/16.
 */
public class ShaiXuanEvent implements Serializable {
    private int firstMoney;
    private int endMoney;
    private int firstXL, endXL;
    private int fl;

    public ShaiXuanEvent() {
    }

    public ShaiXuanEvent(int firstMoney, int endMoney, int firstXL, int endXL, int fl) {
        this.firstMoney = firstMoney;
        this.endMoney = endMoney;
        this.firstXL = firstXL;
        this.endXL = endXL;
        this.fl = fl;
    }

    public int getFirstMoney() {
        return firstMoney;
    }

    public void setFirstMoney(int firstMoney) {
        this.firstMoney = firstMoney;
    }

    public int getEndMoney() {
        return endMoney;
    }

    public void setEndMoney(int endMoney) {
        this.endMoney = endMoney;
    }

    public int getFirstXL() {
        return firstXL;
    }

    public void setFirstXL(int firstXL) {
        this.firstXL = firstXL;
    }

    public int getEndXL() {
        return endXL;
    }

    public void setEndXL(int endXL) {
        this.endXL = endXL;
    }

    public int getFl() {
        return fl;
    }

    public void setFl(int fl) {
        this.fl = fl;
    }
}
