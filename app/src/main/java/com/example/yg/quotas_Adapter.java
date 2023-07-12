package com.example.yg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class quotas_Adapter extends RecyclerView.Adapter<quotas_Adapter.quotasViewHolder> {
    private Context context;
    private List<quotas> quotasList;

    public quotas_Adapter(Context context, List<quotas> quotasList) {
        this.context = context;
        this.quotasList = quotasList;
    }

    @NonNull
    @Override
    public quotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quotas_items, parent, false);
        return new quotasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull quotasViewHolder holder, int position) {
        quotas quota = quotasList.get(position);
        holder.month.setText(quota.getMonth());
        holder.no_month.setText(String.valueOf(quota.getId()));
        holder.on_viewCardViw.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {

                 Intent intent = new Intent(context,quotas_citizen.class);
                 intent.putExtra("id", quota.getId()) ;
                 context.startActivity(intent);
             }
         }

        );


    }

    @Override
    public int getItemCount() {
        return quotasList.size();
    }

    public class quotasViewHolder extends RecyclerView.ViewHolder {
        TextView month;
        TextView no_month;
        CardView on_viewCardViw;

        public quotasViewHolder(@NonNull View itemView) {
            super(itemView);
            month = itemView.findViewById(R.id.list_month_name);
            no_month = itemView.findViewById(R.id.id_number);
            on_viewCardViw = itemView.findViewById(R.id.quotas_cv);

        }
    }
}
