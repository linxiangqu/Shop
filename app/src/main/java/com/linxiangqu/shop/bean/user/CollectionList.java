package com.linxiangqu.shop.bean.user;

import java.io.Serializable;


public class CollectionList implements Serializable {

    /**
     * id : 42
     * goodsName : 2016新款睡衣女夏天短袖开衫碎花针织纯棉薄款全棉家居服套装
     * goodsImg : /XRMall/u/cms/www/201606/28090203xrp1.png
     * goodsStorePrice : 129.0
     * goodsId : 25
     */

    private int id;
    private String goodsName;
    private String goodsImg;
    private double goodsStorePrice;
    private int goodsId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public double getGoodsStorePrice() {
        return goodsStorePrice;
    }

    public void setGoodsStorePrice(double goodsStorePrice) {
        this.goodsStorePrice = goodsStorePrice;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
}
