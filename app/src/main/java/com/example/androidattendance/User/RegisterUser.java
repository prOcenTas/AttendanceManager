package com.example.androidattendance.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidattendance.Activitys.AdminActivity;
import com.example.androidattendance.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {

    private EditText registerEmail,registerPassword,registerType;
    private Button registerButton;

    private FirebaseAuth fireBaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        registerEmail=(EditText) findViewById(R.id.registerEmail);
        registerPassword=(EditText) findViewById(R.id.registerPassword);
        registerButton=(Button) findViewById(R.id.registerButton);
        registerType=(EditText) findViewById(R.id.registerType);

        fireBaseAuth=FirebaseAuth.getInstance();


        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(validate()){
                    // Register to database upload
                    String user_email = registerEmail.getText().toString().trim();
                    String user_password = registerPassword.getText().toString().trim();
                    fireBaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterUser.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterUser.this, AdminActivity.class));
                            }else{
                                Toast.makeText(RegisterUser.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }

    private Boolean validate(){
        boolean result = false;
        String name = registerEmail.getText().toString();
        String password = registerPassword.getText().toString();
        String type=registerType.getText().toString();

        if(name.isEmpty() && password.isEmpty() || type.isEmpty()){
            Toast.makeText(this,"Please Enter all details",Toast.LENGTH_SHORT).show();
        }else{
            result =true;
        }
        return result;
    }
}