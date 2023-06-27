package com.example.yg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yg.Server.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login_page extends AppCompatActivity {

    private boolean passwordshowing = false;
    private EditText usernamekey, Passwordtext;
    private ImageView passwordshowicon;
    private TextView signupbtn;
//    private ProgressDialog dialog;
    private Button signIn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        usernamekey = findViewById(R.id.usernamekey);
        Passwordtext = findViewById(R.id.Passwordtext);
        passwordshowicon = findViewById(R.id.Passwordshowicon);
        signupbtn = findViewById(R.id.signupbtn2);
        signIn_btn = findViewById(R.id.signInBtn);
//        dialog = new ProgressDialog(getBaseContext());
//        dialog.setCancelable(false);

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    Toast.makeText(login_page.this, "valid", Toast.LENGTH_SHORT).show();
                    login();
                }
            }
        });

        passwordshowicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (passwordshowing) {
                    passwordshowing = false;

                    Passwordtext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordshowicon.setImageResource(R.drawable.showpass);
                } else {
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
                startActivity(new Intent(login_page.this, registeration.class));
            }
        });


    }

    private void login (){
//        dialog.setMessage("Logging in");
//        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, URLs.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")){
                        JSONObject user = object.getJSONObject("citizen");
                        //make shared preference user
                        SharedPreferences userPref = getBaseContext().getSharedPreferences("user",MODE_PRIVATE);
                        SharedPreferences.Editor editor = userPref.edit();
                        editor.putString("token",user.getString("api_token"));
                        editor.putString("ssn",user.getString("ssn"));
                        editor.putString("name",user.getString("name"));
                        editor.putInt("id",user.getInt("id"));
                        editor.putBoolean("isLoggedIn",true);
                        editor.apply();
                        //if success
                        startActivity(new Intent(login_page.this, MainActivity.class));
                        finish();
                        Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                dialog.dismiss();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                dialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }){

            // add parameters

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("ssn",usernamekey.getText().toString().trim());
                map.put("password",Passwordtext.getText().toString());
                return map;
            }
        };

        //add this request to requestqueue
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        queue.add(request);

    }


    private boolean validate() {
        //first getting the values
        final String mySSn = usernamekey.getText().toString().trim();
        final String myPassword = Passwordtext.getText().toString().trim();

        //validating inputs
        if (TextUtils.isEmpty(mySSn)) {
            usernamekey.setError("Please enter your SSN");
            usernamekey.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(myPassword)) {
            Passwordtext.setError("Please enter your password");
            Passwordtext.requestFocus();
            return false;
        }
        return true;
    }
}