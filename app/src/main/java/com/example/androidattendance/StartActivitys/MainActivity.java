package com.example.androidattendance.StartActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.AdminActivitys.RegisteringTeacher.RegisterUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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