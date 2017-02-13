package com.linxiangqu.shop.bean;

public class Tab {
    private int icoId;
    private int tabName;
    private Class fragment;

    public Tab(int icoId, int tabName, Class fragment) {
        this.icoId = icoId;
        this.tabName = tabName;
        this.fragment = fragment;
    }

    public int getIcoId() {
        return icoId;
    }

    public void setIcoId(int icoId) {
        this.icoId = icoId;
    }

    public int getTabName() {
        return tabName;
    }

    public void setTabName(int tabName) {
        this.tabName = tabName;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
