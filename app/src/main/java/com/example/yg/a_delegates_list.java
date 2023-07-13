package com.example.yg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class a_delegates_list extends AppCompatActivity {
    private RecyclerView dRec;
    private delegateAdapter delegateAdapter;
    private List<delegat> delegatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adelegates_list);
        dRec=findViewById(R.id.a_delegat_recyclerView);

    }
}