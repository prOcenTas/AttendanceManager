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

public class ProfileUser extends AppCompatActivity {
    private ImageView goBack;
    private TextView namePassed,phonePassed,typePassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        namePassed=findViewById(R.id.namePassed);
        phonePassed=findViewById(R.id.phonePassed);
        typePassed=findViewById(R.id.typePassed);


        //getting the user's information
        Intent profileIntent=getIntent();
        final String name=profileIntent.getStringExtra("name");
        final String phone=profileIntent.getStringExtra("phoneNu");
        final String type=profileIntent.getStringExtra("type");

        //passing the data to the text fields
        namePassed.setText(name);
        phonePassed.setText(phone);
        typePassed.setText(type);

        //we going back
        goBack=findViewById(R.id.toolbar_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileUser.this, UserActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("phoneNu",phone);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });
    }

    public void signOut(View view) {
        Intent intent=new Intent(ProfileUser.this, LoginActivity.class);
        intent.putExtra("name",namePassed.getText().toString());
        intent.putExtra("phoneNu",phonePassed.getText().toString());
        intent.putExtra("type",typePassed.getText().toString());
        startActivity(intent);
    }
}