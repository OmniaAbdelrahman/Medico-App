package com.example.medicalapp.API;


import com.example.medicalapp.API.DiagnosesModel.DiagnosesResponse;
import com.example.medicalapp.API.DoctorsModel.DoctorResponse;
import com.example.medicalapp.API.DrugModel.DrugInfoModel.DrugInfoResponse;
import com.example.medicalapp.API.DrugModel.DrugResponse;
import com.example.medicalapp.API.NewsModel.NewsResponse;
import com.example.medicalapp.API.SymptomModel.SymptomResponse;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServices {

// Rabid Api methods
    @Headers({

    "X-RapidAPI-Host: priaid-symptom-checker-v1.p.rapidapi.com",
     "X-RapidAPI-Key: 33310b876bmsh77e2f780c2c8278p1e049djsn00702b1c2550"
    })
    @GET("symptoms")
    Call<List<SymptomResponse>> getSymptoms(@Query("language")String language);



    @Headers({
            "X-RapidAPI-Host:priaid-symptom-checker-v1.p.rapidapi.com",
            "X-RapidAPI-Key:33310b876bmsh77e2f780c2c8278p1e049djsn00702b1c2550"
    })
    @GET("diagnosis")
    Call<List<DiagnosesResponse>> getDiagnoses(@Query("symptoms") JSONArray symptomArray,@Query("gender")String gender,@Query("year_of_birth")int  year,@Query("language") String language);




//DoctorApi
@GET("doctors")
Call<DoctorResponse>getDoctors(@Query("user_key")String apiKey,@Query("name")String name,@Query("limit")String limit);
//DrugsApi
    @GET("drugs")
    Call<DrugResponse> getDrugs(@Query("page")String page,@Query("count")String count);

    @GET("drugs/{chembl_id}")
    Call<DrugInfoResponse>getDrugInfo(@Path("chembl_id")String chembl_idٍ);


    //InferamidecaApi

    @Headers({"App-Id:281fe9c7",
            "App-Key:648fad60ec47148a7b6a4cebdc871402",
            "Content-Type: application/json",
            "Dev-Mode: true",
            "Accept: application/json",

 })
    //NewsApi
    @GET("everything")
    Call<NewsResponse>getNews(@Query("q")String Q,@Query("apiKey")String ApiKey);

}
