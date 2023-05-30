package com.example.yg;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class deliveryman_activity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private RecyclerView orderRecyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryman);

        // إعداد الخريطة
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        // إعداد RecyclerView
        orderRecyclerView = findViewById(R.id.orderRecycler);
        orderList = generateDummyOrders(); // قم بتنفيذ دالة لتوليد الطلبات
        orderAdapter = new OrderAdapter(orderList);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderRecyclerView.setAdapter(orderAdapter);
    }

    // تنفيذ واجهة OnMapReadyCallback
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // إضافة ماركر لكل طلب على الخريطة
        for (Order order : orderList) {
            LatLng location = new LatLng(order.getLatitude(), order.getLongitude());
            mMap.addMarker(new MarkerOptions().position(location).title(order.getTitle()));
        }

        // تحديد الموقع وعمل تكبير للخريطة
        LatLng initialLocation = new LatLng(37.7749, -122.4194); // تعيين الموقع الابتدائي هنا
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 10));
    }

    // دالة لتوليد طلبات عشوائية (فقط للأغراض التوضيحية)
    private List<Order> generateDummyOrders() {
        List<Order> orders = new ArrayList<>();
        // توليد الطلبات هنا وإضافتها إلى القائمة
        return orders;
    }
}