package com.example.yg.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.yg.all_citizen_details;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yg.Aqel.A_quotas_citizen;
import com.example.yg.Delegate.Citizens_Activity;
import com.example.yg.R;
import com.example.yg.Models.sitizen;

import java.util.List;

public class citizenAdapter extends RecyclerView.Adapter<citizenAdapter.MyViewHolder>{
    private Context context;
    private  List<sitizen> sitizenList;

    public citizenAdapter(Context context, List<sitizen> sitizenList) {
        this.context = context;
        this.sitizenList = sitizenList;
    }




    public void setFilteredList(List<sitizen> filteredList){
        this.sitizenList=filteredList;
        notifyDataSetChanged();
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
        holder.txt1.setText(String.valueOf(sitize.getCard_no()));
        holder.txt2.setText(sitize.getName());
        holder.txt3.setText(sitize.getPh_number());
        holder.txt4.setText(sitize.getSsn());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, all_citizen_details.class) ;
                intent.putExtra("id", sitize.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return sitizenList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt1;
        TextView txt2;
        TextView txt3;
        TextView txt4;
        CardView cv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.c_id_number);
            txt2 = itemView.findViewById(R.id.list_citizen_name);
            txt3 = itemView.findViewById(R.id.citizen_phone_number);
            txt4 = itemView.findViewById(R.id.list_item_no_id_textView);
            cv = itemView.findViewById(R.id.ci_card);
        }
    }


}
