package com.example.medicalapp.API.SymptomModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface symptomDao {
@Insert
public void add_symptoms(List<SymptomResponse> symptomResponses);
@Update
    public void update_symptoms(List<SymptomResponse> symptomDaos);
@Query("select * from SymptomResponse")
    List<SymptomResponse>SYMPTOM_RESPONSES();
}
