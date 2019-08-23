package com.lotteryapp.mine;

public class RulesListValue {
    private String itemName;
    private String itemTime;
    private String itemTip;


    public RulesListValue(String itemName, String itemTime, String itemTip){
        this.itemName = itemName;
        this.itemTime = itemTime;
        this.itemTip = itemTip;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public String getItemTime(){
        return itemTime;
    }

    public void setItemTime(String itemTime){
        this.itemTime = itemTime;
    }

    public String getItemTip(){
        return itemTip;
    }

    public void setItemTip(String itemName){
        this.itemTip = itemTip;
    }
}
