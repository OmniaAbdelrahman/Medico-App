package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.medicalapp.Chat.RoomsFragment;
import com.example.medicalapp.Diagnoses.DiagnosesFragment;
import com.example.medicalapp.Home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    String email , name ,phone, gender , password , birthyear , image;





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = new HomeFragment();

            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    fragment = new HomeFragment();
                    break;
                }
                case R.id.navigation_dashboard: {
                    fragment = new RoomsFragment(name);
                    break;
                }
                case R.id.navigation_notifications: {
                    fragment = new ProfileUserFragment(email,name,gender,birthyear,image);
                    break;
                }  case R.id.guideapp: {
                    fragment = new page1();
                    break;
                }
                case R.id.alarmopen: {
                    fragment = new AlarmFragment();
                    break;
                }

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(null).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        email = intent.getStringExtra("UserEmail");
        name = intent.getStringExtra("UserName");
        phone=intent.getStringExtra("UserPhone");
        gender = intent.getStringExtra("UserGender");
        password = intent.getStringExtra("UserPassword");
        birthyear = intent.getStringExtra("UserBirthYear");
        image = intent.getStringExtra("ProfileImage");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }


}

