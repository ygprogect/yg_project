package com.example.yg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yg.Server.URLs;
import com.example.yg.adapters.citizenAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Citizens_Activity extends AppCompatActivity {
    private RecyclerView sitizenRecyclerView;
    private com.example.yg.adapters.citizenAdapter citizenAdapter;
    private SearchView searchView;
    private SharedPreferences sharedPreferences;
    private List<sitizen> sitizens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizens);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

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
        sitizens=load();
    }

    private void filterListener(String text) {
        List<sitizen> filteredList = new ArrayList<>();

        // Filter sitizenList based on SearchView text
        for (sitizen sitize : sitizens) {
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

    //    private List<sitizen> generateDummyOrders() {
//        List<sitizen> sitizens = new ArrayList<>();
//        // توليد الطلبات هنا وإضافتها إلى القائمة
//
//
//        sitizens.add(new sitizen("احمد","2345","1","44"));
//        sitizens.add(new sitizen("سعيد","2345","1","42"));
//        sitizens.add(new sitizen("فارس","773489","1","43"));
//        sitizens.add(new sitizen("gh","777799","1","54"));
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
//
//
//        return sitizens;
//    }
    private List<sitizen> load(){
        List<sitizen> siti = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, URLs.GetCitizens, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")) {
                        JSONArray array = new JSONArray(object.getString("data"));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject citizen = array.getJSONObject(i);

                            sitizen user = new sitizen();
                            user.setId(citizen.getInt("id"));
                            user.setCard_no(citizen.getInt("card_no"));
                            user.setName(citizen.getString("name"));
                            user.setPh_number(citizen.getString("phone_number"));
                            user.setSsn(citizen.getString("ssn"));

                            siti.add(i,user);

                        }
                        citizenAdapter = new citizenAdapter(Citizens_Activity.this, siti);
                        sitizenRecyclerView.setAdapter(citizenAdapter);
                        sitizenRecyclerView.setLayoutManager(new LinearLayoutManager(Citizens_Activity.this));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }

        }){

            // provide token in header

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = sharedPreferences.getString("token","");
                HashMap<String,String> map = new HashMap<>();
//                map.put("Authorization","Bearer "+token);
                map.put("auth-token",token);
                return map;
            }

        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        return siti;
    }
}