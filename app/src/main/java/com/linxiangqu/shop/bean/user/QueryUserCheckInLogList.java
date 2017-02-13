package com.linxiangqu.shop.bean.user;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/26.
 */
public class QueryUserCheckInLogList implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * score : 18
     * conCheckCount : 2
     * title : 再签到3天就能获取10积分
     * isChecked : true
     * serviceTime : 1472176030837
     * mallUserCheckInLogDTOs : [{"logTime":1472175151000,"scoreAdd":2},{"logTime":1472115819000,"scoreAdd":2},{"logTime":1471960700000,"scoreAdd":2},{"logTime":1470291777000,"scoreAdd":2},{"logTime":1470203608000,"scoreAdd":2}]
     */

    private int stateCode;
    private String msg;
    private int score;
    private int conCheckCount;
    private String title;
    private boolean isChecked;
    private long serviceTime;
    /**
     * logTime : 1472175151000
     * scoreAdd : 2
     */

    private List<MallUserCheckInLogDTOsBean> mallUserCheckInLogDTOs;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getConCheckCount() {
        return conCheckCount;
    }

    public void setConCheckCount(int conCheckCount) {
        this.conCheckCount = conCheckCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(long serviceTime) {
        this.serviceTime = serviceTime;
    }

    public List<MallUserCheckInLogDTOsBean> getMallUserCheckInLogDTOs() {
        return mallUserCheckInLogDTOs;
    }

    public void setMallUserCheckInLogDTOs(List<MallUserCheckInLogDTOsBean> mallUserCheckInLogDTOs) {
        this.mallUserCheckInLogDTOs = mallUserCheckInLogDTOs;
    }

    public static class MallUserCheckInLogDTOsBean {
        private long logTime;
        private int scoreAdd;

        public long getLogTime() {
            return logTime;
        }

        public void setLogTime(long logTime) {
            this.logTime = logTime;
        }

        public int getScoreAdd() {
            return scoreAdd;
        }

        public void setScoreAdd(int scoreAdd) {
            this.scoreAdd = scoreAdd;
        }
    }
}
