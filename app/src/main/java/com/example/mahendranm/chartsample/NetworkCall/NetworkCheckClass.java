package com.example.mahendranm.chartsample.NetworkCall;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkCheckClass {

    public static boolean _loginStatus = false;
    public boolean _networkStatus = false;

    SharedPreferences sharedPreferences;
    ConnectivityManager connectivityManager;
    Context context;

    public NetworkCheckClass(Context context) {
        this.context = context;
    }

    public boolean is_networkStatus() {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            _networkStatus = true;
        }
        return _networkStatus;
    }

    public boolean saveLoginDetails(String userName, String passWord) {
        sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", userName);
        editor.putString("password", passWord);
        editor.putBoolean("login", true);
        editor.commit();
        _loginStatus = true;
        return _loginStatus;
    }

    public boolean isLogin() {
        sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean status = sharedPreferences.getBoolean("login", false);
        return status;
    }
}
