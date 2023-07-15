package com.example.yg.Delegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
    private ImageView imageView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizens);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
imageView=findViewById(R.id.citizens_exit);
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
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
                String errorMessage = "حدث خطأ .";

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
                Toast.makeText(Citizens_Activity.this, errorMessage, Toast.LENGTH_SHORT).show();
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