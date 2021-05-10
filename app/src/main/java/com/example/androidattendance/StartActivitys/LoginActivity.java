package com.example.androidattendance.StartActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.AdminActivitys.AdminActivity;
import com.example.androidattendance.UserActivitys.TeacherActivitys.UserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    private EditText loginUsername,loginPassword;
    private Button loginButton;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.AppBar);
        setSupportActionBar(toolbar);

        loginUsername=(EditText) findViewById(R.id.loginEmail);
        loginPassword=(EditText) findViewById(R.id.loginPassword);


    }

    private boolean validateUsername()
    {
        String val=loginUsername.getText().toString();

        if(val.isEmpty())
        {
            loginUsername.setError("Field cannot be empty");
            return false;
        }
        else
        {
            loginUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword()
    {
        String val=loginPassword.getText().toString();

        if(val.isEmpty())
        {
            loginPassword.setError("Field cannot be empty");
            return false;
        }
        else
        {
            loginPassword.setError(null);
            return true;
        }
    }

    public void login(View v)
    {
        if(!validateUsername() | !validatePassword())
        {
            return;
        }
        else
        {
            checkUser();
        }

    }

    private void checkUser()
    {
        String inputEmail=loginUsername.getText().toString();
        String inputPassword=loginPassword.getText().toString();

        //check references in the user tab
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("user");

        //selecting by what we determine the search
        Query checkUser=reference.orderByChild("userName").equalTo(inputEmail);

        //getting data to the snapshot from DB
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    loginUsername.setError(null);

                    String passwordFromDb=snapshot.child(inputEmail).child("password").getValue(String.class);
                    if(passwordFromDb.equals(inputPassword))
                    {
                        loginUsername.setError(null);

                        String emailFromDb=snapshot.child(inputEmail).child("userName").getValue(String.class);
                        String phoneNuFromDb=snapshot.child(inputEmail).child("phoneNu").getValue(String.class);
                        String typeFromDb=snapshot.child(inputEmail).child("type").getValue(String.class);
                        String nameFromDb=snapshot.child(inputEmail).child("name").getValue(String.class);


                        //creating intent for AdminView
                        Intent adminIntent=new Intent(LoginActivity.this,AdminActivity.class);
                        adminIntent.putExtra("userName",emailFromDb);
                        adminIntent.putExtra("phoneNu",phoneNuFromDb);
                        adminIntent.putExtra("type",typeFromDb);
                        adminIntent.putExtra("name",nameFromDb);

                        //creating intent for UserView
                        Intent userIntent=new Intent(LoginActivity.this,UserActivity.class);
                        userIntent.putExtra("userName",emailFromDb);
                        userIntent.putExtra("phoneNu",phoneNuFromDb);
                        userIntent.putExtra("type",typeFromDb);
                        userIntent.putExtra("name",nameFromDb);
                        if(typeFromDb.equals("admin"))
                        {
                            startActivity(adminIntent);
                        }
                        else
                        {
                            startActivity(userIntent);
                        }
                    }
                    else
                    {
                        loginPassword.setError("Wrong Password");
                        loginPassword.requestFocus();
                    }
                }
                else
                {
                    loginUsername.setError("No such User found");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}

