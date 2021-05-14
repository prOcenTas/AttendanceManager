package com.example.androidattendance.UserActivitys.TeacherActivitys.Attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidattendance.R;
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
    int request_code=1;
    private int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_attendance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //showing proper class name
        className=findViewById(R.id.className);
        Intent intent=getIntent();
        String lectureNameSent=intent.getStringExtra("Lecture");
        className.setText(lectureNameSent);

        //showing todays date
        date=(TextView)findViewById(R.id.date);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dateNow = month+1 + "-" + day + "-" + year;
        date.setText(dateNow);

        attendanceCounter=findViewById(R.id.attendanceCounter);
        studentId=findViewById(R.id.studentId);
        attendanceButton=findViewById(R.id.attendanceButton);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("lecture");
        reference.child(className.getText().toString()).orderByChild("studentId").equalTo(studentId.getText().toString());

        DatabaseReference referenceTo= FirebaseDatabase.getInstance().getReference("attendance")
                .child(className.getText().toString()).child("Date = " + dateNow)
                .child(studentId.getText().toString());

        attendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(className.getText().toString()).orderByChild("studentId").equalTo(studentId.getText().toString())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            ValueEventListener valueEventListener=new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    referenceTo.child(studentId.getText().toString()).setValue(snapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        });
    }
}