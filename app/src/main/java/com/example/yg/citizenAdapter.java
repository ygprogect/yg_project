package com.example.yg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class citizenAdapter extends RecyclerView.Adapter<citizenAdapter.MyViewHolder>{
    private Context context;
    private  List<sitizen> sitizenList;

    public citizenAdapter(Context context, List<sitizen> sitizenList) {
        this.context = context;
        this.sitizenList = sitizenList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.citizens_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        sitizen sitize = sitizenList.get(position);
        holder.txt1.setText(sitize.getId_number()+"");
        holder.txt1.setText(sitize.getName());
        holder.txt1.setText(sitize.getPh_number());
        holder.txt1.setText(sitize.getNo_id());
    }

    @Override
    public int getItemCount() {

        return sitizenList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt1,txt2,txt3,txt4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.id_number);
            txt2 = itemView.findViewById(R.id.list_item1_citizen_name);
            txt3 = itemView.findViewById(R.id.citizen_phone_number);
            txt4 = itemView.findViewById(R.id.list_item_no_id_textView);
        }
    }


}
