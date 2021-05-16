package com.example.androidattendance.UserActivitys.TeacherActivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidattendance.R;
import com.example.androidattendance.StartActivitys.LoginActivity;
import com.example.androidattendance.UserActivitys.AdminActivitys.AdminActivity;
import com.example.androidattendance.UserActivitys.TeacherActivitys.Attendance.CheckAttendance;

public class UserActivity extends AppCompatActivity {
    ImageView ADSbutton,ESWbutton,ANDbutton,DAIbutton;
    TextView welcmUser;

    int request_code=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ADSbutton=findViewById(R.id.ADSbutton);
        ANDbutton=findViewById(R.id.ANDbutton);
        DAIbutton=findViewById(R.id.DAIbutton);
        ESWbutton=findViewById(R.id.ESWbutton);

        welcmUser=findViewById(R.id.welcomingUser);
        Intent userIntent=getIntent();
        String namePassed=userIntent.getStringExtra("name");
        welcmUser.setText(namePassed);
    }

    public void android(View view) {
        String className="AND";

        Intent intent=new Intent(UserActivity.this, CheckAttendance.class);
        intent.putExtra("Lecture",className);
        startActivityForResult(intent,request_code);
    }

    public void embedded(View view) {
        String className="ESW";

        Intent intent=new Intent(UserActivity.this, CheckAttendance.class);
        intent.putExtra("Classname",className);
        startActivityForResult(intent,request_code);
    }

    public void dataWarehousing(View view) {
        String className="DAI";

        Intent intent=new Intent(UserActivity.this, CheckAttendance.class);
        intent.putExtra("Classname",className);
        startActivityForResult(intent,request_code);
    }

    public void algorithm(View view) {
        String className="ADS";

        Intent intent=new Intent(UserActivity.this, CheckAttendance.class);
        intent.putExtra("Classname",className);
        startActivityForResult(intent,request_code);
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
//                Intent intent=new Intent(UserActivity.this, LoginActivity.class);
//                startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}