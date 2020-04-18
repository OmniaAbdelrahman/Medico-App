package com.example.medicalapp.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.medicalapp.database.alarmmodel;

public class broadcasralarm extends BroadcastReceiver {
    public static alarmmodel alarm;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
int alarmminute,alarmhours;
long alarmmiles , alaramcurrent;
alarmminute = bundle.getInt("alarmmin");
alarmhours = bundle.getInt("alarmhour");
alarmmiles = bundle.getLong("alarmmiles");
alaramcurrent = bundle.getLong("currentmiles");
        alarm = new alarmmodel( bundle.getString("tittle"),bundle.getString("dessc"),
                alarmhours,alarmminute,bundle.getBoolean("check"));
    if(alarmmiles>=alaramcurrent) {
        intent = (new Intent(context, showalaram.class));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
}


