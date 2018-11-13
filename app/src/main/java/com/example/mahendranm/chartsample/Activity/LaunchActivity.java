package com.example.mahendranm.chartsample.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mahendranm.chartsample.NetworkCall.NetworkCheckClass;
import com.example.mahendranm.chartsample.R;


public class LaunchActivity extends AppCompatActivity {

    private boolean networkStatus;
    private boolean loginStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    NetworkCheckClass networkCheckClass = new NetworkCheckClass(LaunchActivity.this);
                    networkStatus = networkCheckClass.is_networkStatus();
                    if (networkStatus != false) {
                        loginStatus  = networkCheckClass.isLogin();
                        if (loginStatus != false) {
                            Intent intent = new Intent(LaunchActivity.this, DashBoard_Activity.class);
                            startActivity(intent);
                        } else {

                            Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    } else {

                        Toast.makeText(LaunchActivity.this, "Network Failure!",Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }
}

