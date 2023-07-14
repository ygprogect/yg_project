package com.example.yg.Delegate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yg.R;
import com.example.yg.Server.URLs;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class mandob_profile_screen extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private ProgressBar progressBar;
    private String aqel_name;
    private String ssn,phone_number, neighbor,name;
    private TextInputEditText txt_ssn,txt_ph_no,txt_neighbor,txt_aqel;
    private TextView txt_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandob_profile_screen);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        txt_aqel = findViewById(R.id.m_profile_aqelN);
        txt_neighbor = findViewById(R.id.m_profile_location);
        txt_ssn = findViewById(R.id.m_profile_nationalN);
        txt_ph_no = findViewById(R.id.m_profie_number);
        txt_name = findViewById(R.id.m_profile_name);
        progressBar = findViewById(R.id.m_profile_progress);
        load();
    }
    private void load() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.GET, URLs.DelegateProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")) {
//                        JSONObject citizen = object.getJSONObject("data");
                        JSONObject aqel = object.getJSONObject("data");
                        aqel_name = aqel.getString("aqel");
                        phone_number = aqel.getString("phone_number");
                        neighbor = aqel.getString("neighbor");
                        ssn = aqel.getString("ssn");
                        name = aqel.getString("name");

                        txt_ssn.setText(ssn);
                        txt_aqel.setText(aqel_name);
                        txt_neighbor.setText(neighbor);
                        txt_ph_no.setText(phone_number);
                        txt_name.setText(name);

                    }else {
                        Toast.makeText(mandob_profile_screen.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(mandob_profile_screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                error.printStackTrace();
                Toast.makeText(mandob_profile_screen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }) {

            // provide token in header

//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("order_id", String.valueOf(order_id));
//                map.put("citizen_id", String.valueOf(id));
//                return map;
//            }

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
    }
}