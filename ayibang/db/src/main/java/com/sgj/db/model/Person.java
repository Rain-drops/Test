package com.sgj.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John on 2016/4/11.
 */
public class Person implements Parcelable {

    private String name;
    private String phong;
    private int age;

    protected Person(Parcel in) {
        name = in.readString();
        phong = in.readString();
        age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getPhong() {
        return phong;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phong);
        dest.writeInt(age);
    }
}
