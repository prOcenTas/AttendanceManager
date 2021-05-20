package com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.AdminActivitys.AdminActivity;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.ADS.ShowAdsActivity;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.AND.ShowAndActivity;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.DAI.ShowDaiActivity;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.ESW.ShowEswActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CheckCourse extends AppCompatActivity {

    ImageView checkADS,checkDAI,checkAND,checkESW,goBack;
//    private DatabaseReference referenceLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_course);


        checkADS=(ImageView)findViewById(R.id.checkADS);
        checkDAI=(ImageView)findViewById(R.id.checkDAI);
        checkESW=(ImageView)findViewById(R.id.checkESW);
        checkAND=(ImageView)findViewById(R.id.checkAND);

        Intent intent=getIntent();
        final String name=intent.getStringExtra("name");
        final String phone=intent.getStringExtra("phoneNu");
        final String type=intent.getStringExtra("type");
        goBack=(ImageView)findViewById(R.id.toolbar_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckCourse.this, AdminActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("phoneNu",phone);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });

//        referenceLecture= FirebaseDatabase.getInstance().getReference("lecture");

        checkADS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckCourse.this, ShowAdsActivity.class));
            }
        });

        checkDAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckCourse.this, ShowDaiActivity.class));
            }
        });

        checkESW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckCourse.this, ShowEswActivity.class));
            }
        });

        checkAND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckCourse.this, ShowAndActivity.class));
            }
        });
    }
}