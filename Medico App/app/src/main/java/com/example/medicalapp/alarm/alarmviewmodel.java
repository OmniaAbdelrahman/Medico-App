package com.example.medicalapp.alarm;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalapp.database.AppDataBase;
import com.example.medicalapp.database.alarmmodel;

import java.util.List;

public class alarmviewmodel extends AndroidViewModel {

    public alarmviewmodel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<List<alarmmodel>> alarms = new MutableLiveData<>();
    public void loadalramdb(){
        alarms.postValue(AppDataBase.getInstance(getApplication()).alarmDao().showalarms());

    }
    public void addalarmdb(String title, String desc , int hours,int minutes,boolean isworking){
        alarmmodel alarmmodel = new alarmmodel(title,desc,hours,minutes,true);
        AppDataBase.getInstance(getApplication()).alarmDao().insertalarm(alarmmodel);
        loadalramdb();
    }
    public void broadcastalarm(Context context,int list,boolean bool ){
        if(alarms.getValue()!=null){
            for (int i = 0;i<alarms.getValue().size()
                    ;i++){
                if( alarms.getValue().get(i)
                        .getIsworking()){
                    alarms.getValue().get(i)
                            .setalarmdata(context , alarms.getValue().get(i).getId(),alarms.getValue().get(i)
                            ,list,bool);
                }}
        }}

        public void updatealarm( alarmmodel alram){
        AppDataBase.getInstance(getApplication()).alarmDao().updatealarm(alram);
        loadalramdb();
    }
    public void deletealarm( alarmmodel alram){
        AppDataBase.getInstance(getApplication()).alarmDao().deletealarm(alram);
        loadalramdb();
    }

}
