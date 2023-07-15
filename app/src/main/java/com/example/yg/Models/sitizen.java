package com.example.yg.Models;

public class sitizen {
    private String name;
    private String ph_number;
    private int id;
    private int card_no;
    private String ssn;


    public sitizen() {
    }

    public sitizen(String name, String ph_number, int id, int card_no, String ssn) {
        this.name = name;
        this.ph_number = ph_number;
        this.id = id;
        this.card_no = card_no;
        this.ssn = ssn;
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

    public int getCard_no() {
        return card_no;
    }

    public void setCard_no(int card_no) {
        this.card_no = card_no;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
