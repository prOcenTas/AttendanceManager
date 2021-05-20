package com.example.androidattendance.UserActivitys.TeacherActivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.ProfileUser;
import com.example.androidattendance.UserActivitys.TeacherActivitys.Attendance.CheckAttendance;

public class UserActivity extends AppCompatActivity {
    private ImageView ADSbutton,ESWbutton,ANDbutton,DAIbutton,userProfile;
    private TextView welcmUser;

    int request_code=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        ADSbutton=findViewById(R.id.ADSbutton);
        ANDbutton=findViewById(R.id.ANDbutton);
        DAIbutton=findViewById(R.id.DAIbutton);
        ESWbutton=findViewById(R.id.ESWbutton);


        welcmUser=findViewById(R.id.welcmUser);
        Intent userIntent=getIntent();
        final String namePassed=userIntent.getStringExtra("name");
        final String phonePassed=userIntent.getStringExtra("phoneNu");
        final String typePassed=userIntent.getStringExtra("type");
        welcmUser.setText(namePassed);

        userProfile=findViewById(R.id.profile);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent=new Intent(UserActivity.this, ProfileUser.class);
                profileIntent.putExtra("name",namePassed);
                profileIntent.putExtra("phoneNu",phonePassed);
                profileIntent.putExtra("type",typePassed);
                startActivity(profileIntent);
            }
        });
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
        intent.putExtra("Lecture",className);
        startActivityForResult(intent,request_code);
    }

    public void dataWarehousing(View view) {
        String className="DAI";

        Intent intent=new Intent(UserActivity.this, CheckAttendance.class);
        intent.putExtra("Lecture",className);
        startActivityForResult(intent,request_code);
    }

    public void algorithm(View view) {
        String className="ADS";

        Intent intent=new Intent(UserActivity.this, CheckAttendance.class);
        intent.putExtra("Lecture",className);
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