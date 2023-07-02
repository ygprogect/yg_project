package com.example.yg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity4 extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        bottomNavigationView = findViewById(R.id.bottom_nav4);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container4 , new home_aqelFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_home4);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_setting4:
                        fragment = new setting_aqelFragment();
                        break;


                    case R.id.nav_home4:
                        fragment = new home_aqelFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container4 , fragment).commit();

                return true;
            }

        });


    }



}