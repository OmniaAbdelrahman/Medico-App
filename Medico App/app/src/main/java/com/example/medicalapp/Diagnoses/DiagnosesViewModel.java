package com.example.medicalapp.Diagnoses;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.medicalapp.API.APIManager;
import com.example.medicalapp.API.DiagnosesModel.DiagnosesResponse;
import com.example.medicalapp.API.DiagnosesModel.Issue;
import com.example.medicalapp.Constants;
import com.example.medicalapp.Symptom.SymptomAdapter;
import com.example.medicalapp.database.AppDataBase;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiagnosesViewModel extends AndroidViewModel {
    public MutableLiveData<List<DiagnosesResponse>> Diagnoses = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public MutableLiveData<String> alertMessage = new MutableLiveData<>();

    public DiagnosesViewModel(@NonNull Application application) {
        super(application);
    }

    List<Issue> issues;

    public void loadDiadnoses(List<String> checkedSymptoms) {
        issues = new ArrayList<>();
        JSONArray arr = new JSONArray();

        System.out.println(checkedSymptoms.size() + "--------------------");
        for (int i = 0; i < checkedSymptoms.size(); i++) {
            arr.put(checkedSymptoms.get(i));
            System.out.println("hi ");
        }

showLoading.setValue(true);
        APIManager.getSymptomChkerApis().getDiagnoses(arr, "male", 1984, Constants.Language).enqueue(new Callback<List<DiagnosesResponse>>() {
            @Override
            public void onResponse(Call<List<DiagnosesResponse>> call, Response<List<DiagnosesResponse>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("leeeh kda");
                    return;
                }
                showLoading.setValue(false);


                if (response.body() == null || response.body().size() == 0) {
                    Diagnoses.setValue(new ArrayList<DiagnosesResponse>());

                    savedata(new ArrayList<DiagnosesResponse>());
                    AppDataBase.getInstance(getApplication()).diagnosesDao().add_diagnose(issues);
                } else {
                    Diagnoses.setValue(response.body());
                    if ((response.body()) != null) {
                        savedata(response.body());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<DiagnosesResponse>> call, Throwable t) {
                showLoading.setValue(false);
                Diagnoses.postValue(converter());
            }
        });
    }

    public void savedata(List<DiagnosesResponse> diagnosesResponses) {

        for (DiagnosesResponse dia : diagnosesResponses) {
            issues.add(dia.getIssue());
        }
        if (issues != null) {

                AppDataBase.getInstance(getApplication()).diagnosesDao().add_diagnose(issues);

            }
    }



    public List<DiagnosesResponse> converter () {
        issues = AppDataBase.getInstance(getApplication()).diagnosesDao().ISSUES();
        List<DiagnosesResponse> diagnosesResponse = new ArrayList<>();
        for (Issue i : issues) {
            DiagnosesResponse diagnosesResponse1 = new DiagnosesResponse();
            diagnosesResponse1.setIssue(i);
            diagnosesResponse1.setSpecialisation(null);
            diagnosesResponse.add(diagnosesResponse1);

        }
        return diagnosesResponse;
    }
}