package com.example.yg.Aqel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.yg.R;
import com.example.yg.notification_sreen;

public class home_aqelFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public static home_aqelFragment newInstance(String param1, String param2) {
        home_aqelFragment fragment = new home_aqelFragment();
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
        View v = inflater.inflate(R.layout.fragment_home_aqel, container, false);
        ImageView imageView = v.findViewById(R.id.notificationbtn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), notification_sreen.class);
                startActivity(intent);
            }
        });
        CardView myCardView= v.findViewById(R.id.a_citizen_cv);
        myCardView.setOnClickListener(view -> {
                    Intent intent = new Intent(getActivity(), A_citizen_activity.class);
                    startActivity(intent);
                }
        );
        CardView mydelegates = v.findViewById(R.id.a_delegate_cv);
        mydelegates.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), a_delegates_list.class);
            startActivity(intent);
        });
        CardView mycitizen;
        mycitizen=v.findViewById(R.id.a_qoutas_cv);
        mycitizen.setOnClickListener(view -> {
            Intent intent=new Intent(getActivity(), A_Quotas_Activity.class);
            startActivity(intent);
        });



        return v;
    }
}