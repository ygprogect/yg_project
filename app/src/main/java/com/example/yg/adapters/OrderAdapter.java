package com.example.yg.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yg.Models.Order;
import com.example.yg.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_order_list_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.titleTextView.setText(order.getTitle());
        holder.itemsCountTextView.setText(String.valueOf(order.getItemCount()));
        holder.pickupLocationTextView.setText(order.getPickupLocation());
//TODO ADD BUTTON EVENTS FROM API

        holder.list_item1_btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent I = new Intent(context, delivery_order_details.class);
//                context.startActivity(I);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView itemsCountTextView;
        TextView pickupLocationTextView;
        ImageView list_item1_btn_ok;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.list_item1_title_name);
            itemsCountTextView = itemView.findViewById(R.id.list_item1_itemsCount_textView);
            pickupLocationTextView = itemView.findViewById(R.id.order_pickup_location);
            list_item1_btn_ok = itemView.findViewById(R.id.list_item1_btn_ok);
        }
    }
}
