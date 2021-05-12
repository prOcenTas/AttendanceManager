package com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.ADS.ShowAdsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CheckCourse extends AppCompatActivity {

    Button checkADS,checkDAI,checkAND,checkESW;
    private DatabaseReference referenceLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        checkADS=(Button)findViewById(R.id.checkAdsButton);
        checkDAI=(Button)findViewById(R.id.checkDaiButton);
        checkESW=(Button)findViewById(R.id.checkEswButton);
        checkAND=(Button)findViewById(R.id.checkAndButton);

        referenceLecture= FirebaseDatabase.getInstance().getReference("lecture");

        checkADS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckCourse.this, ShowAdsActivity.class));
            }
        });
    }
}