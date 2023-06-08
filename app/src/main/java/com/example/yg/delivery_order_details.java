package com.example.yg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class delivery_order_details extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_order_details);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    public delivery_order_details(GoogleMap mMap, List<Order> orderList) {
        this.mMap = mMap;
        this.orderList = orderList;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // إضافة ماركر لكل طلب على الخريطة
//        for (Order order : orderList) {
//            LatLng location = new LatLng(order.getLatitude(), order.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(location).title(order.getTitle()));
//        }

        // تحديد الموقع وعمل تكبير للخريطة
        LatLng initialLocation = new LatLng(37.7749, -122.4194); // تعيين الموقع الابتدائي هنا
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 10));


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}