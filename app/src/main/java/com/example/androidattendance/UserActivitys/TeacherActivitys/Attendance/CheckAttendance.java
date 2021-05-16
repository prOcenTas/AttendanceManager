package com.example.androidattendance.UserActivitys.TeacherActivitys.Attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.TeacherActivitys.UserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CheckAttendance extends AppCompatActivity {
    private TextView className,attendanceCounter,date;
    private EditText studentId;
    private Button attendanceButton;
    private ImageView backOut;



    int request_code=1;
    private int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_attendance);


        //showing proper class name
        className=(TextView) findViewById(R.id.courseNameToPass);
        Intent intent=getIntent();
        String lectureNameSent=intent.getStringExtra("Lecture");
        className.setText(lectureNameSent);

        //showing todays date
        date=(TextView)findViewById(R.id.dateNow);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dateToShow = month+1 + "-" + day + "-" + year;
        date.setText(dateToShow);


        //backout to previous page
        backOut=findViewById(R.id.toolbar_back);
        backOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckAttendance.this, UserActivity.class);
                startActivity(intent);
            }
        });

        attendanceCounter=findViewById(R.id.attendanceCounter);
        studentId=findViewById(R.id.attendanceId);
        attendanceButton=findViewById(R.id.attendanceButton);


    }


    public void checkIn(View view) {
        //this studentId.getText().toString() been making me sit and stare at the screen for 2hours because of null
        //HEY IMMA EXPLAIN WHAT TOOK ME 2HOURS, I HAD A WRONG ID SET ON EDITTEXT STUDENTID.... THATS WHAT TOO MUCH WORK DOES TO YOU
        //I even rewrote the whole part of check attendance

        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("lecture");
        reference.child(className.getText().toString()).orderByChild("studentId").equalTo(studentId.getText().toString());

        final DatabaseReference referenceTo= FirebaseDatabase.getInstance().getReference("attendance")
                .child(className.getText().toString()).child("Date = " + date.getText().toString()).child(studentId.getText().toString());


        reference.child(className.getText().toString()).orderByChild("studentId").equalTo(studentId.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            ValueEventListener valueEventListener=new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    referenceTo.setValue(snapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isComplete()) {
                                                studentId.setText("");
                                            } else {

                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) { }
                            };
                            reference.child(className.getText().toString()).child(studentId.getText().toString()).addListenerForSingleValueEvent(valueEventListener);
                            counter=counter+1;
                            attendanceCounter.setText(String.valueOf(counter));
                            Toast.makeText(CheckAttendance.this,"Successful",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(CheckAttendance.this,"Unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
    }
}