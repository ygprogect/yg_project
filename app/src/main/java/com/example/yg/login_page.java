package com.example.yg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class login_page extends AppCompatActivity {

    private boolean passwordshowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final EditText usernamekey = findViewById(R.id.usernamekey);
        final EditText Passwordtext = findViewById(R.id.Passwordtext);
        final ImageView passwordshowicon = findViewById(R.id.Passwordshowicon);
        final TextView signupbtn = findViewById(R.id.signupbtn2);

        passwordshowicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (passwordshowing){
                    passwordshowing = false;

                    Passwordtext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordshowicon.setImageResource(R.drawable.showpass);
                }
                else {
                    passwordshowing = true;

                    Passwordtext.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordshowicon.setImageResource(R.drawable.hidepass);
                }
                Passwordtext.setSelection(Passwordtext.length());

            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_page.this , registeration.class));
            }
        });
    }
}