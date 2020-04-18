package com.example.medicalapp.Base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    public AppCompatActivity activity;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
    }

    public AlertDialog showConfirmationDialog(int title,int message,int posText,
                                              AlertDialog.OnClickListener posAction,boolean isCancelable){
        alertDialog=new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(posText,posAction)
                .setCancelable(isCancelable)
                .show();
        return alertDialog;
    }
    public AlertDialog showMessage(int title,int message,int posText){
        alertDialog=new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(posText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
        return alertDialog;
    }
    public AlertDialog showConfirmationDialog(String title,String message,String posText,
                                              AlertDialog.OnClickListener posAction,boolean isCancelable){
        alertDialog=new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(posText,posAction)
                .setCancelable(isCancelable)
                .show();
        return alertDialog;
    }
    public AlertDialog showMessage(String title,String message,String posText){
        alertDialog=new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(posText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
        return alertDialog;
    }

    ProgressDialog progressDialog;
    public ProgressDialog showProgressBar(int message){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(message));
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }
    public void hideProgressDialog(){
        if(progressDialog!=null)
            progressDialog.dismiss();
    }


}
