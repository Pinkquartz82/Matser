package com.example.mahendranm.chartsample.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.example.mahendranm.chartsample.NetworkCall.NetworkCheckClass;
import com.example.mahendranm.chartsample.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatEditText edt_UserName, edt_Password;
    AppCompatButton btn_SignOn;

    String userName = null;
    String Password = null;

    boolean loginStatus = false;
    boolean networkStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_UserName = findViewById(R.id.UserName);
        edt_Password = findViewById(R.id.Password);
        btn_SignOn = findViewById(R.id.SignOn);
        btn_SignOn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        try {
            userName = edt_UserName.getText().toString();
            Password = edt_Password.getText().toString();

            if ((userName != null && !userName.equals("")) && (Password != null && !Password.equals(""))) {
                NetworkCheckClass networkCheckClass = new NetworkCheckClass(LoginActivity.this);
                networkStatus = networkCheckClass.is_networkStatus();
                if (networkStatus != false) {
                    loginStatus = networkCheckClass.saveLoginDetails(userName, Password);
                    if (loginStatus != false) {
                        Intent intent = new Intent(LoginActivity.this, DashBoard_Activity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Network Failiure!", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (userName == null && userName.equals("")) {
                    Toast.makeText(this, "please enter name", Toast.LENGTH_SHORT).show();
                }
                if (Password == null && Password.equals("")) {
                    Toast.makeText(this, "please enter Password", Toast.LENGTH_SHORT).show();
                }
                if ((userName == null && userName.equals("")) && (Password == null && Password.equals(""))) {
                    Toast.makeText(this, "please enter Name & Password", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


