package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.medicalapp.Base.BaseActivity;
import com.google.gson.Gson;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Gson gson = new Gson();
        String useracc = getstring("user");
        if(useracc!=null){
            Constants.CURRENT_USER =gson.fromJson(useracc,User.class);
            getdata( Constants.CURRENT_USER,"DEFAULT");
        }

        else{
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(activity,LogInActivity.class));
                 finish();
            }
        },2000);
    }}
    public String getstring (String key){
        SharedPreferences sharedPreferences =
                getSharedPreferences("Userapp",MODE_PRIVATE);

        return sharedPreferences.getString(key,null);
    }
    public void getdata(User user , String img ) {
        //MoveToNewActivity(user);
        final Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        intent.putExtra("UserEmail", user.getEmail());
        intent.putExtra("UserName", user.getName());
        intent.putExtra("UserPhone", user.getPhone());
        intent.putExtra("UserGender", user.getGender());
        intent.putExtra("UserPassword", user.getPassword());
        intent.putExtra("UserBirthYear", user.getDate());
        intent.putExtra("ProfileImage", img);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
