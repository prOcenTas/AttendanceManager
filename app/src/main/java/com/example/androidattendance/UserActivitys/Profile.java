package com.example.androidattendance.UserActivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidattendance.R;
import com.example.androidattendance.StartActivitys.LoginActivity;
import com.example.androidattendance.UserActivitys.TeacherActivitys.UserActivity;

public class Profile extends AppCompatActivity {
    private ImageView goBack;
    private TextView namePassed,phonePassed,typePassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        namePassed=findViewById(R.id.namePassed);
        phonePassed=findViewById(R.id.phonePassed);
        typePassed=findViewById(R.id.typePassed);

        //we going back
        goBack=findViewById(R.id.toolbar_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this, UserActivity.class);
                startActivity(intent);
            }
        });

        //getting the user's information
        Intent profileIntent=getIntent();
        String name=profileIntent.getStringExtra("name");
        String phone=profileIntent.getStringExtra("phoneNu");
        String type=profileIntent.getStringExtra("type");

        //passing the data to the text fields
        namePassed.setText(name);
        phonePassed.setText(phone);
        typePassed.setText(type);
    }

    public void signOut(View view) {
        Intent intent=new Intent(Profile.this, LoginActivity.class);
        startActivity(intent);
    }
}