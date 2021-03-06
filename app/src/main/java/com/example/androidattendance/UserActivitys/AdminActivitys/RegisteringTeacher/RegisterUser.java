package com.example.androidattendance.UserActivitys.AdminActivitys.RegisteringTeacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidattendance.R;
import com.example.androidattendance.User.User;
import com.example.androidattendance.UserActivitys.AdminActivitys.AdminActivity;
import com.example.androidattendance.UserActivitys.TeacherActivitys.Attendance.CheckAttendance;
import com.example.androidattendance.UserActivitys.TeacherActivitys.UserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {

    private EditText registerUserName,registerPassword,registerType,registerPhoneNu,registerName;
    private Button registerButton;
    private ImageView goBack;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        registerUserName=(EditText) findViewById(R.id.registerEmail);
        registerPassword=(EditText) findViewById(R.id.registerPassword);
        registerButton=(Button) findViewById(R.id.registerButton);
        registerType=(EditText) findViewById(R.id.registerType);
        registerPhoneNu=(EditText) findViewById(R.id.phoneNu);
        registerName=(EditText) findViewById(R.id.registerName);


        Intent intent=getIntent();
        final String name=intent.getStringExtra("name");
        final String phone=intent.getStringExtra("phoneNu");
        final String type=intent.getStringExtra("type");
        goBack=(ImageView)findViewById(R.id.toolbar_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterUser.this, AdminActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("phoneNu",phone);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("user");

        //first we go through validation and if it passes we store the data in the database
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(validate()){
                    String userName = registerUserName.getText().toString();
                    String password = registerPassword.getText().toString();
                    String type=registerType.getText().toString();
                    String phoneNu=registerPhoneNu.getText().toString();
                    String name=registerName.getText().toString();

                    User usersInfo=new User(userName,password,type,phoneNu,name);
                    reference.child(userName).setValue(usersInfo);
                }
            }
        });
    }

    //validating if the EditTexts are not empty
    private Boolean validate(){
        boolean result = false;
        String user = registerUserName.getText().toString();
        String password = registerPassword.getText().toString();
        String type=registerType.getText().toString();
        String phoneNu=registerPhoneNu.getText().toString();
        String name=registerName.getText().toString();


        if(user.isEmpty() || password.isEmpty() || phoneNu.isEmpty() || name.isEmpty() || type.isEmpty()){
            Toast.makeText(this,"Please Enter all details",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"User has been registered",Toast.LENGTH_SHORT).show();
            result =true;
        }
        return result;
    }

}