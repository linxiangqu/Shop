package com.linxiangqu.shop.bean.order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/21.
 */
public class QueryOrderList implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * pageNo : 1
     * pageSize : 20
     * totalCount : 484
     * mallOrderGoodsListDTOs : [{"orderId":1303,"addTime":1468484707000,"orderPrice":223,"shippingFee":1,"orderState":70,"mallOrderGoodsInfoDTOs":[{"goodsId":26,"goodsName":"1","goodsImage":"1","goodsNum":1,"goodsPrice":222,"goodsAllSpecValue":"白色 自定义1 XXS"}],"orderAllAmount":1},{"orderId":1302,"addTime":1468482388000,"orderPrice":223,"shippingFee":1,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":26,"goodsName":"1无","goodsImage":"1","goodsNum":1,"goodsPrice":222,"goodsAllSpecValue":"白色 自定义1 XXS"}],"orderAllAmount":1},{"orderId":1301,"addTime":1468482167000,"orderPrice":223,"shippingFee":1,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":26,"goodsName":"1无","goodsImage":"1","goodsNum":1,"goodsPrice":222,"goodsAllSpecValue":"白色 自定义1 XXS"}],"orderAllAmount":1},{"orderId":1300,"addTime":1468479638000,"orderPrice":667,"shippingFee":1,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":26,"goodsName":"1(null)","goodsImage":"1","goodsNum":1,"goodsPrice":222,"goodsAllSpecValue":"白色 自定义1 XXS"},{"goodsId":26,"goodsName":"1(null)","goodsImage":"1","goodsNum":1,"goodsPrice":222,"goodsAllSpecValue":"白色 自定义1 XXS"},{"goodsId":26,"goodsName":"1(null)","goodsImage":"1","goodsNum":1,"goodsPrice":222,"goodsAllSpecValue":"白色 自定义1 XXS"}],"orderAllAmount":3},{"orderId":1299,"addTime":1468479422000,"orderPrice":223,"shippingFee":1,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":26,"goodsName":"1(null)","goodsImage":"1","goodsNum":1,"goodsPrice":222,"goodsAllSpecValue":"白色 自定义1 XXS"}],"orderAllAmount":1},{"orderId":1298,"addTime":1468479357000,"orderPrice":223,"shippingFee":1,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":26,"goodsName":"1(null)","goodsImage":"1","goodsNum":1,"goodsPrice":222,"goodsAllSpecValue":"白色 自定义1 XXS"}],"orderAllAmount":1},{"orderId":1297,"addTime":1468479255000,"orderPrice":1512,"shippingFee":15,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":1,"goodsName":"安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42安踏ANTA跑步鞋 2016 春夏季男鞋运动鞋11615587 黑/安踏白 42","goodsImage":"/XRMall/mallImg/PWLX.jpg","goodsNum":3,"goodsPrice":499,"goodsAllSpecValue":"白色 L"}],"orderAllAmount":3},{"orderId":1296,"addTime":1468479211000,"orderPrice":189,"shippingFee":0,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":24,"goodsName":"2016新款夏季短袖真丝睡裙女100%桑蚕丝睡衣家居服真丝睡衣(null)","goodsImage":"/XRMall/u/cms/www/201606/27170238zu1m.png","goodsNum":1,"goodsPrice":189,"goodsAllSpecValue":"蓝色 M"}],"orderAllAmount":1},{"orderId":1293,"addTime":1468478417000,"orderPrice":2805,"shippingFee":25,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42(null)","goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsNum":1,"goodsPrice":380,"goodsAllSpecValue":"褐色 L"},{"goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42(null)","goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsNum":1,"goodsPrice":800,"goodsAllSpecValue":"白色 S"},{"goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42(null)","goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsNum":1,"goodsPrice":800,"goodsAllSpecValue":"白色 S"},{"goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42(null)","goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsNum":1,"goodsPrice":800,"goodsAllSpecValue":"白色 S"}],"orderAllAmount":4},{"orderId":1290,"addTime":1468474368000,"orderPrice":2805,"shippingFee":25,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42(null)","goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsNum":1,"goodsPrice":380,"goodsAllSpecValue":"褐色 L"},{"goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42(null)","goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsNum":1,"goodsPrice":800,"goodsAllSpecValue":"白色 S"},{"goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42(null)","goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsNum":1,"goodsPrice":800,"goodsAllSpecValue":"白色 S"},{"goodsId":3,"goodsName":"MIZUNO美津浓 K1GR160370 入门基础 男慢跑鞋 MIZUNO SPARK 银/蓝/亮绿 42(null)","goodsImage":"/XRMall/mallImg/PWLZ.jpg","goodsNum":1,"goodsPrice":800,"goodsAllSpecValue":"白色 S"}],"orderAllAmount":4},{"orderId":1188,"addTime":1467791055000,"orderPrice":2,"shippingFee":1,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":26,"goodsName":"1null","goodsImage":"1","goodsNum":1,"goodsPrice":1,"goodsAllSpecValue":"白色 自定义1 XXS"}],"orderAllAmount":1},{"orderId":1061,"addTime":1464855456000,"orderPrice":423,"shippingFee":20,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码分","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":201,"goodsAllSpecValue":"43 白色"},{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码 买就送 ","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":202,"goodsAllSpecValue":"38 白色"}],"orderAllAmount":2},{"orderId":1003,"addTime":1463532401000,"orderPrice":423,"shippingFee":20,"orderState":20,"mallOrderGoodsInfoDTOs":[{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":201,"goodsAllSpecValue":"43 白色"},{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码买就送","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":202,"goodsAllSpecValue":"38 白色"}],"orderAllAmount":2},{"orderId":833,"addTime":1463532388000,"orderPrice":423,"shippingFee":20,"orderState":10,"mallOrderGoodsInfoDTOs":[{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":201,"goodsAllSpecValue":"43 白色"},{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码买就送","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":202,"goodsAllSpecValue":"38 白色"}],"orderAllAmount":2},{"orderId":811,"addTime":1463532387000,"orderPrice":423,"shippingFee":20,"orderState":10,"mallOrderGoodsInfoDTOs":[{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":201,"goodsAllSpecValue":"43 白色"},{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码买就送","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":202,"goodsAllSpecValue":"38 白色"}],"orderAllAmount":2},{"orderId":813,"addTime":1463532387000,"orderPrice":423,"shippingFee":20,"orderState":10,"mallOrderGoodsInfoDTOs":[{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":201,"goodsAllSpecValue":"43 白色"},{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码买就送","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":202,"goodsAllSpecValue":"38 白色"}],"orderAllAmount":2},{"orderId":815,"addTime":1463532387000,"orderPrice":423,"shippingFee":20,"orderState":10,"mallOrderGoodsInfoDTOs":[{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":201,"goodsAllSpecValue":"43 白色"},{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码买就送","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":202,"goodsAllSpecValue":"38 白色"}],"orderAllAmount":2},{"orderId":817,"addTime":1463532387000,"orderPrice":423,"shippingFee":20,"orderState":10,"mallOrderGoodsInfoDTOs":[{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":201,"goodsAllSpecValue":"43 白色"},{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码买就送","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":202,"goodsAllSpecValue":"38 白色"}],"orderAllAmount":2},{"orderId":819,"addTime":1463532387000,"orderPrice":423,"shippingFee":20,"orderState":10,"mallOrderGoodsInfoDTOs":[{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":201,"goodsAllSpecValue":"43 白色"},{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码买就送","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":202,"goodsAllSpecValue":"38 白色"}],"orderAllAmount":2},{"orderId":821,"addTime":1463532387000,"orderPrice":423,"shippingFee":20,"orderState":10,"mallOrderGoodsInfoDTOs":[{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":201,"goodsAllSpecValue":"43 白色"},{"goodsId":2,"goodsName":"New Balance（NB）ML574TSY 574男女款 复古鞋跑步鞋 情侣休闲鞋 US 8码 41.5码买就送","goodsImage":"/XRMall/mallImg/PWLY.jpg","goodsNum":1,"goodsPrice":202,"goodsAllSpecValue":"38 白色"}],"orderAllAmount":2}]
     */

    private int stateCode;
    private String msg;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    /**
     * orderId : 1303
     * addTime : 1468484707000
     * orderPrice : 223.0
     * shippingFee : 1.0
     * orderState : 70
     * mallOrderGoodsInfoDTOs : [{"goodsId":26,"goodsName":"1","goodsImage":"1","goodsNum":1,"goodsPrice":222,"goodsAllSpecValue":"白色 自定义1 XXS"}]
     * orderAllAmount : 1
     */

    private List<MallOrderGoodsListDTOsBean> mallOrderGoodsListDTOs;

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

    public List<MallOrderGoodsListDTOsBean> getMallOrderGoodsListDTOs() {
        return mallOrderGoodsListDTOs;
    }

    public void setMallOrderGoodsListDTOs(List<MallOrderGoodsListDTOsBean> mallOrderGoodsListDTOs) {
        this.mallOrderGoodsListDTOs = mallOrderGoodsListDTOs;
    }

    public static class MallOrderGoodsListDTOsBean {
        private int orderId;
        private long addTime;
        private double orderPrice;
        private double shippingFee;
        private int orderState;
        private int orderAllAmount;
        /**
         * goodsId : 26
         * goodsName : 1
         * goodsImage : 1
         * goodsNum : 1
         * goodsPrice : 222.0
         * goodsAllSpecValue : 白色 自定义1 XXS
         */

        private List<MallOrderGoodsInfoDTOsBean> mallOrderGoodsInfoDTOs;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public double getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(double orderPrice) {
            this.orderPrice = orderPrice;
        }

        public double getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(double shippingFee) {
            this.shippingFee = shippingFee;
        }

        public int getOrderState() {
            return orderState;
        }

        public void setOrderState(int orderState) {
            this.orderState = orderState;
        }

        public int getOrderAllAmount() {
            return orderAllAmount;
        }

        public void setOrderAllAmount(int orderAllAmount) {
            this.orderAllAmount = orderAllAmount;
        }

        public List<MallOrderGoodsInfoDTOsBean> getMallOrderGoodsInfoDTOs() {
            return mallOrderGoodsInfoDTOs;
        }

        public void setMallOrderGoodsInfoDTOs(List<MallOrderGoodsInfoDTOsBean> mallOrderGoodsInfoDTOs) {
            this.mallOrderGoodsInfoDTOs = mallOrderGoodsInfoDTOs;
        }

        public static class MallOrderGoodsInfoDTOsBean {
            private int goodsId;
            private String goodsName;
            private String goodsImage;
            private int goodsNum;
            private double goodsPrice;
            private String goodsAllSpecValue;

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

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }

            public int getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(int goodsNum) {
                this.goodsNum = goodsNum;
            }

            public double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public String getGoodsAllSpecValue() {
                return goodsAllSpecValue;
            }

            public void setGoodsAllSpecValue(String goodsAllSpecValue) {
                this.goodsAllSpecValue = goodsAllSpecValue;
            }
        }
    }
}
