package com.example.yg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
                boolean isLoggedIn = userPref.getBoolean("isLoggedIn", false);
                String type = userPref.getString("type", "non");
                if (isLoggedIn) {
                    switch (type) {
                        case "citizen":
                            startActivity(new Intent(Splash.this, MainActivity.class));
                            finish();
                            break;
                        case "aqel":
                            startActivity(new Intent(Splash.this, MainActivity2.class));
                            finish();
                            break;
                        case "delivery":
                            startActivity(new Intent(Splash.this, MainActivity3.class));
                            finish();
                            break;
                        case "delegate":
                            startActivity(new Intent(Splash.this, MainActivity4.class));
                            finish();
                        default:
                            // Do something when no option is selected
                            break;
                    }

                } else {
                    isFirstTime();
                }
            }
        }, 3000);
    }

    private void isFirstTime() {
        //for checking if the app is running for the very first time
        //we need to save a value to shared preferences
        SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirstTime", true);
        //default value true
        if (isFirstTime) {
            // if its true then its first time and we will change it false
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();

            // start Onboard activity
            startActivity(new Intent(Splash.this, onboarding_screen.class));
            finish();
        } else {
            //start Auth Activity
            startActivity(new Intent(Splash.this, login_page.class));
            finish();
        }
    }
}