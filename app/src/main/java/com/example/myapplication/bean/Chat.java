package com.example.myapplication.bean;

public class Chat {
    String name;
    String content;
    String time;
    int img;

    public Chat(String name, String time, String content,int img){
        this.name = name;
        this.time = time;
        this.content = content;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
