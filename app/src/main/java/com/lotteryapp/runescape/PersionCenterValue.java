package com.lotteryapp.runescape;

public class PersionCenterValue {
    private String perContext;
    private int perDraw;
    private String perType;
    private String perDate;
    private int perShare;
    private int perGood;
    private int perFollowUp;

    public PersionCenterValue(String perContext,int perDraw,String perType,String perDate,int perShare,int perGood,int perFollowUp){
        this.perContext = perContext;
        this.perDraw = perDraw;
        this.perType = perType;
        this.perDate = perDate;
        this.perShare = perShare;
        this.perGood = perGood;
        this.perFollowUp = perFollowUp;
    }

    public String getPerContext() {
        return perContext;
    }

    public void setPerContext(String perContext) {
        this.perContext = perContext;
    }

    public int getPerDraw() {
        return perDraw;
    }

    public void setPerDraw(int perDraw) {
        this.perDraw = perDraw;
    }

    public String getPerType() {
        return perType;
    }

    public void setPerType(String perType) {
        this.perType = perType;
    }

    public String getPerDate() {
        return perDate;
    }

    public void setPerDate(String perDate) {
        this.perDate = perDate;
    }

    public int getPerShare() {
        return perShare;
    }

    public void setPerShare(int perShare) {
        this.perShare = perShare;
    }

    public int getPerGood() {
        return perGood;
    }

    public void setPerGood(int perGood) {
        this.perGood = perGood;
    }

    public int getPerFollowUp() {
        return perFollowUp;
    }

    public void setPerFollowUp(int perFollowUp) {
        this.perFollowUp = perFollowUp;
    }
}
