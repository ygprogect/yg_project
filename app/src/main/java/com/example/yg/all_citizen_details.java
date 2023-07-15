package com.example.yg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

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

public class all_citizen_details extends AppCompatActivity {
    private TextInputEditText txt_name,txt_number,txt_card_no,txt_no_male,txt_no_female,txt_ssn,txt_neighbor,txt_d_name;
    private String name,ph_number,ssn,neighbor,d_name;
    private int card_no,no_male,no_female;
    private SharedPreferences sharedPreferences;
    private ContentLoadingProgressBar progressBar;
    private int id;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_citizen_details);
        txt_name = findViewById(R.id.citizen_name);
        txt_number = findViewById(R.id.citizen_number);
        txt_card_no = findViewById(R.id.citizen_card_no);
        txt_no_female = findViewById(R.id.no_female);
        txt_no_male = findViewById(R.id.no_male);
        txt_ssn = findViewById(R.id.citizen_nationalN);
        txt_neighbor = findViewById(R.id.citizen_location);
        txt_d_name = findViewById(R.id.name_delegat);
        progressBar = findViewById(R.id.all_details_progressBar);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        id = getIntent().getIntExtra("id",0);

        String type = sharedPreferences.getString("type", "non");
            switch (type) {
                case "aqel":
                    url = URLs.AGetAllDetails;
                    break;
                case "delegate":
                    url = URLs.DGetAllDetails;
                    break;
                default:
                    // Do something when no option is selected
                    break;
            }

        load();

    }
    private void load() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")) {
                        JSONObject citizen = object.getJSONObject("data");;
                        ssn = citizen.getString("ssn");
                        ph_number = citizen.getString("phone_number");
                        card_no = citizen.getInt("card_no");
                        name = citizen.getString("name");
                        neighbor = citizen.getString("neighbor");
                        d_name = citizen.getString("delegate");
                        no_female = citizen.getInt("no_of_female");
                        no_male = citizen.getInt("no_of_male");
                        txt_ssn.setText(ssn);
                        txt_name.setText(name);
                        txt_neighbor.setText(neighbor);
                        txt_number.setText(ph_number);
                        txt_d_name.setText(d_name);
                        txt_card_no.setText(card_no);
                        txt_no_male.setText(no_male);
                        txt_no_female.setText(no_female);
                    }else {
                        Toast.makeText(all_citizen_details.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(all_citizen_details.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                error.printStackTrace();
                Toast.makeText(all_citizen_details.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }) {

            // provide token in header

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(id));
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