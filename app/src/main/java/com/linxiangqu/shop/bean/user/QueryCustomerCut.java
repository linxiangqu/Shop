package com.linxiangqu.shop.bean.user;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/26.
 */
public class QueryCustomerCut implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * cut : 70.33
     * pageNo : 1
     * pageSize : 20
     * totalCount : 1
     * customerCutDTOs : [{"userId":40,"userImg":"asd","realname":"asd","cut":0}]
     */

    private int stateCode;
    private String msg;
    private double cut;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    /**
     * userId : 40
     * userImg : asd
     * realname : asd
     * cut : 0.0
     */

    private List<CustomerCutDTOsBean> customerCutDTOs;

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

    public double getCut() {
        return cut;
    }

    public void setCut(double cut) {
        this.cut = cut;
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

    public List<CustomerCutDTOsBean> getCustomerCutDTOs() {
        return customerCutDTOs;
    }

    public void setCustomerCutDTOs(List<CustomerCutDTOsBean> customerCutDTOs) {
        this.customerCutDTOs = customerCutDTOs;
    }

    public static class CustomerCutDTOsBean {
        private int userId;
        private String userImg;
        private String realname;
        private double cut;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public double getCut() {
            return cut;
        }

        public void setCut(double cut) {
            this.cut = cut;
        }
    }
}
