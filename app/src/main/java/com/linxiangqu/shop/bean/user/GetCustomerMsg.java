package com.linxiangqu.shop.bean.user;

import java.io.Serializable;


public class GetCustomerMsg implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * userImg : /XRMall/user/user_img/201608/21105328ks18.jpg
     * realname : uhb
     * gender : false
     * birthday : 1467129600000
     * email : qu604065683@23311.com
     * mobile : customer03211649
     */

    private int stateCode;
    private String msg;
    private String userImg;
    private String realname;
    private boolean gender;
    private long birthday;
    private String email;
    private String mobile;

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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
