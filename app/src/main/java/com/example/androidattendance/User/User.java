package com.example.androidattendance.User;

public class User {

   private String userName,password,type,phoneNu,name;

    public User(String userName,String password,String type,String phoneNu,String name)
    {
        this.userName=userName;
        this.password=password;
        this.type=type;
        this.phoneNu=phoneNu;
        this.name=name;
    }

    public String getUser() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String getPhoneNu() {
        return phoneNu;
    }

    public String getName() {
        return name;
    }

    public void setUser(String email) {
        this.userName = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhoneNu(String phoneNu) {
        this.phoneNu = phoneNu;
    }

    public void setName(String name) {
        this.name = name;
    }
}
