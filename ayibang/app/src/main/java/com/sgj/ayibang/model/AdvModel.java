package com.sgj.ayibang.model;

import java.util.List;

/**
 * Created by John on 2016/3/22.
 * 引导页图片
 */
public class AdvModel {
    public List<ListEntity> list;

    public static class ListEntity {
        public int Id;
        public String title;
        public String Img;
        public String Link;
    }
}
