package com.example.yg.Models;

public class quotas {
    String month;
    int id;
    int no;

    public quotas(String month, int id, int no) {
        this.month = month;
        this.id = id;
        this.no = no;
    }

    public quotas() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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
