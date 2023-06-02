package com.example.yg;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

public class deliveryman_activity extends AppCompatActivity  {


    private RecyclerView orderRecyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryman);



        // إعداد RecyclerView
        orderRecyclerView = findViewById(R.id.recyclerView);
        orderList = generateDummyOrders(); // قم بتنفيذ دالة لتوليد الطلبات
        orderAdapter = new OrderAdapter(orderList);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderRecyclerView.setAdapter(orderAdapter);
    }


    // دالة لتوليد طلبات عشوائية (فقط للأغراض التوضيحية)
    private List<Order> generateDummyOrders() {
        List<Order> orders = new ArrayList<>();
        // توليد الطلبات هنا وإضافتها إلى القائمة

        return orders;
    }
}







