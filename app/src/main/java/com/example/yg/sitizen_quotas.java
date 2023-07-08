package com.example.yg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yg.adapters.citizenAdapter;

import java.util.List;


public class sitizen_quotas extends Fragment {

    private RecyclerView quotasRecyclerView;
    private com.example.yg.adapters.citizenAdapter citizenAdapter;
    private  List<sitizen> sitizenList;

    private static final String ARG = "id_no";


    private String id_no;

    public sitizen_quotas() {
        // Required empty public constructor
    }


    public static sitizen_quotas newInstance(String id_no) {
        sitizen_quotas fragment = new sitizen_quotas();
        Bundle args = new Bundle();
        args.putString(ARG, id_no);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_no = getArguments().getString(ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_sitizen_quotas, container, false);
        quotasRecyclerView = view.findViewById(R.id.recyclerView);
        sitizenList= GetByDocumentIdRequest(id_no);
        citizenAdapter = new citizenAdapter(getContext(), sitizenList);
        quotasRecyclerView.setAdapter(citizenAdapter);
        quotasRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        quotasRecyclerView.setHasFixedSize(true);
        return view;


    }

    private List<sitizen> GetByDocumentIdRequest(String id_no) {

        return null;
    }


}