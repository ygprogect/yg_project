package com.example.yg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.yg.Delegate.SettingdelegateFragment;
import com.example.yg.Delegate.home_delegateFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigationView = findViewById(R.id.bottom_nav2);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container2 , new home_delegateFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_home2);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_setting2:
                        fragment = new SettingdelegateFragment();
                        break;


                    case R.id.nav_home2:
                        fragment = new home_delegateFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container2 , fragment).commit();

                return true;
            }

        });


    }



}