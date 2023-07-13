package com.example.yg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class setting_aqelFragment extends Fragment {

    private static final String ARG_PARAM2 = "param2";


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
        myCardView.setOnClickListener(view -> {
                    Intent intent = new Intent(getActivity(),aqel_profile_screen.class);
                    startActivity(intent);
                }
        );
        return v;

    }
}