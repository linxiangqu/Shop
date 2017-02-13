package com.linxiangqu.shop.bean.addressandcitymanger;

import java.io.Serializable;

/**
 * Created by linxiangqu on 2016/9/12.
 */
public class GetDefaultAddress implements Serializable {

    /**
     * addressId : 94
     * trueName : ddd5555
     * areaInfo : ass
     * address : dddd
     * zipCode : 215000
     * telPhone : 123
     * mobPhone : 15706844526
     * provinceName : 北京
     * cityName : 北京市
     * districtName : 东城区
     * provinceId : 1
     * districtId : 37
     * cityId : 36
     * isDefault : 1
     */

    private int addressId;
    private String trueName;
    private String areaInfo;
    private String address;
    private String zipCode;
    private int telPhone;
    private String mobPhone;
    private String provinceName;
    private String cityName;
    private String districtName;
    private int provinceId;
    private int districtId;
    private int cityId;
    private int isDefault;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(int telPhone) {
        this.telPhone = telPhone;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
