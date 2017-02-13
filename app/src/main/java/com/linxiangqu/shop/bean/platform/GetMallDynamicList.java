package com.linxiangqu.shop.bean.platform;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/9/20.
 */
public class GetMallDynamicList implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * pageNo : 1
     * pageSize : 20
     * totalCount : 2
     * mallDynamicListInfoDTO : [{"dynamicId":2,"dynamicTitle":"动态标题2","dynamicTime":"2016-05-13 18:11:20","dynamicImg":"/XRMall/mallImg/NNFZ.png","userName":"storeDealer0509"},{"dynamicId":1,"dynamicTitle":"动态标题1","dynamicTime":"2016-05-13 18:10:51","dynamicImg":"/XRMall/mallImg/NNFZ.png","userName":"storeDealer0509"}]
     */

    private int stateCode;
    private String msg;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    /**
     * dynamicId : 2
     * dynamicTitle : 动态标题2
     * dynamicTime : 2016-05-13 18:11:20
     * dynamicImg : /XRMall/mallImg/NNFZ.png
     * userName : storeDealer0509
     */

    private List<MallDynamicListInfoDTOBean> mallDynamicListInfoDTO;

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

    public List<MallDynamicListInfoDTOBean> getMallDynamicListInfoDTO() {
        return mallDynamicListInfoDTO;
    }

    public void setMallDynamicListInfoDTO(List<MallDynamicListInfoDTOBean> mallDynamicListInfoDTO) {
        this.mallDynamicListInfoDTO = mallDynamicListInfoDTO;
    }

    public static class MallDynamicListInfoDTOBean {
        private int dynamicId;
        private String dynamicTitle;
        private String dynamicTime;
        private String dynamicImg;
        private String userName;

        public int getDynamicId() {
            return dynamicId;
        }

        public void setDynamicId(int dynamicId) {
            this.dynamicId = dynamicId;
        }

        public String getDynamicTitle() {
            return dynamicTitle;
        }

        public void setDynamicTitle(String dynamicTitle) {
            this.dynamicTitle = dynamicTitle;
        }

        public String getDynamicTime() {
            return dynamicTime;
        }

        public void setDynamicTime(String dynamicTime) {
            this.dynamicTime = dynamicTime;
        }

        public String getDynamicImg() {
            return dynamicImg;
        }

        public void setDynamicImg(String dynamicImg) {
            this.dynamicImg = dynamicImg;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
