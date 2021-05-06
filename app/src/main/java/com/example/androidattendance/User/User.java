package com.example.androidattendance.User;

public class User {

   private String email,password,type;

    public User(String email,String password,String type)
    {
        this.email=email;
        this.password=password;
        this.type=type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }
}
