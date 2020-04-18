package com.example.medicalapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIManager {
    private static Retrofit Newsretrofit;
    private static Retrofit symptomCheaherRetrofit;
    private static Retrofit drugsRetrofit;
    private static  Retrofit DoctorRetrofit;



    public static Retrofit getDoctorInstance(){

        if(DoctorRetrofit==null){
            DoctorRetrofit =new Retrofit.Builder()
                    .baseUrl("https://api.betterdoctor.com/2016-03-01/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return DoctorRetrofit;

    }


    public static Retrofit getNewsInstance(){


        if(Newsretrofit==null){
            Newsretrofit =new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return Newsretrofit;
    }




    public static Retrofit getSymptomCheaherInstance(){
        if(symptomCheaherRetrofit==null){
            symptomCheaherRetrofit =new Retrofit.Builder()
                    .baseUrl("https://priaid-symptom-checker-v1.p.rapidapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return symptomCheaherRetrofit;
    }


    public static Retrofit getDrugsInstance(){
        if(drugsRetrofit==null){
            drugsRetrofit =new Retrofit.Builder()
                    .baseUrl("http://dgidb.org/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return drugsRetrofit;
    }




    public static WebServices getNewsApis(){
        return  getNewsInstance().create(WebServices.class);
    }

    public static WebServices getDrugApi(){
        return getDrugsInstance().create(WebServices.class);
    }

    public static WebServices getDoctorApi(){
        return getDoctorInstance().create(WebServices.class);
    }



    public static WebServices getSymptomChkerApis(){
        return getSymptomCheaherInstance().create(WebServices.class);
    }


}
