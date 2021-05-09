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
    private EditText loginEmail,loginPassword;
    private Button loginButton;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginEmail=(EditText) findViewById(R.id.loginEmail);
        loginPassword=(EditText) findViewById(R.id.loginPassword);

//        firebaseAuth=FirebaseAuth.getInstance();
//
//        FirebaseUser user=firebaseAuth.getCurrentUser();
//        if(user != null)
//        {
//            startActivity(new Intent(this, UserActivity.class));
//        }

    }

    private boolean validateUsername()
    {
        String val=loginEmail.getText().toString();

        if(val.isEmpty())
        {
            loginEmail.setError("Field cannot be empty");
            return false;
        }
        else
        {
            loginEmail.setError(null);
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
        //validate(loginEmail.getText().toString(), loginPassword.getText().toString());
    }

    private void checkUser()
    {
        String inputEmail=loginEmail.getText().toString();
        String inputPassword=loginPassword.getText().toString();

        //check references in the user tab
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("user");

        //selecting by what we determine the search
        Query checkUser=reference.orderByChild("email").equalTo(inputEmail);

        //getting data to the snapshot from DB
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    loginEmail.setError(null);

                    String passwordFromDb=snapshot.child(inputEmail).child("password").getValue(String.class);
                    if(passwordFromDb.equals(inputPassword))
                    {
                        loginEmail.setError(null);

                        String emailFromDb=snapshot.child(inputEmail).child("email").getValue(String.class);
                        String phoneNuFromDb=snapshot.child(inputEmail).child("phoneNu").getValue(String.class);
                        String typeFromDb=snapshot.child(inputEmail).child("type").getValue(String.class);


                        //creating intent for AdminView
                        Intent adminIntent=new Intent(LoginActivity.this,AdminActivity.class);
                        adminIntent.putExtra("email",emailFromDb);
                        adminIntent.putExtra("phoneNu",phoneNuFromDb);
                        adminIntent.putExtra("type",typeFromDb);

                        //creating intent for UserView
                        Intent userIntent=new Intent(LoginActivity.this,UserActivity.class);
                        userIntent.putExtra("email",emailFromDb);
                        adminIntent.putExtra("phoneNu",phoneNuFromDb);
                        adminIntent.putExtra("type",typeFromDb);
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
                    loginEmail.setError("No such User found");
                    loginEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


//    private void validate(String username,String password)
//    {
//        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(LoginActivity.this,"LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
//                }else {
//                    Toast.makeText(LoginActivity.this,"LOGIN FAILED", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

}

