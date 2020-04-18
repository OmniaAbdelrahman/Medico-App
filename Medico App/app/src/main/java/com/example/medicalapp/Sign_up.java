package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicalapp.Base.BaseActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_up extends BaseActivity {
    EditText txtName , txtEmail ,txtphone, txtPassword , txtConfirmPassword ;
    RadioButton radioGenderMale , radioGenderFemale;
    Spinner data_spinner;
    Button btn_register ;
    DatabaseReference databaseReference;
    String gender="";
    CheckBox checkpass;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth ;
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN=123;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Sign_up.this,LogInActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Casting Views
        data_spinner=(Spinner)findViewById(R.id.date);
        txtName=(EditText)findViewById(R.id.name_edittext);
        txtEmail=(EditText)findViewById(R.id.email_edittext);
        txtphone=(EditText)findViewById(R.id.phone_edittext);
        txtPassword=(EditText)findViewById(R.id.pass_edittext);
        txtConfirmPassword=(EditText)findViewById(R.id.pass_conf_edittext);
        btn_register=(Button)findViewById(R.id.Register_btn);
        radioGenderMale=(RadioButton)findViewById(R.id.male_radiobutton);
        radioGenderFemale=(RadioButton)findViewById(R.id.female_radiobuttin);
        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                String phone=txtphone.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmpassword = txtConfirmPassword.getText().toString();
                String date=data_spinner.getSelectedItem().toString();

                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                }
                if(password.length()<6)
                {
                    Toast.makeText(getApplicationContext(), "Password is too short , at least 6", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(confirmpassword))
                {
                    Toast.makeText(getApplicationContext(), "Please Confirm Password", Toast.LENGTH_SHORT).show();
                }
                if(!radioGenderMale.isChecked()&& !radioGenderFemale.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Gender", Toast.LENGTH_SHORT).show();
                }
                if(password.equals(confirmpassword))
                {
                    showProgressBar(R.string.loading);
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) //////////?????
                                    {
                                        hideProgressDialog();
                                        String name = txtName.getText().toString();
                                        String email = txtEmail.getText().toString();
                                        String phone=txtphone.getText().toString();
                                        String password = txtPassword.getText().toString();
                                        String gender="";
                                        String date=data_spinner.getSelectedItem().toString();
                                        //String imgUrl=" ";
                                        if(radioGenderMale.isChecked())
                                        {
                                            gender="Male";
                                        }
                                        if(radioGenderFemale.isChecked())
                                        {
                                            gender="Female";
                                        }
                                        User user_info = new User(name,email,phone,password,gender,date);
                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user_info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(getApplicationContext(), "Registeration Completed Successfully", Toast.LENGTH_SHORT).show();
                                                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                                Intent intent= new Intent(Sign_up.this,LogInActivity.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                        });

                                    }


                                }
                            });

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Confirm Correct Password ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mAuth =FirebaseAuth.getInstance();

         checkpass=(CheckBox)findViewById(R.id.showpass);
        checkpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkpass.isChecked()){

                    txtPassword.setTransformationMethod(null);
                    txtConfirmPassword.setTransformationMethod(null);


                }
                else{
                    txtPassword.setTransformationMethod(new PasswordTransformationMethod());
                    txtConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());




                }

            }
        });

    }


}
