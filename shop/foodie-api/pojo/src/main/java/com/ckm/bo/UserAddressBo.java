package com.ckm.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户收货地址添加相关信息
 */
@ApiModel(value = "用户收货地址相关Bo",description = "从客户端，由用户传入数据封装在此类中")
public class UserAddressBo {
    @ApiModelProperty(value = "收货地址id",name = "addressId",example = "T190825CG3AA14Y3C")
    private String addressId;
    @ApiModelProperty(value = "用户id",name = "userId",example = "190825CG3AA14Y3C",required = true)
    private String userId;
    @ApiModelProperty(value = "收件人姓名",name = "receiver",example = "陈凯",required = true)
    private String receiver;
    @ApiModelProperty(value = "手机号",name = "mobile",example = "17805952333",required = true)
    private String mobile;
    @ApiModelProperty(value = "省份",name = "province",example = "福建",required = true)
    private String province;
    @ApiModelProperty(value = "城市",name = "city",example = "福州",required = true)
    private String city;
    @ApiModelProperty(value = "区县",name = "district",example = "平潭县",required = true)
    private String district;
    @ApiModelProperty(value = "详细地址",name = "detail",example = "福建福州平潭县xxx街道xx楼",required = true)
    private String detail;
    @ApiModelProperty(value = "是否默认地址",name = "isDefault",example = "1/0",required = true)
    private String isDefault;

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
