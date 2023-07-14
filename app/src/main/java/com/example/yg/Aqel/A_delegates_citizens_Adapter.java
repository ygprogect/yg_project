package com.example.yg.Aqel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yg.R;
import com.example.yg.Models.sitizen;

import java.util.List;

public class A_delegates_citizens_Adapter extends RecyclerView.Adapter<A_delegates_citizens_Adapter.ADC_ViewHolder> {
    private Context context;
    private List<sitizen> sitizenList;

    public A_delegates_citizens_Adapter(Context context, List<sitizen> sitizenList) {
        this.context = context;
        this.sitizenList = sitizenList;
    }

    @NonNull
    @Override
    public ADC_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.citizens_list_item, parent, false);
        return new ADC_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ADC_ViewHolder holder, int position) {
        sitizen sitize = sitizenList.get(position);
        holder.txt1.setText(String.valueOf(sitize.getCard_no()));
        holder.txt2.setText(sitize.getName());
        holder.txt3.setText(sitize.getPh_number());
        holder.txt4.setText(sitize.getSsn());
    }

    @Override
    public int getItemCount() {
        return sitizenList.size();
    }

    public class ADC_ViewHolder extends RecyclerView.ViewHolder{
        TextView txt1;
        TextView txt2;
        TextView txt3;
        TextView txt4;
        CardView cv;
        public ADC_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.c_id_number);
            txt2 = itemView.findViewById(R.id.list_citizen_name);
            txt3 = itemView.findViewById(R.id.citizen_phone_number);
            txt4 = itemView.findViewById(R.id.list_item_no_id_textView);
        }
    }
}
