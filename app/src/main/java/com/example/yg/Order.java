package com.example.yg;

public class Order {
    private String title;
    private double latitude;
    private double longitude;
    private int itemCount;
    private String pickupLocation;
    private int imageResId;


    public Order(String title, double latitude, double longitude,int itemCount, String pickupLocation,int imageResId) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.itemCount = itemCount;
        this.pickupLocation = pickupLocation;
        this.imageResId=imageResId;
    }




    public String getTitle() {
        return title;
    }
    public int getItemCount() {
        return itemCount;}

    public String getPickupLocation() {
        return pickupLocation;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getImageResId() {
        return imageResId;
    }
}


