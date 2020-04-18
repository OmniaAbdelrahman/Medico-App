package com.example.medicalapp.API.DiagnosesModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface diagnosesDao {
    @Insert
    public void add_diagnose(List<Issue> issues);
    @Update
    public void update_diagnose(List<Issue> issues);
    @Query("select * from Issue")
    List<Issue>ISSUES();
}
