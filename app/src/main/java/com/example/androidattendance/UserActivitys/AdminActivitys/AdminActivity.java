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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.androidattendance.R;
import com.example.androidattendance.StartActivitys.LoginActivity;
import com.example.androidattendance.UserActivitys.AdminActivitys.AddingStudent.AdminAddStudent;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.CheckCourse;
import com.example.androidattendance.UserActivitys.AdminActivitys.RegisteringTeacher.RegisterUser;
import com.example.androidattendance.UserActivitys.Profile;
import com.example.androidattendance.UserActivitys.TeacherActivitys.UserActivity;

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
                Intent profileIntent=new Intent(AdminActivity.this, Profile.class);
                profileIntent.putExtra("name",namePassed);
                profileIntent.putExtra("phoneNu",phonePassed);
                profileIntent.putExtra("type",typePassed);
                startActivity(profileIntent);
            }
        });

    }

    public void register(View view)
    {
        startActivity(new Intent(AdminActivity.this, RegisterUser.class));
    }

    public void manageStudent(View view)
    {
        startActivity(new Intent(AdminActivity.this, AdminAddStudent.class));
    }

    public void checkCourse(View view)
    {
        startActivity(new Intent(AdminActivity.this, CheckCourse.class));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.toolbar, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.signOut:
//                Intent intent=new Intent(AdminActivity.this, LoginActivity.class);
//                startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}