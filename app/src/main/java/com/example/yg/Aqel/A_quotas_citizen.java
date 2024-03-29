package com.example.yg.Aqel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yg.Models.Citizen_Order;
import com.example.yg.Models.sitizen;
import com.example.yg.R;
import com.example.yg.Server.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A_quotas_citizen extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private RecyclerView quotasRecyclerView;
    private A_Quotas_Citizen_Adapter citizenadapter;
    private  List<Citizen_Order> sitizenList;
    private int id;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquotas_citizen);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        quotasRecyclerView = findViewById(R.id.a_c_recyclerView);
        sitizenList= load();
        imageView=findViewById(R.id.quotas_citi_exit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private List<Citizen_Order> load(){
        List<Citizen_Order> siti = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.POST, URLs.GetOrderCitizens, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response) ;
                    if (object.getBoolean("success")) {
                        JSONObject object2 = object.getJSONObject("data");
                        JSONArray array = new JSONArray(object2.getString("citizen_order"));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject citizen = array.getJSONObject(i);

                            sitizen user = new sitizen();
                            user.setId(citizen.getInt("id"));
                            user.setCard_no(citizen.getInt("card_no"));
                            user.setName(citizen.getString("name"));
                            user.setPh_number(citizen.getString("phone_number"));
                            user.setSsn(citizen.getString("ssn"));

                            JSONObject pivot = citizen.getJSONObject("pivot");

                            Citizen_Order citizen_order = new Citizen_Order();
                            citizen_order.setCitizen(user);
                            citizen_order.setOrder_id(id);
                            citizen_order.setDelivery_type(pivot.getInt("delivery_type"));
                            citizen_order.setTake_state(pivot.getInt("take_state"));
                            citizen_order.setGive_state(pivot.getInt("give_state"));
                            citizen_order.setTake_date(pivot.getString("take_date"));
                            citizen_order.setGive_date(pivot.getString("give_date"));
                            citizen_order.setOrder_state(pivot.getInt("order_state"));
                            citizen_order.setPay_state(pivot.getInt("pay_state"));
                            siti.add(i,citizen_order);



                        }
                        citizenadapter = new A_Quotas_Citizen_Adapter(A_quotas_citizen.this, siti);
                        quotasRecyclerView.setAdapter(citizenadapter);
                        quotasRecyclerView.setLayoutManager(new LinearLayoutManager(A_quotas_citizen.this));
                        quotasRecyclerView.setHasFixedSize(true);
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

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",String.valueOf(id));
                return map;
            }

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