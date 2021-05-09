package com.example.androidattendance.UserActivitys.AdminActivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidattendance.StartActivitys.LoginActivity;
import com.example.androidattendance.R;
import com.example.androidattendance.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {

    private EditText registerEmail,registerPassword,registerType,registerPhoneNu;
    private Button registerButton;

    private FirebaseAuth fireBaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    User userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        registerEmail=(EditText) findViewById(R.id.registerEmail);
        registerPassword=(EditText) findViewById(R.id.registerPassword);
        registerButton=(Button) findViewById(R.id.registerButton);
        registerType=(EditText) findViewById(R.id.registerType);
        registerPhoneNu=(EditText) findViewById(R.id.phoneNu);

        fireBaseAuth=FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("user");

//        User userInfo=new User();

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(validate()){
                    String email = registerEmail.getText().toString();
                    String password = registerPassword.getText().toString();
                    String type=registerType.getText().toString();
                    String phoneNu=registerPhoneNu.getText().toString();

                    User usersInfo=new User(email,password,type,phoneNu);
                    reference.child(email).setValue(usersInfo);


                    fireBaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(RegisterUser.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterUser.this, LoginActivity.class));
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
        String phoneNu=registerPhoneNu.getText().toString();

        if(name.isEmpty() && password.isEmpty() && phoneNu.isEmpty() || type.isEmpty()){
            Toast.makeText(this,"Please Enter all details",Toast.LENGTH_SHORT).show();
        }else{
            result =true;
        }
        return result;
    }

//    private void addDatatoFirebase(String email, String password, String type) {
//        // below 3 lines of code is used to set
//        // data in our object class.
//        userInfo.setEmail(email);
//        userInfo.setPassword(password);
//        userInfo.setType(type);
//        // we are use add value event listener method
//        // which is called with database reference.
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // inside the method of on Data change we are setting
//                // our object class to our database reference.
//                // data base reference will sends data to firebase.
//                reference.setValue(userInfo);
//
//                // after adding this data we are showing toast message.
//                Toast.makeText(RegisterUser.this, "data added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // if the data is not added or it is cancelled then
//                // we are displaying a failure toast message.
//                Toast.makeText(RegisterUser.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}