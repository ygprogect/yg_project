package com.example.yg.Aqel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yg.MainActivity2;
import com.example.yg.MainActivity4;
import com.example.yg.R;
import com.example.yg.Server.URLs;
import com.example.yg.login_page;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class setting_aqelFragment extends Fragment {

    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences sharedPreferences;
    private String mParam2;

    public setting_aqelFragment() {
        // Required empty public constructor
    }


    public static setting_aqelFragment newInstance( String param2) {
        setting_aqelFragment fragment = new setting_aqelFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_setting_aqel, container, false);
        CardView myCardView = v.findViewById(R.id.aqel_myaccount);
        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        myCardView.setOnClickListener(view -> {
                    Intent intent = new Intent(getActivity(),aqel_profile_screen.class);
                    startActivity(intent);
                }
        );

        CardView cv_logout = v.findViewById(R.id.aqel_logout);
        cv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("هل تريد تسجيل الخروج");
                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                });
                builder.setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        return v;

    }

    private void logout() {
        StringRequest request = new StringRequest(Request.Method.POST, URLs.AQEL_LOGOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                        JSONObject object = new JSONObject(response);
                        if (object.getBoolean("success")){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(getActivity(), login_page.class);
                            startActivity(intent);
                            MainActivity4 activity = (MainActivity4) getActivity();
                            activity.finish();
                            Toast.makeText(getActivity(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    else {
                            Toast.makeText(getActivity(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }

                    catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                dialog.dismiss();
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = sharedPreferences.getString("token","");
                HashMap<String,String> map = new HashMap<>();
                map.put("auth-token",token);
                return map;
            }

        };

        //add this request to requestqueue
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }
    }
