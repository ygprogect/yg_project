package com.example.yg;

public class sitizen {
    private String name;
    private String ph_number;
    private int id_number;
    private String no_id;
    private String category;



    public sitizen(String name, String ph_number, String id_number, String no_id,String category) {
        this.name = name;
        this.ph_number = ph_number;
        this.id_number = Integer.parseInt(id_number);
        this.no_id = no_id;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
