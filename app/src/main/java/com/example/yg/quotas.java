package com.example.yg;

public class quotas {
    String month;
    int id;

    public quotas() {
    }

    public quotas(String month, int id) {
        this.month = month;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
