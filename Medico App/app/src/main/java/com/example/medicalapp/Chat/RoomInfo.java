package com.example.medicalapp.Chat;

public class RoomInfo {
    private String name;
    private int photo;

    public RoomInfo(String name, int photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public int getPhoto() {
        return photo;
    }
}
