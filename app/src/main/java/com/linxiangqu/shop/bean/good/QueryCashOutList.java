package com.linxiangqu.shop.bean.good;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/24.
 */
public class QueryCashOutList implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * pageNo : 1
     * pageSize : 20
     * totalCount : 5
     * mallCashOutDTOs : [{"money":10,"state":1,"createTime":1470048092000},{"money":10,"state":1,"createTime":1470048091000},{"money":10,"state":1,"createTime":1470048091000},{"money":10,"state":1,"createTime":1470048090000},{"money":10,"state":1,"createTime":1470048089000}]
     */

    private int stateCode;
    private String msg;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    /**
     * money : 10.0
     * state : 1
     * createTime : 1470048092000
     */

    private List<MallCashOutDTOsBean> mallCashOutDTOs;

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

    public List<MallCashOutDTOsBean> getMallCashOutDTOs() {
        return mallCashOutDTOs;
    }

    public void setMallCashOutDTOs(List<MallCashOutDTOsBean> mallCashOutDTOs) {
        this.mallCashOutDTOs = mallCashOutDTOs;
    }

    public static class MallCashOutDTOsBean {
        private double money;
        private int state;
        private long createTime;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
