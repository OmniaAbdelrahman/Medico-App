package com.example.medicalapp;

public class User {
    private String name , email,phone , password , gender , date ;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public User(){}
    public User(String name , String email ,String phone, String password , String gender, String date )
    {
       this.name=name;
       this.email=email;
       this.phone=phone;
       this.password=password;
       this.gender=gender;
       this.date=date;
       //this.imgUrl=imgUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl;}



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() { return gender;}

    //public String getImgUrl() { return  imgUrl;}


}
