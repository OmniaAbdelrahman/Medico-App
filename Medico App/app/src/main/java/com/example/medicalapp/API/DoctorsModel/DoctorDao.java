package com.example.medicalapp.API.DoctorsModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.medicalapp.API.DiagnosesModel.Issue;

import java.util.List;

@Dao
public interface DoctorDao
{
    @Insert
    public void add_docotr(List<DataItem> issues);
    @Update
    public void update_doctor(List<DataItem> issues);
    @Query("select * from DataItem")
    List<DataItem>Doctors();
}
