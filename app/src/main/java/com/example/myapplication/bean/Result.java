package com.example.myapplication.bean;


import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result {

    private int code;
    private String msg;

    private JSONObject data;

    private List<JSONObject> list;
//    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public List<JSONObject> getList() {
        return list;
    }

    public void setList(List<JSONObject> list) {
        this.list = list;
    }
}
