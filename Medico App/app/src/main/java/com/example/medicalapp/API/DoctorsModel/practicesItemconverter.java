package com.example.medicalapp.API.DoctorsModel;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class practicesItemconverter {
    @TypeConverter
    public static List<PracticesItem> fromString(String value) {
        Type listType = new TypeToken<List<PracticesItem>>() {}.getType();
        //System.out.println((List<PracticesItem>) new Gson().fromJson(value, listType)+""+"hellllo");
        List<PracticesItem>practicesItems= new Gson().fromJson(value, listType);
        return  practicesItems;
    }

    @TypeConverter
    public static String fromArrayList(List<PracticesItem> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
}
}
