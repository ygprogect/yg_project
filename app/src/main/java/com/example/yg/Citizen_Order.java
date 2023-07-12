package com.example.yg;

public class Citizen_Order {
    private sitizen citizen ;
    private int take_state;
    private int order_state;
    private int delivery_type;
    private int pay_state;
    private int give_state;
    private int order_id;
    private String take_date;
    private String give_date;

    public Citizen_Order(sitizen citizen, int take_state, int order_state, int delivery_type, int pay_state, int give_state, int order_id, String take_date, String give_date) {
        this.citizen = citizen;
        this.take_state = take_state;
        this.order_state = order_state;
        this.delivery_type = delivery_type;
        this.pay_state = pay_state;
        this.give_state = give_state;
        this.order_id = order_id;
        this.take_date = take_date;
        this.give_date = give_date;
    }

    public Citizen_Order() {
    }

    public sitizen getCitizen() {
        return citizen;
    }

    public void setCitizen(sitizen citizen) {
        this.citizen = citizen;
    }

    public int getTake_state() {
        return take_state;
    }

    public void setTake_state(int take_state) {
        this.take_state = take_state;
    }

    public int getOrder_state() {
        return order_state;
    }

    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }

    public int getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(int delivery_type) {
        this.delivery_type = delivery_type;
    }

    public int getPay_state() {
        return pay_state;
    }

    public void setPay_state(int pay_state) {
        this.pay_state = pay_state;
    }

    public int getGive_state() {
        return give_state;
    }

    public void setGive_state(int give_state) {
        this.give_state = give_state;
    }

    public String getTake_date() {
        return take_date;
    }

    public void setTake_date(String take_date) {
        this.take_date = take_date;
    }

    public String getGive_date() {
        return give_date;
    }

    public void setGive_date(String give_date) {
        this.give_date = give_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
