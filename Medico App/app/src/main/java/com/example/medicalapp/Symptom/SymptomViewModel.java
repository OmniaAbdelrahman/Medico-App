package com.example.medicalapp.Symptom;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalapp.API.APIManager;
import com.example.medicalapp.API.DiagnosesModel.Issue;
import com.example.medicalapp.API.SymptomModel.SymptomResponse;
import com.example.medicalapp.Constants;
import com.example.medicalapp.database.AppDataBase;
import com.example.medicalapp.database.webdatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SymptomViewModel extends AndroidViewModel {



    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public MutableLiveData<String> alertMessage = new MutableLiveData<>();

    public MutableLiveData<List<SymptomResponse>> symptom = new MutableLiveData<>();
List<SymptomResponse>issues;
    public SymptomViewModel(@NonNull Application application) {
        super(application);
    }


    public void loadSymptom() {
        showLoading.setValue(true);
        APIManager.getSymptomChkerApis().getSymptoms(Constants.Language).enqueue(new Callback<List<SymptomResponse>>() {
            @Override
            public void onResponse(Call<List<SymptomResponse>> call, Response<List<SymptomResponse>> response) {
                if (!response.isSuccessful()) {

                    return;
                }
                showLoading.setValue(false);

                symptom.setValue(response.body());
                if(AppDataBase.getInstance(getApplication()).symptomDao().SYMPTOM_RESPONSES()==null ||
                        AppDataBase.getInstance(getApplication()).symptomDao().SYMPTOM_RESPONSES().isEmpty()){
                AppDataBase.getInstance(getApplication()).symptomDao().add_symptoms(response.body());}
                else { AppDataBase.getInstance(getApplication()).symptomDao().update_symptoms(response.body());}
            }

            @Override
            public void onFailure(Call<List<SymptomResponse>> call, Throwable t) {
                showLoading.setValue(false);
                symptom.postValue(  AppDataBase.getInstance(getApplication()).symptomDao().SYMPTOM_RESPONSES());
            }

        });

    }

}