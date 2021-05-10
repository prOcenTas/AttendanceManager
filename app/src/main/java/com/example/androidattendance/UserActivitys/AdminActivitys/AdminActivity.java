package com.example.androidattendance.UserActivitys.AdminActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.AdminActivitys.AddingStudent.AdminAddStudent;

public class AdminActivity extends AppCompatActivity {
    Button addStudent, studentAttendance, checkStudent;
    TextView welcmUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addStudent=findViewById(R.id.addStudentButton);
        studentAttendance=findViewById(R.id.studentAttendanceButton);
        checkStudent=findViewById(R.id.checkStudentButton);

        //welcoming user with their name
        welcmUser=findViewById(R.id.welcomingUser);
        Intent userIntent=getIntent();
        String namePassed=userIntent.getStringExtra("name");
        welcmUser.setText(namePassed);


    }

    public void register(View view)
    {
        startActivity(new Intent(this,RegisterUser.class));
    }

    public void manageStudent(View view)
    {
        startActivity(new Intent(AdminActivity.this, AdminAddStudent.class));
    }


}