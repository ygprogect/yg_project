package com.example.yg;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Citizens_Activity extends AppCompatActivity {
      private RecyclerView sitizenRecyclerView;
      private citizenAdapter citizenAdapter;
      private  List<sitizen> sitizenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizens);
        sitizenRecyclerView =findViewById(R.id.citizen_recyclerView);
        sitizenList=generateDummyOrders();
        citizenAdapter = new citizenAdapter(getBaseContext(), sitizenList);
        sitizenRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sitizenRecyclerView.setAdapter(citizenAdapter);

    }
    private List<sitizen> generateDummyOrders() {
        List<sitizen> sitizens = new ArrayList<>();
        // توليد الطلبات هنا وإضافتها إلى القائمة

        sitizens.add(new sitizen("gh","2345",1,"13"));
        sitizens.add(new sitizen("gh","2345",1,"14"));
        sitizens.add(new sitizen("gh","2345",1,"41"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"13"));
        sitizens.add(new sitizen("gh","2345",1,"14"));
        sitizens.add(new sitizen("gh","2345",1,"41"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"13"));
        sitizens.add(new sitizen("gh","2345",1,"14"));
        sitizens.add(new sitizen("gh","2345",1,"41"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"13"));
        sitizens.add(new sitizen("gh","2345",1,"14"));
        sitizens.add(new sitizen("gh","2345",1,"41"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"13"));
        sitizens.add(new sitizen("gh","2345",1,"14"));
        sitizens.add(new sitizen("gh","2345",1,"41"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));
        sitizens.add(new sitizen("gh","2345",1,"42"));


        return sitizens;
    }
}