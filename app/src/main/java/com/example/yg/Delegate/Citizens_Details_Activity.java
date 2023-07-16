package com.example.yg.Delegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yg.Models.Citizen_Order;
import com.example.yg.R;
import com.example.yg.Server.URLs;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Citizens_Details_Activity extends AppCompatActivity {
    private Switch stake,sgive,spay,sdelivery;
    private TextInputEditText txt_ssn,txt_delegate;
    private Button btn_save;
    private int take_state, give_state, pay_state, delivery_type,order_id,id ;
    private String ssn,delegate ;
    Citizen_Order details;
    private SharedPreferences sharedPreferences;
    private ProgressBar progressBar;
    private ImageView imageView;

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
        txt_delegate = findViewById(R.id.txt_mandob);
        imageView=findViewById(R.id.Citizens_Details_Activity_exit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
    private void save(){
//            progressBar.setVisibility(View.VISIBLE);
            StringRequest request = new StringRequest(Request.Method.POST, URLs.UpdateDetails, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getBoolean("success")) {
                                Toast.makeText(Citizens_Details_Activity.this, "تم الحفظ", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        else {
                            Toast.makeText(Citizens_Details_Activity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
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
                        delegate = citizen.getString("delegate");
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
                        txt_delegate.setText(delegate);
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