package com.linxiangqu.shop.bean.platform;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/9/20.
 */
public class GetMallGoodsZoneList implements Serializable {
    /**
     * stateCode : 0
     * msg : 成功
     * pageNo : 1
     * pageSize : 20
     * totalCount : 22
     * mallGoodsZoneListinfoDTO : [{"goodsZoneId":40,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"18758302404","goodsZoneTitle":"Sdy","goodsZoneTime":"2016-07-20 15:04:48","userName":"storeDealer0711","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/20150448bukt.png"}]},{"goodsZoneId":39,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"hjjj","goodsZoneTitle":"hjj","goodsZoneTime":"2016-07-19 16:45:36","userName":"storeDealer0711","picture":[]},{"goodsZoneId":38,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"Aa","goodsZoneTitle":"As","goodsZoneTime":"2016-07-19 15:16:00","userName":"storeDealer0711","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/19151600syeg.jpg"},{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/19151600mqeb.jpg"}]},{"goodsZoneId":36,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"15706844148","goodsZoneTitle":"我的小","goodsZoneTime":"2016-07-18 09:50:58","userName":"mamiStore","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/18095058r1et.jpg"}]},{"goodsZoneId":35,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"你","goodsZoneTitle":"你","goodsZoneTime":"2016-07-14 09:17:50","userName":"storeDealer0711","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/14091750f5ly.jpg"}]},{"goodsZoneId":34,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"15257894893","goodsZoneTitle":"tyuu","goodsZoneTime":"2016-07-14 09:07:17","userName":"storeDealer0711","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/14090717suiz.png"}]},{"goodsZoneId":33,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"245456","goodsZoneTitle":"额滴肾","goodsZoneTime":"2016-07-12 15:09:10","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/12150910fnfv.jpg"}]},{"goodsZoneId":32,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"15267854893","goodsZoneTitle":"在一起","goodsZoneTime":"2016-07-12 15:03:17","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/12150317munv.jpg"}]},{"goodsZoneId":30,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"13245678091","goodsZoneTitle":"电瓶车","goodsZoneTime":"2016-07-12 13:28:55","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/121328557v7u.jpg"}]},{"goodsZoneId":29,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"15257894893","goodsZoneTitle":"rrrr","goodsZoneTime":"2016-07-12 10:33:33","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/12103333pti2.jpg"}]},{"goodsZoneId":23,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"13254239852","goodsZoneTitle":"jnjdjdjdjd","goodsZoneTime":"2016-06-28 18:58:15","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201606/281858159hei.png"}]},{"goodsZoneId":18,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"13356985412","goodsZoneTitle":"空军建军节","goodsZoneTime":"2016-05-23 09:35:34","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/23093534tmqu.png"}]},{"goodsZoneId":17,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"13258965248","goodsZoneTitle":"汪长义傻逼","goodsZoneTime":"2016-05-20 16:25:46","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/201625461dyo.png"},{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/20162546pa40.png"},{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/20162546pguj.png"}]},{"goodsZoneId":16,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"13221983255","goodsZoneTitle":"匿名","goodsZoneTime":"2016-05-20 16:18:03","userName":"storeDealer0509","picture":[]},{"goodsZoneId":14,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"13566545489","goodsZoneTitle":"dsd","goodsZoneTime":"2016-05-18 16:44:28","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/181644282jb3.jpg"},{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/18164428dmkv.jpg"}]},{"goodsZoneId":13,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"13566545489","goodsZoneTitle":"测试高东强","goodsZoneTime":"2016-05-18 16:43:26","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/18164326xpit.jpg"}]},{"goodsZoneId":12,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"1371111111","goodsZoneTitle":"测试5.18","goodsZoneTime":"2016-05-18 16:36:07","userName":"storeDealer0509","picture":[]},{"goodsZoneId":11,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"1371111111","goodsZoneTitle":"测试5.18","goodsZoneTime":"2016-05-18 16:32:20","userName":"storeDealer0509","picture":[]},{"goodsZoneId":10,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"13","goodsZoneTitle":"测试5.18","goodsZoneTime":"2016-05-18 16:31:42","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/18154902c4t1.jpg"}]},{"goodsZoneId":9,"goodsZoneTypeId":0,"goodsZoneTypeName":"男女服装","goodsZoneMobile":"1616","goodsZoneTitle":"ffdf","goodsZoneTime":"2016-05-18 16:14:06","userName":"storeDealer0509","picture":[{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/18152823zucf.jpg"},{"imgPath":"/XRMall/u/cms//goodsZone/img/201605/18152823yu6y.jpg"}]}]
     */

    private int stateCode;
    private String msg;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    /**
     * goodsZoneId : 40
     * goodsZoneTypeId : 0
     * goodsZoneTypeName : 男女服装
     * goodsZoneMobile : 18758302404
     * goodsZoneTitle : Sdy
     * goodsZoneTime : 2016-07-20 15:04:48
     * userName : storeDealer0711
     * picture : [{"imgPath":"/XRMall/u/cms//goodsZone/img/201607/20150448bukt.png"}]
     */

    private List<MallGoodsZoneListinfoDTOBean> mallGoodsZoneListinfoDTO;

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

    public List<MallGoodsZoneListinfoDTOBean> getMallGoodsZoneListinfoDTO() {
        return mallGoodsZoneListinfoDTO;
    }

    public void setMallGoodsZoneListinfoDTO(List<MallGoodsZoneListinfoDTOBean> mallGoodsZoneListinfoDTO) {
        this.mallGoodsZoneListinfoDTO = mallGoodsZoneListinfoDTO;
    }

    public static class MallGoodsZoneListinfoDTOBean {
        private int goodsZoneId;
        private int goodsZoneTypeId;
        private String goodsZoneTypeName;
        private String goodsZoneMobile;
        private String goodsZoneTitle;
        private String goodsZoneTime;
        private String userName;
        /**
         * imgPath : /XRMall/u/cms//goodsZone/img/201607/20150448bukt.png
         */

        private List<PictureBean> picture;

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

        public String getGoodsZoneTime() {
            return goodsZoneTime;
        }

        public void setGoodsZoneTime(String goodsZoneTime) {
            this.goodsZoneTime = goodsZoneTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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
}
