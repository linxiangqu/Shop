package com.linxiangqu.shop.bean.user;

import java.io.Serializable;


public class Login implements Serializable {
    /**
     * stateCode : 0
     * msg : 成功
     * userId : 34
     * username : customer03211649
     * realname : uhb
     * rolename : asd
     * roleId : 33
     * groupname : VIP
     * groupId : 4
     */

    private int stateCode;
    private String msg;
    private int userId;
    private String username;
    private String realname;
    private String rolename;
    private int roleId;
    private String groupname;
    private int groupId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
