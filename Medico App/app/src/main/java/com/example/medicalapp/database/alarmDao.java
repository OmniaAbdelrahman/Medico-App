package com.example.medicalapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface alarmDao {
    @Insert
    public void insertalarm(alarmmodel alarm);
    @Delete
    public void deletealarm(alarmmodel alarmmodel);
    @Update
    public void updatealarm(alarmmodel alarmmodel);
    @Query("select * from alarmmodel")
    List<alarmmodel>showalarms();
    @Query("delete from alarmmodel")
    public void deleteall();
}
