package com.example.androidattendance.UserActivitys.AdminActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



import com.example.androidattendance.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


    }

    public void register(View view)
    {
        startActivity(new Intent(this,RegisterUser.class));
    }
}