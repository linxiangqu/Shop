package com.linxiangqu.shop.bean.good;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/20.
 */
public class QueryTopicGoods implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * pageNo : 1
     * pageSize : 20
     * totalCount : 2
     * topicId : 1
     * title : 蒙牛
     * subTitle : 不是所有的牛奶都叫……
     * image : /XRMall/u/cms/www/201607/21160134oyno.jpg
     * mallTopicGoodsDTOs : [{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsSubtitle":null,"goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsStorePrice":516,"goodsSerial":"JKJK","salenum":14},{"goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42","goodsSubtitle":null,"goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsStorePrice":439,"goodsSerial":"","salenum":0}]
     */

    private int stateCode;
    private String msg;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    private int topicId;
    private String title;
    private String subTitle;
    private String image;
    /**
     * goodsId : 2
     * goodsName : New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码
     * goodsSubtitle : null
     * goodsImage : /XRMall/mallImg/PWLY.jpg
     * goodsStorePrice : 516.0
     * goodsSerial : JKJK
     * salenum : 14
     */

    private List<MallTopicGoodsDTOsBean> mallTopicGoodsDTOs;

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

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<MallTopicGoodsDTOsBean> getMallTopicGoodsDTOs() {
        return mallTopicGoodsDTOs;
    }

    public void setMallTopicGoodsDTOs(List<MallTopicGoodsDTOsBean> mallTopicGoodsDTOs) {
        this.mallTopicGoodsDTOs = mallTopicGoodsDTOs;
    }

    public static class MallTopicGoodsDTOsBean {
        private int goodsId;
        private String goodsName;
        private Object goodsSubtitle;
        private String goodsImage;
        private double goodsStorePrice;
        private String goodsSerial;
        private int salenum;

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

        public Object getGoodsSubtitle() {
            return goodsSubtitle;
        }

        public void setGoodsSubtitle(Object goodsSubtitle) {
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
    }
}
