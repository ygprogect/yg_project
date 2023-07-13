package com.example.yg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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


    }

    @Override
    public int getItemCount() {
        return delegatList.size();
    }

    public class delegatViewHolder extends RecyclerView.ViewHolder {
        TextView delegat_name;
        TextView d_ph_no;
        TextView ssn;

        public delegatViewHolder(@NonNull View itemView) {
            super(itemView);
            delegat_name=itemView.findViewById(R.id.delegat_name);
            d_ph_no=itemView.findViewById(R.id.delegat_phone_number);
            ssn=itemView.findViewById(R.id.delegat_ssn);

        }
    }
}
