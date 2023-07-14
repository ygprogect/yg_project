package com.example.yg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class delegateAdapter extends RecyclerView.Adapter<delegateAdapter.delegatViewHolder> {
    private Context context;
    private List<delegat> delegatList;

    public delegateAdapter(Context context, List<delegat> delegatList) {
        this.context = context;
        this.delegatList = delegatList;
    }

    @NonNull
    @Override
    public delegatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delegates_item,parent,false);
        return new delegatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull delegatViewHolder holder, int position) {
        delegat delegats=delegatList.get(position);
        holder.delegat_name.setText(delegats.getName());
        holder.d_ph_no.setText(delegats.getPh_number());
        holder.ssn.setText(delegats.getSsn());
        holder.id_number.setText(String.valueOf(delegats.getNo()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, A_delegate_citizens.class);
                intent.putExtra("id", delegats.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return delegatList.size();
    }

    public class delegatViewHolder extends RecyclerView.ViewHolder {
        TextView delegat_name, d_ph_no, ssn,id_number;
        CardView cardView;


        public delegatViewHolder(@NonNull View itemView) {
            super(itemView);
            delegat_name=itemView.findViewById(R.id.delegat_name);
            d_ph_no=itemView.findViewById(R.id.delegat_phone_number);
            ssn=itemView.findViewById(R.id.delegat_ssn);
            id_number = itemView.findViewById(R.id.id_number);
            cardView = itemView.findViewById(R.id.delegat_cv);

        }
    }
}
