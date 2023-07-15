package com.example.yg.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yg.Aqel.A_quotas_citizen;
import com.example.yg.R;
import com.example.yg.Models.quotas;

import java.util.List;

public class A_Quotas_Adapter extends RecyclerView.Adapter<A_Quotas_Adapter.MyViewHolder> {
    private Context context;
    private List<quotas> quotasList;

    public A_Quotas_Adapter(Context context, List<quotas> quotasList) {
        this.context = context;
        this.quotasList = quotasList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quotas_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        quotas quota = quotasList.get(position);
        holder.month.setText(quota.getMonth());
        holder.no_month.setText(String.valueOf(quota.getNo()));
        holder.on_viewCardViw.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {

                 Intent intent = new Intent(context, A_quotas_citizen.class) ;
                 intent.putExtra("id", quota.getId());
                 context.startActivity(intent);
             }
         }

        );
    }

    @Override
    public int getItemCount() {
        return quotasList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView month;
        TextView no_month;
        CardView on_viewCardViw;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            month = itemView.findViewById(R.id.list_month_name);
            no_month = itemView.findViewById(R.id.id_number);
            on_viewCardViw = itemView.findViewById(R.id.cv_quotas);
        }
    }
}
