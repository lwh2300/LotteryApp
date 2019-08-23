package com.lotteryapp.runescape;

public class RuneMsgDetailValue {
    private int photo;
    private String name;
    private String context;
    private String time;

    public RuneMsgDetailValue(int photo,String name,String context,String time){
        this.photo = photo;
        this.name = name;
        this.context = context;
        this.time = time;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
