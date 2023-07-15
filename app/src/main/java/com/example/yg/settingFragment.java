package com.example.yg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yg.Server.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences sharedPreferences;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public settingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static settingFragment newInstance(String param1, String param2) {
        settingFragment fragment = new settingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        CardView myCardView = v.findViewById(R.id.myaccount);
        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        myCardView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(),profile_screen.class);
            startActivity(intent);
        }
        );
        CardView changePass = v.findViewById(R.id.reset_pass);
        changePass.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(),resetpass.class);
            startActivity(intent);
        });
        CardView onBoarding = v.findViewById(R.id.onboarding);
        onBoarding.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(),onboarding_screen.class);
            startActivity(intent);
        });
        CardView mapScreen;
        mapScreen = v.findViewById(R.id.map_screen);
        mapScreen.setOnClickListener(view -> {
//            Intent intent = new Intent(getActivity(),map_screen.class);
//            startActivity(intent);
        });
        CardView orderAd;
        orderAd=v.findViewById(R.id.order);
        orderAd.setOnClickListener(view -> {
            AlertDialog dialog = new AlertDialog.Builder
                    (getContext()).setTitle("من نحن").setMessage("يمن غــــــــــــــاز").setIcon(R.drawable.ic_people_black_24).create();
            dialog.show();
//            Intent intent = new Intent(getActivity(),deliveryman_activity.class);
//           startActivity(intent);
        });

        CardView cv_logout = v.findViewById(R.id.c_logout);
        cv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
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
        StringRequest request = new StringRequest(Request.Method.POST, URLs.CITIZEN_LOGOUT, new Response.Listener<String>() {
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
