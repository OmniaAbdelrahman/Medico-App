package com.example.medicalapp.API.DrugModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface drugDAo {
@Insert
    public void  add_drug(List<RecordsItem> recordsItems);
@Update
    public void update_drug(List<RecordsItem> recordsItems);
@Query("select * from RecordsItem")
    List<RecordsItem>RECORDS_ITEMS();
}
