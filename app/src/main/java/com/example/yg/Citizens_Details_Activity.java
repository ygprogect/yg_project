package com.example.yg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yg.Server.URLs;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Citizens_Details_Activity extends AppCompatActivity {
    private Switch stake,sgive,spay,sdelivery;
    private TextInputEditText txt_ssn;
    private Button btn_save;
    private int take_state, give_state, pay_state, delivery_type,order_id,id ;
    private String ssn ;
    private SharedPreferences sharedPreferences;
    Citizen_Order details;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizens_details);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        stake = findViewById(R.id.switchTake);
        sgive = findViewById(R.id.switchGive);
        spay = findViewById(R.id.switchTakeMoney);
        sdelivery = findViewById(R.id.switchWithDelivery);
        btn_save = findViewById(R.id.details_save_btn);
        progressBar = findViewById(R.id.d_d_progressBar);
        txt_ssn = findViewById(R.id.d_d_txt_ssn);

        order_id = getIntent().getIntExtra("order_id",0);
        id = getIntent().getIntExtra("id",0);

        load();
//        take_state = getIntent().getIntExtra("take_state",0);
//        give_state = getIntent().getIntExtra("give_state",0);
//        pay_state = getIntent().getIntExtra("pay_state",0);
//        delivery_type = getIntent().getIntExtra("delivery_type",0);
//        ssn = getIntent().getStringExtra("ssn");



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
                save();
            }
        });

    }

    private void check(){
        if(stake.isChecked()){
            take_state = 1;
        }
        if(sgive.isChecked()){
            give_state = 1;
        }
        if(spay.isChecked()){
            pay_state = 1;
        }
        if(sdelivery.isChecked()){
            delivery_type = 1;
        }
    }
    private boolean save(){
        final boolean[] s = new boolean[1];
//            progressBar.setVisibility(View.VISIBLE);
            StringRequest request = new StringRequest(Request.Method.POST, URLs.UpdateDetails, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getBoolean("success")) {
                               s[0] = true;
                                Toast.makeText(Citizens_Details_Activity.this, "تم الحفظ", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        else {
                            Toast.makeText(Citizens_Details_Activity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                        s[0] = false;
                        finish();
                        }

                        }
                     catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    progressBar.setVisibility(View.GONE);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    error.printStackTrace();
                    Toast.makeText(Citizens_Details_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
                }

            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> map = new HashMap<>();
                    map.put("take_state",String.valueOf(take_state));
                    map.put("give_state",String.valueOf(give_state));
                    map.put("pay_state",String.valueOf(pay_state));
                    map.put("delivery_type",String.valueOf(delivery_type));
                    map.put("citizen_id",String.valueOf(id));
                    map.put("order_id",String.valueOf(order_id));
                    return map;
                }
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


        return s[0];
    }

    private void load() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, URLs.GetDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")) {
                        JSONObject citizen = object.getJSONObject("data");
                        JSONObject pivot = citizen.getJSONObject("pivot");
                        take_state = pivot.getInt("take_state");
                        give_state = pivot.getInt("give_state");
                        pay_state = pivot.getInt("pay_state");
                        delivery_type = pivot.getInt("delivery_type");
                        ssn = citizen.getString("ssn");
                        if (take_state == 1){
                            stake.setChecked(true);
                        }
                        if (give_state == 1){
                            sgive.setChecked(true);
                        }
                        if (pay_state == 1){
                            spay.setChecked(true);
                        }
                        if (delivery_type == 1){
                            sdelivery.setChecked(true);
                        }
                        txt_ssn.setText(ssn);
//                            sitizen user = new sitizen();
//                            user.setId(citizen.getInt("id"));
//                            user.setCard_no(citizen.getInt("card_no"));
//                            user.setName(citizen.getString("name"));
//                            user.setPh_number(citizen.getString("phone_number"));
//                            user.setSsn(citizen.getString("ssn"));
//
//                            Citizen_Order citizen_order = new Citizen_Order();
//                            citizen_order.setCitizen(user);
//                            citizen_order.setOrder_id(id);
//                            citizen_order.setDelivery_type(pivot.getInt("delivery_type"));
//                            citizen_order.setTake_state(pivot.getInt("take_state"));
//                            citizen_order.setGive_state(pivot.getInt("give_state"));
//                            citizen_order.setTake_date(pivot.getString("take_date"));
//                            citizen_order.setGive_date(pivot.getString("give_date"));
//                            citizen_order.setOrder_state(pivot.getInt("order_state"));
//                            citizen_order.setPay_state(pivot.getInt("pay_state"));
//                            siti.add(0,citizen_order);
                    }else {
                        Toast.makeText(Citizens_Details_Activity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Citizens_Details_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                error.printStackTrace();
                Toast.makeText(Citizens_Details_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }) {

            // provide token in header

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("order_id", String.valueOf(order_id));
                map.put("citizen_id", String.valueOf(id));
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = sharedPreferences.getString("token", "");
                HashMap<String, String> map = new HashMap<>();
//                map.put("Authorization","Bearer "+token);
                map.put("auth-token", token);
                return map;
            }

        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

//        return siti.get(0);
    }
}