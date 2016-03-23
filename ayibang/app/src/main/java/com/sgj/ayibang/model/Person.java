package com.sgj.ayibang.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by John on 2016/3/22.
 */
public class Person extends BmobObject{
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
