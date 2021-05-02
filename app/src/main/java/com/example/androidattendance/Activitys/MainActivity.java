package com.example.androidattendance.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidattendance.R;
import com.example.androidattendance.User.RegisterUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v)
    {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void register(View v)
    {
        startActivity(new Intent(this, RegisterUser.class));
    }
}