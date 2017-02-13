package com.linxiangqu.shop.bean.good;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/9/13.
 */
public class GetBuySongPage implements Serializable {

    /**
     * pageNo : 1
     * pageSize : 20
     * totalCount : 3
     * buySongListContentDTO : [{"buysongId":1,"buysongName":"买三送一","startTime":"2016-4-11","endTime":"2016-4-14","goodsId":1,"goodsImage":"/XRMall/mallImg/PWLX.jpg","goodsName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","goodsStorePrice":299,"goodsIsbuy":true,"ruleId":1,"buysNumber":3,"songName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","songNumber":1},{"buysongId":2,"buysongName":"赠电饭煲","startTime":"2016-4-12","endTime":"2016-5-12","goodsId":1,"goodsImage":"/XRMall/mallImg/PWLX.jpg","goodsName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","goodsStorePrice":299,"goodsIsbuy":true,"ruleId":4,"buysNumber":1,"songName":"电饭煲","songNumber":null},{"buysongId":4,"buysongName":"硕大的","startTime":"2016-4-20","endTime":"2016-4-29","goodsId":1,"goodsImage":"/XRMall/mallImg/PWLX.jpg","goodsName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","goodsStorePrice":299,"goodsIsbuy":true,"ruleId":2,"buysNumber":2,"songName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","songNumber":1}]
     */

    private int pageNo;
    private int pageSize;
    private int totalCount;
    /**
     * buysongId : 1
     * buysongName : 买三送一
     * startTime : 2016-4-11
     * endTime : 2016-4-14
     * goodsId : 1
     * goodsImage : /XRMall/mallImg/PWLX.jpg
     * goodsName : 安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42
     * goodsStorePrice : 299.0
     * goodsIsbuy : true
     * ruleId : 1
     * buysNumber : 3
     * songName : 安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42
     * songNumber : 1
     */

    private List<BuySongListContentDTOBean> buySongListContentDTO;

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

    public List<BuySongListContentDTOBean> getBuySongListContentDTO() {
        return buySongListContentDTO;
    }

    public void setBuySongListContentDTO(List<BuySongListContentDTOBean> buySongListContentDTO) {
        this.buySongListContentDTO = buySongListContentDTO;
    }

    public static class BuySongListContentDTOBean {
        private int buysongId;
        private String buysongName;
        private String startTime;
        private String endTime;
        private int goodsId;
        private String goodsImage;
        private String goodsName;
        private double goodsStorePrice;
        private boolean goodsIsbuy;
        private int ruleId;
        private int buysNumber;
        private String songName;
        private int songNumber;

        public int getBuysongId() {
            return buysongId;
        }

        public void setBuysongId(int buysongId) {
            this.buysongId = buysongId;
        }

        public String getBuysongName() {
            return buysongName;
        }

        public void setBuysongName(String buysongName) {
            this.buysongName = buysongName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public double getGoodsStorePrice() {
            return goodsStorePrice;
        }

        public void setGoodsStorePrice(double goodsStorePrice) {
            this.goodsStorePrice = goodsStorePrice;
        }

        public boolean isGoodsIsbuy() {
            return goodsIsbuy;
        }

        public void setGoodsIsbuy(boolean goodsIsbuy) {
            this.goodsIsbuy = goodsIsbuy;
        }

        public int getRuleId() {
            return ruleId;
        }

        public void setRuleId(int ruleId) {
            this.ruleId = ruleId;
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

        public int getSongNumber() {
            return songNumber;
        }

        public void setSongNumber(int songNumber) {
            this.songNumber = songNumber;
        }
    }
}
