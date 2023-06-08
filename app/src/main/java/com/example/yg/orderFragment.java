package com.example.yg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class orderFragment extends Fragment {
    private RecyclerView orderRecyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;


//    private static final String CATEGORY = "category";
//    private String category;
//
//    public orderFragment() {
//        // Required empty public constructor
//    }
//
//
//
//    public static orderFragment newInstance(String category) {
//        orderFragment fragment = new orderFragment();
//        Bundle args = new Bundle();
//        args.putString(CATEGORY, category);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            category = getArguments().getString(CATEGORY);
//
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);
        orderRecyclerView = view.findViewById(R.id.recyclerView);
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
        orders.add(new Order("طلب 4","99","mm"));
        orders.add(new Order("طلب 5","99","mm"));
        orders.add(new Order("طلب 6","99","mm"));
        orders.add(new Order("طلب 4","99","mm"));
        orders.add(new Order("طلب 5","99","mm"));
        orders.add(new Order("طلب 6","99","mm")); orders.add(new Order("طلب 4","99","mm"));
        orders.add(new Order("طلب 5","99","mm"));
        orders.add(new Order("طلب 6","99","mm"));
        orders.add(new Order("طلب 4","99","mm"));
        orders.add(new Order("طلب 5","99","mm"));
        orders.add(new Order("طلب 6","99","mm")); orders.add(new Order("طلب 4","99","mm"));
        orders.add(new Order("طلب 5","99","mm"));
        orders.add(new Order("طلب 6","99","mm"));
        orders.add(new Order("طلب 4","99","mm"));
        orders.add(new Order("طلب 5","99","mm"));
        orders.add(new Order("طلب 6","99","mm"));

        return orders;
    }

}