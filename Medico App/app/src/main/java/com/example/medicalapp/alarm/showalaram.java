package com.example.medicalapp.alarm;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.R;

public class showalaram extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    TextView title ,description,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showalaram);
        setview();
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer med) {
                mediaPlayer.start();
            }
        });
    }
        public void destoryalarm(View view) {
            mediaPlayer.stop();

            finish();

        }
        public void setview(){
            int hours ,minutes;
            title = findViewById(R.id.alarmtitleshow);
            description =findViewById(R.id.dcerip);
            time = findViewById(R.id.alarmtimershow);
            title.setText(broadcasralarm.alarm.getTitle());
            description.setText(broadcasralarm.alarm.getDescription());
            hours = broadcasralarm.alarm.getHours();
            minutes = broadcasralarm.alarm.getMinutes();
            if(hours >=12){
                if(hours == 12)
                    if(minutes<10)
                        time.setText("12 : 0"+minutes+" pm");
                    else
                        time.setText("12 : "+minutes+" pm");
                else {
                    hours = hours-12;
                    if(minutes<10)
                        time.setText(hours+" : 0"+minutes+" pm");
                    else
                        time.setText(hours +" : "+minutes+" pm");
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
                }}
        }

}

