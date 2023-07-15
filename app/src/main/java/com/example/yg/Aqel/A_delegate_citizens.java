package com.example.yg.Aqel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class A_delegate_citizens extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private RecyclerView quotasRecyclerView;
    private A_delegates_citizens_Adapter citizenadapter;
    private List<sitizen> sitizenList;
    private int id;
    private ImageView imagexit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adelegate_citizens);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        quotasRecyclerView = findViewById(R.id.a_d_c_recyclerView);
        imagexit=findViewById(R.id.a_d_c_exit);
        sitizenList= load();
        imagexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private List<sitizen> load() {
        List<sitizen> siti = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.POST, URLs.GetDelegateCitizen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")) {
//                        JSONObject object2 = object.getJSONObject("data");
                        JSONArray array = new JSONArray(object.getString("data"));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject citizen = array.getJSONObject(i);

                            sitizen user = new sitizen();
                            user.setId(citizen.getInt("id"));
                            user.setCard_no(citizen.getInt("card_no"));
                            user.setName(citizen.getString("name"));
                            user.setPh_number(citizen.getString("phone_number"));
                            user.setSsn(citizen.getString("ssn"));
                            siti.add(user);


                        }
                        citizenadapter = new A_delegates_citizens_Adapter(A_delegate_citizens.this, siti);
                        quotasRecyclerView.setAdapter(citizenadapter);
                        quotasRecyclerView.setLayoutManager(new LinearLayoutManager(A_delegate_citizens.this));
                        quotasRecyclerView.setHasFixedSize(true);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(A_delegate_citizens.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMessage="??";
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                     errorMessage = "فشل الاتصال بالخادم. يرجى التحقق من اتصال الإنترنت والمحاولة مرة أخرى.";
                    // handle time out error or no connection error
                } else if (error instanceof AuthFailureError) {
                     errorMessage = "فشل التحقق من الهوية. يرجى إعادة تسجيل الدخول.";
                    // handle authentication failure error
                } else if (error instanceof ServerError) {
                     errorMessage = "حدث خطأ في الخادم. يرجى المحاولة مرة أخرى في وقت لاحق.";
                    // handle server error
                } else if (error instanceof NetworkError) {
                     errorMessage = "فشل الاتصال بالخادم. يرجى التحقق من اتصال الإنترنت والمحاولة مرة أخرى.";
                    // handle network error
                } else if (error instanceof ParseError) {
                     errorMessage = "حدث خطأ أثناء معالجة البيانات. يرجى المحاولة مرة أخرى في وقت لاحق.";
                    // handle JSON parsing error
                }
                Toast.makeText(A_delegate_citizens.this,errorMessage, Toast.LENGTH_SHORT).show();
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

        return siti;

    }
}