package com.example.yg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.yg.Server.URLs;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Citizens_Details_Activity extends AppCompatActivity {
    private Switch stake,sgive,spay,sdelivery;
    private TextInputEditText txt_name;
    private Button btn_save;
    private int take_state, give_state, pay_state, delivery_type,order_id,id ;
    private String ssn ;
    private SharedPreferences sharedPreferences;
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

        order_id = getIntent().getIntExtra("order_id",0);
        id = getIntent().getIntExtra("id",0);
        take_state = getIntent().getIntExtra("take_state",0);
        give_state = getIntent().getIntExtra("give_state",0);
        pay_state = getIntent().getIntExtra("pay_state",0);
        delivery_type = getIntent().getIntExtra("delivery_type",0);
        ssn = getIntent().getStringExtra("ssn");

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

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
                boolean s = save();
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
}