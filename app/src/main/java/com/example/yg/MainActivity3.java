package com.example.yg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity3 extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        bottomNavigationView = findViewById(R.id.bottom_nav3);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container3 , new orderFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_home3);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_setting3:
                        fragment = new deliviary_setting();
                        break;

                    case R.id.nav_add3:
                        fragment = new myorderFragment();
                        break;


                    case R.id.nav_home3:
                        fragment = new orderFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container3 , fragment).commit();

                return true;
            }

        });


    }



}