package com.linxiangqu.shop.bean.platform;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/9/20.
 */
public class GetMallGoodsZoneInfo implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * goodsZoneId : 40
     * goodsZoneTypeId : 0
     * goodsZoneTypeName : 男女服装
     * goodsZoneMobile : 18758302404
     * goodsZoneTitle : Sdy
     * goodsZoneContent : dhdj
     * goodsZoneTime : 2016-07-20 15:04:48
     * goodsZoneLinkMan : 男女服装
     * userName : storeDealer0711
     * userImg : asd
     * picture : [{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/20150448bukt.png"}]
     */

    private int stateCode;
    private String msg;
    private int goodsZoneId;
    private int goodsZoneTypeId;
    private String goodsZoneTypeName;
    private String goodsZoneMobile;
    private String goodsZoneTitle;
    private String goodsZoneContent;
    private String goodsZoneTime;
    private String goodsZoneLinkMan;
    private String userName;
    private String userImg;
    /**
     * imgPath : /XRMall/u/cms//goodsZone/img/201607/20150448bukt.png
     */

    private List<PictureBean> picture;

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

    public int getGoodsZoneId() {
        return goodsZoneId;
    }

    public void setGoodsZoneId(int goodsZoneId) {
        this.goodsZoneId = goodsZoneId;
    }

    public int getGoodsZoneTypeId() {
        return goodsZoneTypeId;
    }

    public void setGoodsZoneTypeId(int goodsZoneTypeId) {
        this.goodsZoneTypeId = goodsZoneTypeId;
    }

    public String getGoodsZoneTypeName() {
        return goodsZoneTypeName;
    }

    public void setGoodsZoneTypeName(String goodsZoneTypeName) {
        this.goodsZoneTypeName = goodsZoneTypeName;
    }

    public String getGoodsZoneMobile() {
        return goodsZoneMobile;
    }

    public void setGoodsZoneMobile(String goodsZoneMobile) {
        this.goodsZoneMobile = goodsZoneMobile;
    }

    public String getGoodsZoneTitle() {
        return goodsZoneTitle;
    }

    public void setGoodsZoneTitle(String goodsZoneTitle) {
        this.goodsZoneTitle = goodsZoneTitle;
    }

    public String getGoodsZoneContent() {
        return goodsZoneContent;
    }

    public void setGoodsZoneContent(String goodsZoneContent) {
        this.goodsZoneContent = goodsZoneContent;
    }

    public String getGoodsZoneTime() {
        return goodsZoneTime;
    }

    public void setGoodsZoneTime(String goodsZoneTime) {
        this.goodsZoneTime = goodsZoneTime;
    }

    public String getGoodsZoneLinkMan() {
        return goodsZoneLinkMan;
    }

    public void setGoodsZoneLinkMan(String goodsZoneLinkMan) {
        this.goodsZoneLinkMan = goodsZoneLinkMan;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public List<PictureBean> getPicture() {
        return picture;
    }

    public void setPicture(List<PictureBean> picture) {
        this.picture = picture;
    }

    public static class PictureBean {
        private String imgPath;

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }
    }
}
