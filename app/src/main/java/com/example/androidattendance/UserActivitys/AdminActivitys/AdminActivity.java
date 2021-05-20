package com.example.androidattendance.UserActivitys.AdminActivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.AdminActivitys.AddingStudent.AdminAddStudent;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.CheckCourse;
import com.example.androidattendance.UserActivitys.AdminActivitys.RegisteringTeacher.RegisterUser;
import com.example.androidattendance.UserActivitys.ProfileAdmin;
import com.example.androidattendance.UserActivitys.ProfileUser;

public class AdminActivity extends AppCompatActivity {
    private  LinearLayout manageStudent, register, courses;
    private TextView welcmUser;
    private ImageView userProfile;
    private String namePassed,phonePassed,typePassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        manageStudent=findViewById(R.id.manageStudent);
        courses=findViewById(R.id.courses);
        register=findViewById(R.id.registerClick);

        //welcoming user with their name
        welcmUser=findViewById(R.id.welcomingUser);
        Intent userIntent=getIntent();
        namePassed=userIntent.getStringExtra("name");
        phonePassed=userIntent.getStringExtra("phoneNu");
        typePassed=userIntent.getStringExtra("type");
        welcmUser.setText(namePassed);

        userProfile=findViewById(R.id.profile);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent=new Intent(AdminActivity.this, ProfileAdmin.class);
                profileIntent.putExtra("name",namePassed);
                profileIntent.putExtra("phoneNu",phonePassed);
                profileIntent.putExtra("type",typePassed);
                startActivity(profileIntent);
            }
        });

    }

    public void register(View view)
    {
        Intent intent=new Intent(AdminActivity.this, RegisterUser.class);
        intent.putExtra("name",namePassed);
        intent.putExtra("phoneNu",phonePassed);
        intent.putExtra("type",typePassed);
        startActivity(intent);
    }

    public void manageStudent(View view)
    {
        Intent intent=new Intent(AdminActivity.this, AdminAddStudent.class);
        intent.putExtra("name",namePassed);
        intent.putExtra("phoneNu",phonePassed);
        intent.putExtra("type",typePassed);
        startActivity(intent);
    }

    public void checkCourse(View view)
    {
        Intent intent=new Intent(AdminActivity.this, CheckCourse.class);
        intent.putExtra("name",namePassed);
        intent.putExtra("phoneNu",phonePassed);
        intent.putExtra("type",typePassed);
        startActivity(intent);
    }

}