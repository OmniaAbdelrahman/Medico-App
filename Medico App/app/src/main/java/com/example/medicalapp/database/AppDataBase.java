package com.example.medicalapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.medicalapp.API.DiagnosesModel.Issue;
import com.example.medicalapp.API.DiagnosesModel.diagnosesDao;
import com.example.medicalapp.API.DoctorsModel.DataItem;
import com.example.medicalapp.API.DoctorsModel.DoctorDao;
import com.example.medicalapp.API.DoctorsModel.objectlistconverter;
import com.example.medicalapp.API.DoctorsModel.practicesItemconverter;
import com.example.medicalapp.API.DoctorsModel.profileconverter;
import com.example.medicalapp.API.DoctorsModel.specialtiesconverter;
import com.example.medicalapp.API.SymptomModel.SymptomResponse;
import com.example.medicalapp.API.SymptomModel.symptomDao;

@Database(entities = {alarmmodel.class,SymptomResponse.class
, Issue.class,DataItem.class},version = 1,exportSchema = false)
@TypeConverters({profileconverter.class, specialtiesconverter.class,objectlistconverter.class,practicesItemconverter.class})
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase Mydatabase;
    public static AppDataBase getInstance(Context context){
        if(Mydatabase == null){
            Mydatabase = Room.databaseBuilder(context, AppDataBase.class,
                    "medicaldatabase").fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return Mydatabase;
    }
    public abstract diagnosesDao diagnosesDao();
    public  abstract DoctorDao doctorDao();
    public abstract symptomDao symptomDao();
    public abstract alarmDao alarmDao();
}
