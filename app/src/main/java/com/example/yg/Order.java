package com.example.yg;

public class Order {
    private String title;

    private int itemCount;
    private String pickupLocation;


    public Order(String title, String itemCount, String pickupLocation) {
        this.title = title;
        this.itemCount = Integer.parseInt(itemCount);
        this.pickupLocation = pickupLocation;
    }


    public String getTitle() {
        return title;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }


}


