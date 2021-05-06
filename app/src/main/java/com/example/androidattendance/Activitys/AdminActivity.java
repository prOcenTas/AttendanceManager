package com.example.androidattendance.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.androidattendance.AdminFragments.AdminMainMenuFragment;
import com.example.androidattendance.R;
import com.example.androidattendance.User.RegisterUser;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar AppBar=findViewById(R.id.AppBar);
        setSupportActionBar(AppBar);


    }

    public void register(View view)
    {
        startActivity(new Intent(this,RegisterUser.class));
    }
}