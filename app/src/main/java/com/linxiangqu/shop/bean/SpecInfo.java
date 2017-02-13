package com.linxiangqu.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/7/28.
 */
public class SpecInfo implements Serializable {
    private String spValue;
    private int spValueId;

    public SpecInfo() {
    }

    public SpecInfo(String spValue, int spValueId) {
        this.spValue = spValue;
        this.spValueId = spValueId;
    }

    public String getSpValue() {
        return spValue;
    }

    public void setSpValue(String spValue) {
        this.spValue = spValue;
    }

    public int getSpValueId() {
        return spValueId;
    }

    public void setSpValueId(int spValueId) {
        this.spValueId = spValueId;
    }
}
