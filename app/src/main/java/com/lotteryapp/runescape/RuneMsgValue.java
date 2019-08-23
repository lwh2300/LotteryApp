package com.lotteryapp.runescape;

public class RuneMsgValue {
    private int image;
    private String name;
    private String date;
    private int draw;
    private String context;
    private int shareNum;
    private int goodNum;
    private int followUpNum;

    public RuneMsgValue(int image,String name,String date,int draw,String context,int shareNum,int goodNum,int followUpNum){
        this.image = image;
        this.name = name;
        this.date = date;
        this.draw = draw;
        this.context = context;
        this.shareNum = shareNum;
        this.goodNum = goodNum;
        this.followUpNum = followUpNum;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getShareNum() {
        return shareNum;
    }

    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public int getFollowUpNum() {
        return followUpNum;
    }

    public void setFollowUpNum(int followUpNum) {
        this.followUpNum = followUpNum;
    }
}
