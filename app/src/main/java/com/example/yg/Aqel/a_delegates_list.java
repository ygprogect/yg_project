package com.example.yg.Aqel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yg.R;
import com.example.yg.Server.URLs;
import com.example.yg.delegat;
import com.example.yg.delegateAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a_delegates_list extends AppCompatActivity {
    private RecyclerView dRec;
    private com.example.yg.delegateAdapter delegateAdapter;
    private List<delegat> delegatList;
    private SharedPreferences sharedPreferences;
    private ContentLoadingProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adelegates_list);
        dRec=findViewById(R.id.a_delegat_recyclerView);
        progressBar = findViewById(R.id.a_d_progressBar);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        delegatList= load();

    }
    private List<delegat> load(){
        progressBar.setVisibility(View.VISIBLE);
        List<delegat> siti = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, URLs.GetDelegates, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")) {
                        JSONArray array = new JSONArray(object.getString("data"));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject citizen = array.getJSONObject(i);

                            delegat user = new delegat();
                            user.setNo(i+1);
                            user.setId(citizen.getInt("id"));
                            user.setName(citizen.getString("name"));
                            user.setPh_number(citizen.getString("phone_number"));
                            user.setSsn(citizen.getString("ssn"));

                            siti.add(i,user);

                        }
                        delegateAdapter=new delegateAdapter(a_delegates_list.this,siti);
                        dRec.setAdapter(delegateAdapter);
                        dRec.setLayoutManager(new LinearLayoutManager(a_delegates_list.this));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(a_delegates_list.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                Toast.makeText(a_delegates_list.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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