package com.example.yg.Aqel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;

import com.example.yg.R;
import com.google.android.material.textfield.TextInputEditText;

public class A_Citizen_Details extends AppCompatActivity {
    private Switch stake,sgive,spay,sdelivery ;
    private TextInputEditText txt_name;
    private int take_state, give_state, pay_state, delivery_type,order_id,id ;
    private String ssn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitizen_details);
        stake = findViewById(R.id.a_switchTake);
        sgive = findViewById(R.id.a_switchGive);
        spay = findViewById(R.id.a_switchTakeMoney);
        sdelivery = findViewById(R.id.a_switchWithDelivery);

        order_id = getIntent().getIntExtra("order_id",0);
        id = getIntent().getIntExtra("id",0);
        take_state = getIntent().getIntExtra("take_state",0);
        give_state = getIntent().getIntExtra("give_state",0);
        pay_state = getIntent().getIntExtra("pay_state",0);
        delivery_type = getIntent().getIntExtra("delivery_type",0);
        ssn = getIntent().getStringExtra("ssn");

        if (take_state == 1){
            stake.setChecked(true);
        }
        if (give_state == 1){
            sgive.setChecked(true);
        }
        if (pay_state == 1){
            spay.setChecked(true);
        }
        if (delivery_type == 1){
            sdelivery.setChecked(true);
        }
    }
}