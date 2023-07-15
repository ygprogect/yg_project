package com.example.yg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yg.Models.Order;
import com.example.yg.adapters.OrderAdapter;

import java.util.ArrayList;
import java.util.List;


public class myorderFragment extends Fragment {
    private RecyclerView orderRecyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myorder, container, false);
        orderRecyclerView = view.findViewById(R.id.a_recyclerView);
        orderList = generateDummyOrders(); // قم بتنفيذ دالة لتوليد الطلبات
        orderAdapter = new OrderAdapter(getContext(), orderList);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderRecyclerView.setAdapter(orderAdapter);
        return view;
    }

    // دالة لتوليد طلبات عشوائية (فقط للأغراض التوضيحية)
    private List<Order> generateDummyOrders() {
        List<Order> orders = new ArrayList<>();
        // توليد الطلبات هنا وإضافتها إلى القائمة

        return orders;
    }
}