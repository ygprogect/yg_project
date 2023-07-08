package com.example.yg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Quotas_statements extends AppCompatActivity {
    private List<quotas> quotasList;
    private RecyclerView quotasRecyclerView;
    private quotas_Adapter quotas_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotas_statements);
        quotasRecyclerView=findViewById(R.id.recyclerView);
        quotasList= load();
        quotas_adapter=new quotas_Adapter(Quotas_statements.this,quotasList);
        quotasRecyclerView.setAdapter(quotas_adapter);
        quotasRecyclerView.setLayoutManager(new LinearLayoutManager(Quotas_statements.this));



    }
    private List<quotas> load(){
        List<quotas> siti = new ArrayList<>();
        siti.add(new quotas("ياناير",1));
        siti.add(new quotas("فبراير",2));
        siti.add(new quotas("مارس",3));
        siti.add(new quotas("ابريل",4));
        siti.add(new quotas("يونيو",5));
        siti.add(new quotas("يوليو",6));
        siti.add(new quotas("اغسطس",7));
        siti.add(new quotas("ستمبر",8));
        siti.add(new quotas("اكتوبر",9));
        siti.add(new quotas("نوفمبر",10));
        siti.add(new quotas("ديسمبر",11));
        siti.add(new quotas("مايو",12));

        return siti;
    }
}