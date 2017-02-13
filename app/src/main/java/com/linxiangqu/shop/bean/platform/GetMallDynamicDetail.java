package com.linxiangqu.shop.bean.platform;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/9/21.
 */
public class GetMallDynamicDetail implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * dynamicId : 2
     * dynamicContent : 这项赛事是与美国SC、德国ISC大学生超算大赛并驾齐驱的全球三大超算赛事之一，由浪潮集团有限公司和国际超级计算机大会组委会(ISC)、国际高性能计算咨询委员会（HPC AC）共同举办，旨在推动亚洲国家及地区间超算青年人才交流和培养。
     * dynamicTitle : 动态标题2
     * dynamicTime : 2016-05-13 18:11:20
     * dynamicImg : /XRMall/mallImg/NNFZ.png
     * userName : asd
     */

    private int stateCode;
    private String msg;
    private int dynamicId;
    private String dynamicContent;
    private String dynamicTitle;
    private String dynamicTime;
    private String dynamicImg;
    private String userName;

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

    public int getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(int dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
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
