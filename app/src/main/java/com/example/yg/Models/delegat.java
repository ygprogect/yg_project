package com.example.yg.Models;

public class delegat {
    private String name;
    private String ph_number;
    private int id;
    private String ssn;
    private int no;

    public delegat() {
    }

    public delegat(String name, String ph_number, int id, String ssn, int no) {
        this.name = name;
        this.ph_number = ph_number;
        this.id = id;
        this.ssn = ssn;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPh_number() {
        return ph_number;
    }

    public void setPh_number(String ph_number) {
        this.ph_number = ph_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
