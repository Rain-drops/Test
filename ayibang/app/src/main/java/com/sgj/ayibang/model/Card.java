package com.sgj.ayibang.model;

import java.io.File;
import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by John on 2016/3/25.
 */
public class Card extends BmobObject implements Serializable{

    private String title;
    private String subtitle;
    private String bravos;
    private String digest;
    private String author;
    private BmobFile cover;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getBravos() {
        return bravos;
    }

    public String getDigest() {
        return digest;
    }

    public String getAuthor() {
        return author;
    }

    public BmobFile getCover() {
        return cover;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setBravos(String bravos) {
        this.bravos = bravos;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCover(BmobFile cover) {
        this.cover = cover;
    }
}
