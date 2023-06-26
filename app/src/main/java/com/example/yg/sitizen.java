package com.example.yg;

public class sitizen {
    private String name;
    private int ph_number;
    private int id_number;
    private double no_id;




    public sitizen(String name, int ph_number, int id_number, double no_id) {
        this.name = name;
        this.ph_number = ph_number;
        this.id_number = id_number;
        this.no_id = no_id;
    }

    public String getName() {
        return name;
    }

    public int getPh_number() {
        return ph_number;
    }

    public int getId_number() {
        return id_number;
    }

    public double getNo_id() {
        return no_id;
    }
}
