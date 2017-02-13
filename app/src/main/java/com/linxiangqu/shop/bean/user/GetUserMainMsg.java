package com.linxiangqu.shop.bean.user;

import java.io.Serializable;


public class GetUserMainMsg implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * userImg : /XRMall/user/user_img/201608/21105328ks18.jpg
     * cut : 17.0
     * score : 12
     * applyCashCount : 0
     * groupId : 4
     * notPayCount : 449
     * waitSellerSendGoods : 12
     * waitBuyerConfirmGoods : 0
     * buyerConfirmGoods : 4
     * returnCheckOrReturning : 9
     */

    private int stateCode;
    private String msg;
    private String userImg;
    private double cut;
    private int score;
    private double applyCashCount;
    private int groupId;
    private int notPayCount;
    private int waitSellerSendGoods;
    private int waitBuyerConfirmGoods;
    private int buyerConfirmGoods;
    private int returnCheckOrReturning;

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

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public double getCut() {
        return cut;
    }

    public void setCut(double cut) {
        this.cut = cut;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getApplyCashCount() {
        return applyCashCount;
    }

    public void setApplyCashCount(double applyCashCount) {
        this.applyCashCount = applyCashCount;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getNotPayCount() {
        return notPayCount;
    }

    public void setNotPayCount(int notPayCount) {
        this.notPayCount = notPayCount;
    }

    public int getWaitSellerSendGoods() {
        return waitSellerSendGoods;
    }

    public void setWaitSellerSendGoods(int waitSellerSendGoods) {
        this.waitSellerSendGoods = waitSellerSendGoods;
    }

    public int getWaitBuyerConfirmGoods() {
        return waitBuyerConfirmGoods;
    }

    public void setWaitBuyerConfirmGoods(int waitBuyerConfirmGoods) {
        this.waitBuyerConfirmGoods = waitBuyerConfirmGoods;
    }

    public int getBuyerConfirmGoods() {
        return buyerConfirmGoods;
    }

    public void setBuyerConfirmGoods(int buyerConfirmGoods) {
        this.buyerConfirmGoods = buyerConfirmGoods;
    }

    public int getReturnCheckOrReturning() {
        return returnCheckOrReturning;
    }

    public void setReturnCheckOrReturning(int returnCheckOrReturning) {
        this.returnCheckOrReturning = returnCheckOrReturning;
    }
}
