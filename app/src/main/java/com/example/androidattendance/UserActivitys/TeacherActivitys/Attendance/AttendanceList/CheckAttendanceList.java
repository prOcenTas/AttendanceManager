package com.example.androidattendance.UserActivitys.TeacherActivitys.Attendance.AttendanceList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.ADS.ShowAdsActivity;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.CheckCourse;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.HelperClasses.Model;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.HelperClasses.MyAdapter;
import com.example.androidattendance.UserActivitys.TeacherActivitys.Attendance.AttendanceList.HelperClass2.Model2;
import com.example.androidattendance.UserActivitys.TeacherActivitys.Attendance.AttendanceList.HelperClass2.MyAdapter2;
import com.example.androidattendance.UserActivitys.TeacherActivitys.Attendance.CheckAttendance;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckAttendanceList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference referenceAttendance;
    private TextView lecture,date;
    private ImageView goBack;
    private MyAdapter2 adapter;
    private ArrayList<Model2> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_attendance_list);

        //collecting sent data
        lecture=(TextView)findViewById(R.id.lectureNameReceived);
        Intent intent=getIntent();
        String lectureNamePassed=intent.getStringExtra("class");
        lecture.setText(lectureNamePassed);

        date=(TextView)findViewById(R.id.currentDateReceived);
        String datePassed=intent.getStringExtra("date");
        date.setText(datePassed);


        recyclerView=findViewById(R.id.recyclerViewAttendance);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        adapter=new MyAdapter2(this,list);

        recyclerView.setAdapter(adapter);

        referenceAttendance= FirebaseDatabase.getInstance().getReference("attendance").child(lecture.getText().toString()).child("Date = " + date.getText().toString());

        referenceAttendance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Model2 model=dataSnapshot.getValue(Model2.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        goBack=(ImageView)findViewById(R.id.toolbar_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckAttendanceList.this, CheckAttendance.class);
                intent.putExtra("Lecture",lectureNamePassed);
                startActivity(intent);
            }
        });
    }
}