package com.example.medicalapp.alarm;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.medicalapp.AlarmFragment;
import com.example.medicalapp.Base.BaseActivity;
import com.example.medicalapp.R;
import com.example.medicalapp.database.alarmmodel;

import java.util.Calendar;
import java.util.List;

public class alarminfo extends BaseActivity {

com.example.medicalapp.alarm.alarmviewmodel alarmviewmodel;
EditText titletake,descriptake;
int hours ,minutes,checker;
String title,desc;
TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarminfo);
        titletake = findViewById(R.id.titlealram);
        descriptake =findViewById(R.id.description);
        time = findViewById(R.id.add_data);
        alarmviewmodel = ViewModelProviders.of(this).get(com.example.medicalapp.alarm.alarmviewmodel.class);
        checker=0;
subscribetolivedata();
if(AlarmFragment.checkupdate){
    titletake.setText(AlarmFragment.alarmupdate.getTitle());
    descriptake.setText(AlarmFragment.alarmupdate.getDescription());
    hours = AlarmFragment.alarmupdate.getHours();
    minutes = AlarmFragment.alarmupdate.getMinutes();
    time();
    }
    }
public void subscribetolivedata(){

        alarmviewmodel.alarms.observe(activity, new Observer<List<alarmmodel>>() {
            @Override
            public void onChanged(List<alarmmodel> alarmmodels) {
                alarmviewmodel.broadcastalarm(alarminfo.this,-1,true);
            }
        });
}
    public void finishsaving(View view) {
        title = titletake.getText().toString();
        desc = descriptake.getText().toString();
if(title.isEmpty() && desc.isEmpty() && checker == 0){
    //aLART
    showMessage("Error","please full all filed","ok");
}
else {
    if(AlarmFragment.checkupdate){
        AlarmFragment.alarmupdate.setTitle(title);
        AlarmFragment.alarmupdate.setDescription(desc);
        AlarmFragment.alarmupdate.setHours(hours);
        AlarmFragment.alarmupdate.setMinutes(minutes);
        AlarmFragment.alarmupdate.setIsworking(true);
        alarmviewmodel.updatealarm(AlarmFragment.alarmupdate);
    }
        else{
        alarmviewmodel.addalarmdb(title, desc, hours, minutes, true);
    }
    AlarmFragment.checkupdate=false;
        AlarmFragment.fristtime =true;
        finish();
    }
    }


    public void setime(View view) {
    onopentimepacker();
    }

    public void onopentimepacker (){
     Calendar calendar =Calendar.getInstance();

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
              checker =1;
            hours = hourOfDay;
            minutes= minute;

            time();

            }
        },calendar.get(calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
        timePickerDialog.show();
    }
    public void time( ){
        int hourssss = hours;
            if(hours >=12){
                if(hours == 12)
                    if(minutes<10)
                        time.setText("12 : 0"+minutes+" pm");
                    else
                        time.setText("12 : "+minutes+" pm");
                else {
                    hourssss = hours-12;
                    if(minutes<10)
                        time.setText(hourssss+" : 0"+minutes+" pm");
                    else
                        time.setText(hourssss +" : "+minutes+" pm");
                }}
            else if (hours <12){
                if(hours == 0) {
                    if(minutes<10)
                        time.setText("12 : 0"+minutes+" am");
                    else
                        time.setText("12 : " + minutes + " am");
                }else{
                    if(minutes<10)
                        time.setText(hours+" : 0"+minutes+" am");
                    else
                        time.setText(hours +" : "+minutes+" am");
                }
            }

}}
