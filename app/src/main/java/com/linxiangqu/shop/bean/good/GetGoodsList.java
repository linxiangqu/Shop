package com.linxiangqu.shop.bean.good;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/20.
 */
public class GetGoodsList implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * pageNo : 1
     * pageSize : 20
     * totalCount : 7
     * mallGoodsDTO : [{"stateCode":0,"msg":"成功","goodsId":26,"goodsName":"1","goodsSubtitle":"","goodsImage":"1","goodsStorePrice":1,"goodsSerial":"1","salenum":1,"goodsIsbuy":false},{"stateCode":0,"msg":"成功","goodsId":25,"goodsName":"2016新款睡衣女夏天短袖开衫碎花针织纯棉薄款全棉家居服套装 ","goodsSubtitle":"","goodsImage":"/XRMall/u/cms/www/201606/28090203xrp1.png","goodsStorePrice":129,"goodsSerial":"1","salenum":1,"goodsIsbuy":false},{"stateCode":0,"msg":"成功","goodsId":24,"goodsName":"2016新款夏季短袖真丝睡裙女100%桑蚕丝睡衣家居服真丝睡衣","goodsSubtitle":"杭州市 著名商标 新款上市 优雅真丝睡裙","goodsImage":"/XRMall/u/cms/www/201606/27170154mtl3.png","goodsStorePrice":189,"goodsSerial":"1","salenum":0,"goodsIsbuy":false},{"stateCode":0,"msg":"成功","goodsId":23,"goodsName":"图文测试2","goodsSubtitle":"","goodsImage":"/XRMall/u/cms/www/201605/11141503bvq6.jpg","goodsStorePrice":0,"goodsSerial":"1","salenum":0,"goodsIsbuy":false},{"stateCode":0,"msg":"成功","goodsId":21,"goodsName":"图文详情测试1","goodsSubtitle":"","goodsImage":"/XRMall/u/cms/www/201605/09163358tby5.jpeg","goodsStorePrice":4,"goodsSerial":"4444","salenum":0,"goodsIsbuy":false},{"stateCode":0,"msg":"成功","goodsId":20,"goodsName":"1","goodsSubtitle":"","goodsImage":"/XRMall/u/cms/www/201605/03161614y3md.png","goodsStorePrice":0,"goodsSerial":"1","salenum":0,"goodsIsbuy":false},{"stateCode":0,"msg":"成功","goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42","goodsSubtitle":null,"goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsStorePrice":439,"goodsSerial":"","salenum":0,"goodsIsbuy":false}]
     * mallGoodsSecondClassDTO : [{"gcId":29,"gcName":"内衣睡衣"},{"gcId":12,"gcName":"女装"},{"gcId":13,"gcName":"男装"},{"gcId":14,"gcName":"内衣"},{"gcId":17,"gcName":" 雪纺衫"},{"gcId":18,"gcName":"针织衫"},{"gcId":19,"gcName":"卫衣"},{"gcId":23,"gcName":"棉衣"}]
     */

    private int stateCode;
    private String msg;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    /**
     * stateCode : 0
     * msg : 成功
     * goodsId : 26
     * goodsName : 1
     * goodsSubtitle :
     * goodsImage : 1
     * goodsStorePrice : 1.0
     * goodsSerial : 1
     * salenum : 1
     * goodsIsbuy : false
     */

    private List<MallGoodsDTOBean> mallGoodsDTO;
    /**
     * gcId : 29
     * gcName : 内衣睡衣
     */

    private List<MallGoodsSecondClassDTOBean> mallGoodsSecondClassDTO;

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

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<MallGoodsDTOBean> getMallGoodsDTO() {
        return mallGoodsDTO;
    }

    public void setMallGoodsDTO(List<MallGoodsDTOBean> mallGoodsDTO) {
        this.mallGoodsDTO = mallGoodsDTO;
    }

    public List<MallGoodsSecondClassDTOBean> getMallGoodsSecondClassDTO() {
        return mallGoodsSecondClassDTO;
    }

    public void setMallGoodsSecondClassDTO(List<MallGoodsSecondClassDTOBean> mallGoodsSecondClassDTO) {
        this.mallGoodsSecondClassDTO = mallGoodsSecondClassDTO;
    }

    public static class MallGoodsDTOBean {
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

    public static class MallGoodsSecondClassDTOBean {
        private int gcId;
        private String gcName;

        public int getGcId() {
            return gcId;
        }

        public void setGcId(int gcId) {
            this.gcId = gcId;
        }

        public String getGcName() {
            return gcName;
        }

        public void setGcName(String gcName) {
            this.gcName = gcName;
        }
    }
}
