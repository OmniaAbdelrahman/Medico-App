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
import com.example.medicalapp.API.DrugModel.RecordsItem;
import com.example.medicalapp.API.DrugModel.drugDAo;
import com.example.medicalapp.API.NewsModel.ArticlesItem;
import com.example.medicalapp.API.NewsModel.newsDao;
import com.example.medicalapp.API.SymptomModel.SymptomResponse;
import com.example.medicalapp.API.SymptomModel.symptomDao;

@Database(entities = {Issue.class, RecordsItem.class, ArticlesItem.class
, DataItem.class} ,version = 1, exportSchema = false)

public abstract class webdatabase extends RoomDatabase {
    private static webdatabase db_referrance;
    public static webdatabase get_instatnce(Context context){
        if(db_referrance == null){
            db_referrance = Room.databaseBuilder(context, webdatabase.class,"webdatabase")
                   .build();
        }
        return db_referrance;
    }


    public abstract drugDAo drugDAo();
    public abstract newsDao newsDao();



}
