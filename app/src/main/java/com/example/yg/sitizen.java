package com.example.yg;

public class sitizen {
    private String name;
    private String ph_number;
    private int id_number;
    private String no_id;


    public sitizen() {
    }

    public sitizen(String name, String ph_number, int id_number, String no_id) {
        this.name = name;
        this.ph_number = ph_number;
        this.id_number = id_number;
        this.no_id = no_id;
    }

    public String getName() {
        return name;
    }

    public String getPh_number() {
        return ph_number;
    }

    public int getId_number() {
        return id_number;
    }

    public String getNo_id() {
        return no_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPh_number(String ph_number) {
        this.ph_number = ph_number;
    }

    public void setId_number(int id_number) {
        this.id_number = id_number;
    }

    public void setNo_id(String no_id) {
        this.no_id = no_id;
    }
}
