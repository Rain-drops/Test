package com.sgj.ayibang.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by John on 2016/3/24.
 */
public class Order extends BmobObject {
    private String orderType;
    private String phone;
    private BmobFile photo;

    public String getOrderType() {
        return orderType;
    }

    public String getPhone() {
        return phone;
    }

    public BmobFile getPhoto() {
        return photo;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(BmobFile photo) {
        this.photo = photo;
    }
}
