package com.linxiangqu.shop.bean.user;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/26.
 */
public class QueryCustomerCutLog implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * pageNo : 1
     * pageSize : 20
     * totalCount : 2
     * customerCutLogDTOs : [{"logDate":1471252077000,"cutPrice":4.71},{"logDate":1471252269000,"cutPrice":4.71}]
     */

    private int stateCode;
    private String msg;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    /**
     * logDate : 1471252077000
     * cutPrice : 4.71
     */

    private List<CustomerCutLogDTOsBean> customerCutLogDTOs;

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

    public List<CustomerCutLogDTOsBean> getCustomerCutLogDTOs() {
        return customerCutLogDTOs;
    }

    public void setCustomerCutLogDTOs(List<CustomerCutLogDTOsBean> customerCutLogDTOs) {
        this.customerCutLogDTOs = customerCutLogDTOs;
    }

    public static class CustomerCutLogDTOsBean {
        private long logDate;
        private double cutPrice;

        public long getLogDate() {
            return logDate;
        }

        public void setLogDate(long logDate) {
            this.logDate = logDate;
        }

        public double getCutPrice() {
            return cutPrice;
        }

        public void setCutPrice(double cutPrice) {
            this.cutPrice = cutPrice;
        }
    }
}
