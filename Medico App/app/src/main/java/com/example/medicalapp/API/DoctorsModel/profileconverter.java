package com.example.medicalapp.API.DoctorsModel;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class profileconverter {
    @TypeConverter
    public static Profile fromString(String value) {
        Type listType = new TypeToken<Profile>() {}.getType();
        return new Gson().fromJson(value, listType);
    }


    @TypeConverter
    public static String fromprofile(Profile list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
}
}
