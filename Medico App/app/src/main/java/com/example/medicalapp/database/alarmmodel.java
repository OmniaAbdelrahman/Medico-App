package com.example.medicalapp.database;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.medicalapp.alarm.broadcasralarm;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.ALARM_SERVICE;

@Entity
public class alarmmodel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String Title;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private int hours;
    @ColumnInfo
    private int minutes;
    @ColumnInfo
    private Boolean isworking;
    @Ignore
    Set<Integer> DL =new HashSet<>();

    public Boolean getIsworking() {
        return isworking;
    }

    public void setIsworking(Boolean isworking) {
        this.isworking = isworking;
    }

    public alarmmodel() {
    }

    @Ignore
    public alarmmodel(String title, String description, int hours, int minutes, Boolean isworking) {
        Title = title;
        this.description = description;
        this.hours = hours;
        this.minutes = minutes;
        this.isworking = isworking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Ignore
    public void setalarmdata(Context context, int id, alarmmodel alarm, int list , boolean switcher){
        DL.add(list);
        PendingIntent pendingIntent;
        AlarmManager manager;
        Intent intent = new Intent(context, broadcasralarm.class);
        pendingIntent =  PendingIntent.getBroadcast(context,id,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);
        manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        if(!(DL.contains(alarm.id))&&switcher){
      Calendar calendar = Calendar.getInstance();
        int hourss ,minutess ;
        hourss= hours;
        minutess=minutes;
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,hourss);
        calendar.set(Calendar.MINUTE,minutess);
        calendar.set(Calendar.SECOND,0);
        intent.putExtra("alarmmin",minutess);
        intent.putExtra("tittle",alarm.Title);
        intent.putExtra("alarmhour",hourss);
        intent.putExtra("dessc",alarm.description);
        intent.putExtra("currentmiles",System.currentTimeMillis());
        intent.putExtra("alarmmiles",calendar.getTimeInMillis());
        intent.putExtra("check",alarm.isworking);
        pendingIntent =  PendingIntent.getBroadcast(context,id,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);
        manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }

    else {

            manager.cancel(pendingIntent);
        }

    }
}






