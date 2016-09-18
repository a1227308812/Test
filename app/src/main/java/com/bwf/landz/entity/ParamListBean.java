package com.bwf.landz.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/15 0015.
 * Description: 区域，价格，居室等具体数据
 */
public class ParamListBean {

    /* 如果要把对象变成json string，字段一定要用public不要使用set，get方法 */
    public String key;
    public String name;
    public String value;
    public String minValue;
    public String maxValue;
    public List<ParamListBean> childList;

    public boolean isSelect;//是否选中

    @Override
    public String toString() {
        return "ParamListBean{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", minValue='" + minValue + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", childList=" + childList +
                ", isSelect=" + isSelect +
                '}';
    }
}
