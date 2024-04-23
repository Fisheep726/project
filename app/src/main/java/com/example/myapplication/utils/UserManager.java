package com.example.myapplication.utils;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.bean.User;

public class UserManager {

    public static User mUser;

    public static void saveUser(User user){
        mUser= user;
        try {
            String json = JSON.toJSONString(user);
            //使用首选想保存数据 自己写

        }catch (Exception e){

        }
    }

    public static User getUser(){
        return mUser;
    }

}
