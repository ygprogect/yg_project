package com.example.yg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

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
import com.example.yg.Server.URLs;
import com.example.yg.adapters.citizenAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quotas_statements extends AppCompatActivity {
    private List<quotas> quotasList;
    private RecyclerView quotasRecyclerView;
    private quotas_Adapter quotas_adapter;
    private SharedPreferences sharedPreferences;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotas_statements);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        quotasRecyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        quotasList= load();




    }
    private List<quotas> load(){
        progressBar.setVisibility(View.VISIBLE);
        List<quotas> siti = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, URLs.GetOrders, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")) {
                        JSONArray array = new JSONArray(object.getString("data"));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject citizen = array.getJSONObject(i);

                            quotas user = new quotas();
                            user.setId(citizen.getInt("id"));
                            user.setMonth(citizen.getString("title"));

                            siti.add(i,user);

                        }
                        quotas_adapter=new quotas_Adapter(Quotas_statements.this,siti);
                        quotasRecyclerView.setAdapter(quotas_adapter);
                        quotasRecyclerView.setLayoutManager(new LinearLayoutManager(Quotas_statements.this));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                    progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                progressBar.setVisibility(View.GONE);
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