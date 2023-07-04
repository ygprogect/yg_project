package com.example.yg;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yg.adapters.citizenAdapter;

import java.util.ArrayList;
import java.util.List;

public class Citizens_Activity extends AppCompatActivity {
      private RecyclerView sitizenRecyclerView;
      private com.example.yg.adapters.citizenAdapter citizenAdapter;
      private  List<sitizen> sitizenList;
      private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_citizens);
        searchView=findViewById(R.id.search_view_citizen);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               filterListener(newText);
                return true;
            }
        });

        sitizenRecyclerView =findViewById(R.id.citizen_recyclerView);
        sitizenList=generateDummyOrders();
        citizenAdapter = new citizenAdapter(getBaseContext(), sitizenList);
        sitizenRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sitizenRecyclerView.setAdapter(citizenAdapter);

    }

    private void filterListener(String text) {
        List<sitizen> filteredList = new ArrayList<>();

        // Filter sitizenList based on SearchView text
        for (sitizen sitize : sitizenList) {
            if (sitize.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(sitize);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this,"لايوجد مواطن بهاذا الاسم",Toast.LENGTH_SHORT).show();
        }else {
            citizenAdapter.setFilteredList(filteredList);
        }



    }

    private List<sitizen> generateDummyOrders() {
        List<sitizen> sitizens = new ArrayList<>();
        // توليد الطلبات هنا وإضافتها إلى القائمة


        sitizens.add(new sitizen("احمد","2345","1","44",""));
        sitizens.add(new sitizen("سعيد","2345","1","42",""));
        sitizens.add(new sitizen("فارس","773489","1","43",""));
        sitizens.add(new sitizen("gh","777799","1","54",""));
//        sitizens.add(new sitizen("سسسس","43434","1","43"));
//        sitizens.add(new sitizen("قايد","77484","1","42"));
//        sitizens.add(new sitizen("gh","2345","1","43"));
//        sitizens.add(new sitizen("مسعد","288345","1","4"));
//        sitizens.add(new sitizen("خالد","2424","1","4"));
//        sitizens.add(new sitizen("محمد","2345","1","44"));
//        sitizens.add(new sitizen("شرف","009900","1","42"));
//        sitizens.add(new sitizen("محمد سعيج","2345","1","43"));
//        sitizens.add(new sitizen("خوبد","009900","1","54"));
//        sitizens.add(new sitizen("ساره","998899","1","43"));
//        sitizens.add(new sitizen("هناء","2345","1","42"));
//        sitizens.add(new sitizen("gh","2345","1","43"));
//        sitizens.add(new sitizen("خوله","2345","1","4"));
//        sitizens.add(new sitizen("gh","2345","1","4"));
//        sitizens.add(new sitizen("سعيدخ","2345","1","42"));
//        sitizens.add(new sitizen("gh","2345","1","42"));
//        sitizens.add(new sitizen("gh","2345","4","42"));
//        sitizens.add(new sitizen("gh","2345","1","42"));
//        sitizens.add(new sitizen("gh","2345","1","42"));
//        sitizens.add(new sitizen("gh","00998","1","42"));
//        sitizens.add(new sitizen("gh","2345","1","42"));
//        sitizens.add(new sitizen("gh","2345","5","42"));
//        sitizens.add(new sitizen("gh","2345","2","42"));
//        sitizens.add(new sitizen("gh","2345","12","42"));
//        sitizens.add(new sitizen("gh","2345","21","42"));
//        sitizens.add(new sitizen("gh","2345","11","42"));
//        sitizens.add(new sitizen("gh","2345","14","42"));
//        sitizens.add(new sitizen("gh","2345","41","42"));
//        sitizens.add(new sitizen("gh","2345","15","42"));
//        sitizens.add(new sitizen("gh","2345","16","42"));
//        sitizens.add(new sitizen("gh","2345","14","42"));
//        sitizens.add(new sitizen("gh","2345","13","42"));
//        sitizens.add(new sitizen("gh","2345","1","42"));


        return sitizens;
    }
}