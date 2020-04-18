package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalapp.Base.BaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class LogInActivity extends BaseActivity {

    EditText txt_Email , txt_Password;
    CheckBox checkpass;

    Button btn_login ;
    String user_email,user_name,user_gender,user_password;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        TextView signup=(TextView)findViewById(R.id.signup_txt);
        SharedPreferences preferences = getSharedPreferences("Userapp",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user");
        editor.commit();
        String text="You don't have account ? Sign Up";
        SpannableString ss=new SpannableString(text);
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent= new Intent(LogInActivity.this,Sign_up.class);
                startActivity(intent);
                finish();
            }
        };

     ss.setSpan(clickableSpan,24,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
     signup.setText(ss);
     signup.setMovementMethod(LinkMovementMethod.getInstance());

        txt_Email=(EditText)findViewById(R.id.mail_edittext);
        txt_Password=(EditText)findViewById(R.id.pass);
        btn_login=(Button) findViewById(R.id.signin_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_Email.getText().toString().trim();
                String password = txt_Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password is too short , at least 6", Toast.LENGTH_SHORT).show();
                }
                else{
showProgressBar(R.string.loading);
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
hideProgressDialog();

                                    RetriveData();
                                } else {
                                    hideProgressDialog();
                                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
            }
        });

        checkpass=(CheckBox)findViewById(R.id.showpass);
        checkpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkpass.isChecked()){

                    txt_Password.setTransformationMethod(null);


                }
                else{
                    txt_Password.setTransformationMethod(new PasswordTransformationMethod());




                }

            }
        });
    }
    public void RetriveData()
    {
        RootRef.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if((dataSnapshot.exists())&&(dataSnapshot.hasChild("image"))){

                            String user_email=dataSnapshot.child("email").getValue().toString();
                            String user_name=dataSnapshot.child("name").getValue().toString();
                            String user_phone=dataSnapshot.child("phone").getValue().toString();
                            String user_gender=dataSnapshot.child("gender").getValue().toString();
                            String user_password=dataSnapshot.child("password").getValue().toString();
                            String user_date=dataSnapshot.child("date").getValue().toString();
                            String img = dataSnapshot.child("image").getValue().toString();
                            User user =new User(user_name,user_email,user_phone,user_password,user_gender,user_date);
                            Constants.CURRENT_USER = user;
                            Gson gson = new Gson();
                            String useracc = gson.toJson(user);
                            savestring("user",useracc);
                                    getdata(user,img);
                        }
                        else {
                            String user_email=dataSnapshot.child("email").getValue().toString();
                            String user_name=dataSnapshot.child("name").getValue().toString();
                            String user_phone=dataSnapshot.child("phone").getValue().toString();
                            String user_gender=dataSnapshot.child("gender").getValue().toString();
                            String user_password=dataSnapshot.child("password").getValue().toString();
                            String user_date=dataSnapshot.child("date").getValue().toString();
                            User user =new User(user_name,user_email,user_phone,user_password,user_gender,user_date);
                            Constants.CURRENT_USER = user;
                            Gson gson = new Gson();
                            String useracc = gson.toJson(user);
                            savestring("user",useracc);
                            getdata(user,"DEFAULT");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
    public void getdata(User user , String img ) {
        //MoveToNewActivity(user);
        Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
        intent.putExtra("UserEmail", user.getEmail());
        intent.putExtra("UserName", user.getName());
        intent.putExtra("UserPhone", user.getPhone());
        intent.putExtra("UserGender", user.getGender());
        intent.putExtra("UserPassword", user.getPassword());
        intent.putExtra("UserBirthYear", user.getDate());
        intent.putExtra("ProfileImage", img);
        startActivity(intent);
        finish();
    }
    public void savestring (String key ,String value){
        SharedPreferences.Editor editor =
                getSharedPreferences("Userapp",MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.apply();
    }

}

