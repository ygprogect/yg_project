package com.example.yg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yg.adapters.citizenAdapter;

import java.util.List;


public class delegatesitms extends Fragment {
    private RecyclerView delegatRecyclerView;
    private com.example.yg.adapters.citizenAdapter citizenAdapter;
    private List<sitizen> sitizenList;


    private static final String CATEGORY = "category";

    // TODO: Rename and change types of parameters
    private String category;


    public delegatesitms() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static delegatesitms newInstance(String category) {
        delegatesitms fragment = new delegatesitms();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delegatesitms, container, false);
        delegatRecyclerView = view.findViewById(R.id.delegat_RecyclerView);
        sitizenList = Utilsdelegates.GetSitizensByCategory(category);
        citizenAdapter = new citizenAdapter(getContext(), sitizenList);
        delegatRecyclerView.setAdapter(citizenAdapter);
        delegatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        delegatRecyclerView.setHasFixedSize(true);
        return view;

    }
}