package com.example.androidattendance.UserActivitys.AdminActivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.androidattendance.R;
import com.example.androidattendance.StartActivitys.LoginActivity;
import com.example.androidattendance.UserActivitys.AdminActivitys.AddingStudent.AdminAddStudent;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.CheckCourse;
import com.example.androidattendance.UserActivitys.AdminActivitys.RegisteringTeacher.RegisterUser;

public class AdminActivity extends AppCompatActivity {
    Button addStudent, studentAttendance, checkCourse;
    TextView welcmUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addStudent=findViewById(R.id.addStudentButton);
        checkCourse=findViewById(R.id.checkCourseButton);

        //welcoming user with their name
        welcmUser=findViewById(R.id.welcomingUser);
        Intent userIntent=getIntent();
        String namePassed=userIntent.getStringExtra("name");
        welcmUser.setText(namePassed);


    }

    public void register(View view)
    {
        startActivity(new Intent(this, RegisterUser.class));
    }

    public void manageStudent(View view)
    {
        startActivity(new Intent(AdminActivity.this, AdminAddStudent.class));
    }

    public void checkCourse(View view)
    {
        startActivity(new Intent(AdminActivity.this, CheckCourse.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.signOut:
                Intent intent=new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}