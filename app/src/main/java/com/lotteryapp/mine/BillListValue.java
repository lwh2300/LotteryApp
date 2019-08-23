package com.lotteryapp.mine;

public class BillListValue {
    private String type;
    private String time;
    private int money;

    public BillListValue(String type,String time,int money){
        this.type = type;
        this.time = time;
        this.money = money;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
}
