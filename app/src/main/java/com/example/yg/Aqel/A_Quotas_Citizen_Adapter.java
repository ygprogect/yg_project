package com.example.yg.Aqel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yg.Models.Citizen_Order;
import com.example.yg.R;
import com.example.yg.Models.sitizen;

import java.util.List;

public class A_Quotas_Citizen_Adapter extends RecyclerView.Adapter<A_Quotas_Citizen_Adapter.AViewHolder> {
    private Context context;
    private List<Citizen_Order> sitizenList;

    public A_Quotas_Citizen_Adapter(Context context, List<Citizen_Order> sitizenList) {
        this.context = context;
        this.sitizenList = sitizenList;
    }

    @NonNull
    @Override
    public AViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.citizens_list_item, parent, false);
        return new AViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AViewHolder holder, int position) {
        Citizen_Order sitize = sitizenList.get(position);
        sitizen s = sitize.getCitizen();
        holder.txt1.setText(String.valueOf(s.getCard_no()));
        holder.txt2.setText(s.getName());
        holder.txt3.setText(s.getPh_number());
        holder.txt4.setText(s.getSsn());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,A_Citizen_Details.class);
                intent.putExtra("id",s.getId());
                intent.putExtra("order_id",sitize.getOrder_id());
                intent.putExtra("delivery_type",sitize.getDelivery_type());
                intent.putExtra("take_state",sitize.getTake_state());
                intent.putExtra("give_state",sitize.getGive_state());
                intent.putExtra("take_date",sitize.getTake_date());
                intent.putExtra("give_date",sitize.getGive_date());
                intent.putExtra("order_state",sitize.getOrder_state());
                intent.putExtra("pay_state",sitize.getPay_state()) ;
                intent.putExtra("ssn",sitize.getCitizen().getSsn()) ;
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return sitizenList.size();
    }

    public class AViewHolder extends RecyclerView.ViewHolder{
        TextView txt1;
        TextView txt2;
        TextView txt3;
        TextView txt4;
        CardView cv;
        public AViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.c_id_number);
            txt2 = itemView.findViewById(R.id.list_citizen_name);
            txt3 = itemView.findViewById(R.id.citizen_phone_number);
            txt4 = itemView.findViewById(R.id.list_item_no_id_textView);
            cv = itemView.findViewById(R.id.ci_card);
        }
    }
}
