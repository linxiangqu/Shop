package com.linxiangqu.shop.bean.shopcar;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/20.
 */
public class GetUserCartList implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * totalCount : 3
     * userCartDTOs : [{"cartId":265,"customerId":34,"goodsId":2,"storeId":3,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","specId":7,"specInfo":"[{\"spValue\":\"35\",\"spValueId\":27},{\"spValue\":\"白色\",\"spValueId\":1}]","goodsStorePrice":519,"kdPrice":20,"goodsNum":1,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","goodsIsbuy":false,"buysNumber":3,"songName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","goodsSpecDTO":[{"specId":2,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":200,"specGoodsStorage":5,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"36\",\"spValueId\":28},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":3,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":201,"specGoodsStorage":999998852,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"43\",\"spValueId\":35},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":4,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":202,"specGoodsStorage":999998856,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"38\",\"spValueId\":30},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":5,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":203,"specGoodsStorage":28,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"45\",\"spValueId\":37},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":6,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":204,"specGoodsStorage":40,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"40\",\"spValueId\":32},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":7,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":19,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"35\",\"spValueId\":27},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":8,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":60,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"42\",\"spValueId\":34},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":9,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":70,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"37\",\"spValueId\":29},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":10,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":80,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"44\",\"spValueId\":36},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":11,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":87,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"39\",\"spValueId\":31},{\"spValue\":\"白色\",\"spValueId\":1}]"}],"goodsSpecListDTO":[{"spId":3,"spName":"鞋码","goodsSpecValueListDTO":[{"spValueId":27,"spValue":"35"},{"spValueId":28,"spValue":"36"},{"spValueId":29,"spValue":"37"},{"spValueId":30,"spValue":"38"},{"spValueId":31,"spValue":"39"},{"spValueId":32,"spValue":"40"},{"spValueId":34,"spValue":"42"},{"spValueId":35,"spValue":"43"},{"spValueId":36,"spValue":"44"},{"spValueId":37,"spValue":"45"}]},{"spId":1,"spName":"颜色","goodsSpecValueListDTO":[{"spValueId":1,"spValue":"白色"}]}]},{"cartId":264,"customerId":34,"goodsId":1,"storeId":3,"goodsName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","specId":1,"specInfo":"[{\"spValue\":\"白色\",\"spValueId\":1},{\"spValue\":\"S\",\"spValueId\":15}]","goodsStorePrice":299,"kdPrice":15,"goodsNum":3,"specGoodsColor":"/XRMall/mallImg/PWLX.jpg","goodsIsbuy":true,"buysNumber":3,"songName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","goodsSpecDTO":[{"specId":1,"specName":"[{\"spName\":\"颜色\",\"spId\":1},{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":299,"specGoodsStorage":973,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLX.jpg","specGoodsSpec":"[{\"spValue\":\"白色\",\"spValueId\":1},{\"spValue\":\"S\",\"spValueId\":15}]"},{"specId":18,"specName":"[{\"spName\":\"颜色\",\"spId\":1},{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":399,"specGoodsStorage":26,"specSalenum":5,"specGoodsColor":"/XRMall/mallImg/PWLX.jpg","specGoodsSpec":"[{\"spValue\":\"白色\",\"spValueId\":1},{\"spValue\":\"M\",\"spValueId\":16}]"},{"specId":19,"specName":"[{\"spName\":\"颜色\",\"spId\":1},{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":499,"specGoodsStorage":14,"specSalenum":6,"specGoodsColor":"/XRMall/mallImg/PWLX.jpg","specGoodsSpec":"[{\"spValue\":\"白色\",\"spValueId\":1},{\"spValue\":\"L\",\"spValueId\":17}]"}],"goodsSpecListDTO":[{"spId":1,"spName":"颜色","goodsSpecValueListDTO":[{"spValueId":1,"spValue":"白色"}]},{"spId":2,"spName":"尺码","goodsSpecValueListDTO":[{"spValueId":15,"spValue":"S"},{"spValueId":16,"spValue":"M"},{"spValueId":17,"spValue":"L"}]}]},{"cartId":263,"customerId":34,"goodsId":3,"storeId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42","specId":17,"specInfo":"[{\"spValue\":\"白色\",\"spValueId\":1},{\"spValue\":\"S\",\"spValueId\":15}]","goodsStorePrice":800,"kdPrice":25,"goodsNum":1,"specGoodsColor":"/XRMall/mallImg/PWLZ.jpg","goodsIsbuy":false,"buysNumber":null,"songName":null,"goodsSpecDTO":[{"specId":12,"specName":"[{\"spName\":\"颜色\",\"spId\":1},{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":201,"specGoodsStorage":116,"specSalenum":10,"specGoodsColor":"/XRMall/mallImg/PWLZ.jpg","specGoodsSpec":"[{\"spValue\":\"白色\",\"spValueId\":1},{\"spValue\":\"M\",\"spValueId\":16}]"},{"specId":13,"specName":"[{\"spName\":\"颜色\",\"spId\":1},{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":220,"specGoodsStorage":991,"specSalenum":200,"specGoodsColor":"/XRMall/mallImg/PWLZ.jpg","specGoodsSpec":"[{\"spValue\":\"白色\",\"spValueId\":1},{\"spValue\":\"L\",\"spValueId\":17}]"},{"specId":14,"specName":"[{\"spName\":\"颜色\",\"spId\":1},{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":380,"specGoodsStorage":1535,"specSalenum":30,"specGoodsColor":"/XRMall/mallImg/PWLZ.jpg","specGoodsSpec":"[{\"spValue\":\"褐色\",\"spValueId\":8},{\"spValue\":\"L\",\"spValueId\":17}]"},{"specId":15,"specName":"[{\"spName\":\"颜色\",\"spId\":1},{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":500,"specGoodsStorage":2007,"specSalenum":600,"specGoodsColor":"/XRMall/mallImg/PWLZ.jpg","specGoodsSpec":"[{\"spValue\":\"褐色\",\"spValueId\":8},{\"spValue\":\"M\",\"spValueId\":16}]"},{"specId":16,"specName":"[{\"spName\":\"颜色\",\"spId\":1},{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":632,"specGoodsStorage":2022,"specSalenum":33,"specGoodsColor":"/XRMall/mallImg/PWLZ.jpg","specGoodsSpec":"[{\"spValue\":\"褐色\",\"spValueId\":8},{\"spValue\":\"S\",\"spValueId\":15}]"},{"specId":17,"specName":"[{\"spName\":\"颜色\",\"spId\":1},{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":800,"specGoodsStorage":484,"specSalenum":22,"specGoodsColor":"/XRMall/mallImg/PWLZ.jpg","specGoodsSpec":"[{\"spValue\":\"白色\",\"spValueId\":1},{\"spValue\":\"S\",\"spValueId\":15}]"}],"goodsSpecListDTO":[{"spId":1,"spName":"颜色","goodsSpecValueListDTO":[{"spValueId":1,"spValue":"白色"},{"spValueId":8,"spValue":"褐色"}]},{"spId":2,"spName":"尺码","goodsSpecValueListDTO":[{"spValueId":15,"spValue":"S"},{"spValueId":16,"spValue":"M"},{"spValueId":17,"spValue":"L"}]}]}]
     * guessYouLikeList : [{"stateCode":0,"msg":"成功","goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsSubtitle":null,"goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsStorePrice":516,"goodsSerial":"JKJK","salenum":14,"goodsIsbuy":null},{"stateCode":0,"msg":"成功","goodsId":25,"goodsName":"2016新款睡衣女夏天短袖开衫碎花针织纯棉薄款全棉家居服套装 ","goodsSubtitle":"","goodsImage":"/XRMall/u/cms/www/201606/28090203xrp1.png","goodsStorePrice":129,"goodsSerial":"1","salenum":1,"goodsIsbuy":null},{"stateCode":0,"msg":"成功","goodsId":26,"goodsName":"1","goodsSubtitle":"","goodsImage":"1","goodsStorePrice":1,"goodsSerial":"1","salenum":1,"goodsIsbuy":null},{"stateCode":0,"msg":"成功","goodsId":1,"goodsName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","goodsSubtitle":null,"goodsImage":"/XRMall/mallImg/PWLX.jpg","goodsStorePrice":299,"goodsSerial":"DMMLDY","salenum":0,"goodsIsbuy":null},{"stateCode":0,"msg":"成功","goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42","goodsSubtitle":null,"goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsStorePrice":439,"goodsSerial":"","salenum":0,"goodsIsbuy":null},{"stateCode":0,"msg":"成功","goodsId":20,"goodsName":"1","goodsSubtitle":"","goodsImage":"/XRMall/u/cms/www/201605/03161614y3md.png","goodsStorePrice":0,"goodsSerial":"1","salenum":0,"goodsIsbuy":null},{"stateCode":0,"msg":"成功","goodsId":21,"goodsName":"图文详情测试1","goodsSubtitle":"","goodsImage":"/XRMall/u/cms/www/201605/09163358tby5.jpeg","goodsStorePrice":4,"goodsSerial":"4444","salenum":0,"goodsIsbuy":null},{"stateCode":0,"msg":"成功","goodsId":23,"goodsName":"图文测试2","goodsSubtitle":"","goodsImage":"/XRMall/u/cms/www/201605/11141503bvq6.jpg","goodsStorePrice":0,"goodsSerial":"1","salenum":0,"goodsIsbuy":null},{"stateCode":0,"msg":"成功","goodsId":24,"goodsName":"2016新款夏季短袖真丝睡裙女100%桑蚕丝睡衣家居服真丝睡衣","goodsSubtitle":"杭州市 著名商标 新款上市 优雅真丝睡裙","goodsImage":"/XRMall/u/cms/www/201606/27170154mtl3.png","goodsStorePrice":189,"goodsSerial":"1","salenum":0,"goodsIsbuy":null}]
     */

    private int stateCode;
    private String msg;
    private int totalCount;
    /**
     * cartId : 265
     * customerId : 34
     * goodsId : 2
     * storeId : 3
     * goodsName : New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码
     * specId : 7
     * specInfo : [{"spValue":"35","spValueId":27},{"spValue":"白色","spValueId":1}]
     * goodsStorePrice : 519
     * kdPrice : 20
     * goodsNum : 1
     * specGoodsColor : /XRMall/mallImg/PWLY.jpg
     * goodsIsbuy : false
     * buysNumber : 3
     * songName : 安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42
     * goodsSpecDTO : [{"specId":2,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":200,"specGoodsStorage":5,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"36\",\"spValueId\":28},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":3,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":201,"specGoodsStorage":999998852,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"43\",\"spValueId\":35},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":4,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":202,"specGoodsStorage":999998856,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"38\",\"spValueId\":30},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":5,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":203,"specGoodsStorage":28,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"45\",\"spValueId\":37},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":6,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":204,"specGoodsStorage":40,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"40\",\"spValueId\":32},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":7,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":19,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"35\",\"spValueId\":27},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":8,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":60,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"42\",\"spValueId\":34},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":9,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":70,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"37\",\"spValueId\":29},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":10,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":80,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"44\",\"spValueId\":36},{\"spValue\":\"白色\",\"spValueId\":1}]"},{"specId":11,"specName":"[{\"spName\":\"鞋码\",\"spId\":3},{\"spName\":\"颜色\",\"spId\":1}]","specGoodsPrice":519,"specGoodsStorage":87,"specSalenum":0,"specGoodsColor":"/XRMall/mallImg/PWLY.jpg","specGoodsSpec":"[{\"spValue\":\"39\",\"spValueId\":31},{\"spValue\":\"白色\",\"spValueId\":1}]"}]
     * goodsSpecListDTO : [{"spId":3,"spName":"鞋码","goodsSpecValueListDTO":[{"spValueId":27,"spValue":"35"},{"spValueId":28,"spValue":"36"},{"spValueId":29,"spValue":"37"},{"spValueId":30,"spValue":"38"},{"spValueId":31,"spValue":"39"},{"spValueId":32,"spValue":"40"},{"spValueId":34,"spValue":"42"},{"spValueId":35,"spValue":"43"},{"spValueId":36,"spValue":"44"},{"spValueId":37,"spValue":"45"}]},{"spId":1,"spName":"颜色","goodsSpecValueListDTO":[{"spValueId":1,"spValue":"白色"}]}]
     */

    private List<UserCartDTOsBean> userCartDTOs;
    /**
     * stateCode : 0
     * msg : 成功
     * goodsId : 2
     * goodsName : New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码
     * goodsSubtitle : null
     * goodsImage : /XRMall/mallImg/PWLY.jpg
     * goodsStorePrice : 516
     * goodsSerial : JKJK
     * salenum : 14
     * goodsIsbuy : null
     */

    private List<GuessYouLikeListBean> guessYouLikeList;

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<UserCartDTOsBean> getUserCartDTOs() {
        return userCartDTOs;
    }

    public void setUserCartDTOs(List<UserCartDTOsBean> userCartDTOs) {
        this.userCartDTOs = userCartDTOs;
    }

    public List<GuessYouLikeListBean> getGuessYouLikeList() {
        return guessYouLikeList;
    }

    public void setGuessYouLikeList(List<GuessYouLikeListBean> guessYouLikeList) {
        this.guessYouLikeList = guessYouLikeList;
    }

    public static class UserCartDTOsBean {
        private int cartId;
        private int customerId;
        private int goodsId;
        private int storeId;
        private String goodsName;
        private int specId;
        private String specInfo;
        private double goodsStorePrice;
        private double kdPrice;
        private int goodsNum;
        private String specGoodsColor;
        private boolean goodsIsbuy;
        private int buysNumber;
        private String songName;
        /**
         * specId : 2
         * specName : [{"spName":"鞋码","spId":3},{"spName":"颜色","spId":1}]
         * specGoodsPrice : 200
         * specGoodsStorage : 5
         * specSalenum : 0
         * specGoodsColor : /XRMall/mallImg/PWLY.jpg
         * specGoodsSpec : [{"spValue":"36","spValueId":28},{"spValue":"白色","spValueId":1}]
         */

        private List<GoodsSpecDTOBean> goodsSpecDTO;
        /**
         * spId : 3
         * spName : 鞋码
         * goodsSpecValueListDTO : [{"spValueId":27,"spValue":"35"},{"spValueId":28,"spValue":"36"},{"spValueId":29,"spValue":"37"},{"spValueId":30,"spValue":"38"},{"spValueId":31,"spValue":"39"},{"spValueId":32,"spValue":"40"},{"spValueId":34,"spValue":"42"},{"spValueId":35,"spValue":"43"},{"spValueId":36,"spValue":"44"},{"spValueId":37,"spValue":"45"}]
         */

        private List<GoodsSpecListDTOBean> goodsSpecListDTO;

        public int getCartId() {
            return cartId;
        }

        public void setCartId(int cartId) {
            this.cartId = cartId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getSpecId() {
            return specId;
        }

        public void setSpecId(int specId) {
            this.specId = specId;
        }

        public String getSpecInfo() {
            return specInfo;
        }

        public void setSpecInfo(String specInfo) {
            this.specInfo = specInfo;
        }

        public double getGoodsStorePrice() {
            return goodsStorePrice;
        }

        public void setGoodsStorePrice(double goodsStorePrice) {
            this.goodsStorePrice = goodsStorePrice;
        }

        public double getKdPrice() {
            return kdPrice;
        }

        public void setKdPrice(double kdPrice) {
            this.kdPrice = kdPrice;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getSpecGoodsColor() {
            return specGoodsColor;
        }

        public void setSpecGoodsColor(String specGoodsColor) {
            this.specGoodsColor = specGoodsColor;
        }

        public boolean isGoodsIsbuy() {
            return goodsIsbuy;
        }

        public void setGoodsIsbuy(boolean goodsIsbuy) {
            this.goodsIsbuy = goodsIsbuy;
        }

        public int getBuysNumber() {
            return buysNumber;
        }

        public void setBuysNumber(int buysNumber) {
            this.buysNumber = buysNumber;
        }

        public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            this.songName = songName;
        }

        public List<GoodsSpecDTOBean> getGoodsSpecDTO() {
            return goodsSpecDTO;
        }

        public void setGoodsSpecDTO(List<GoodsSpecDTOBean> goodsSpecDTO) {
            this.goodsSpecDTO = goodsSpecDTO;
        }

        public List<GoodsSpecListDTOBean> getGoodsSpecListDTO() {
            return goodsSpecListDTO;
        }

        public void setGoodsSpecListDTO(List<GoodsSpecListDTOBean> goodsSpecListDTO) {
            this.goodsSpecListDTO = goodsSpecListDTO;
        }

        public static class GoodsSpecDTOBean {
            private int specId;
            private String specName;
            private double specGoodsPrice;
            private int specGoodsStorage;
            private int specSalenum;
            private String specGoodsColor;
            private String specGoodsSpec;

            public int getSpecId() {
                return specId;
            }

            public void setSpecId(int specId) {
                this.specId = specId;
            }

            public String getSpecName() {
                return specName;
            }

            public void setSpecName(String specName) {
                this.specName = specName;
            }

            public double getSpecGoodsPrice() {
                return specGoodsPrice;
            }

            public void setSpecGoodsPrice(double specGoodsPrice) {
                this.specGoodsPrice = specGoodsPrice;
            }

            public int getSpecGoodsStorage() {
                return specGoodsStorage;
            }

            public void setSpecGoodsStorage(int specGoodsStorage) {
                this.specGoodsStorage = specGoodsStorage;
            }

            public int getSpecSalenum() {
                return specSalenum;
            }

            public void setSpecSalenum(int specSalenum) {
                this.specSalenum = specSalenum;
            }

            public String getSpecGoodsColor() {
                return specGoodsColor;
            }

            public void setSpecGoodsColor(String specGoodsColor) {
                this.specGoodsColor = specGoodsColor;
            }

            public String getSpecGoodsSpec() {
                return specGoodsSpec;
            }

            public void setSpecGoodsSpec(String specGoodsSpec) {
                this.specGoodsSpec = specGoodsSpec;
            }
        }

        public static class GoodsSpecListDTOBean {
            private int spId;
            private String spName;
            /**
             * spValueId : 27
             * spValue : 35
             */

            private List<GoodsSpecValueListDTOBean> goodsSpecValueListDTO;

            public int getSpId() {
                return spId;
            }

            public void setSpId(int spId) {
                this.spId = spId;
            }

            public String getSpName() {
                return spName;
            }

            public void setSpName(String spName) {
                this.spName = spName;
            }

            public List<GoodsSpecValueListDTOBean> getGoodsSpecValueListDTO() {
                return goodsSpecValueListDTO;
            }

            public void setGoodsSpecValueListDTO(List<GoodsSpecValueListDTOBean> goodsSpecValueListDTO) {
                this.goodsSpecValueListDTO = goodsSpecValueListDTO;
            }

            public static class GoodsSpecValueListDTOBean {
                private int spValueId;
                private String spValue;

                public int getSpValueId() {
                    return spValueId;
                }

                public void setSpValueId(int spValueId) {
                    this.spValueId = spValueId;
                }

                public String getSpValue() {
                    return spValue;
                }

                public void setSpValue(String spValue) {
                    this.spValue = spValue;
                }
            }
        }
    }

    public static class GuessYouLikeListBean {
        private int stateCode;
        private String msg;
        private int goodsId;
        private String goodsName;
        private String goodsSubtitle;
        private String goodsImage;
        private double goodsStorePrice;
        private String goodsSerial;
        private int salenum;
        private boolean goodsIsbuy;

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

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsSubtitle() {
            return goodsSubtitle;
        }

        public void setGoodsSubtitle(String goodsSubtitle) {
            this.goodsSubtitle = goodsSubtitle;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public double getGoodsStorePrice() {
            return goodsStorePrice;
        }

        public void setGoodsStorePrice(double goodsStorePrice) {
            this.goodsStorePrice = goodsStorePrice;
        }

        public String getGoodsSerial() {
            return goodsSerial;
        }

        public void setGoodsSerial(String goodsSerial) {
            this.goodsSerial = goodsSerial;
        }

        public int getSalenum() {
            return salenum;
        }

        public void setSalenum(int salenum) {
            this.salenum = salenum;
        }

        public boolean isGoodsIsbuy() {
            return goodsIsbuy;
        }

        public void setGoodsIsbuy(boolean goodsIsbuy) {
            this.goodsIsbuy = goodsIsbuy;
        }
    }
}
