package com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.AND;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.ADS.ShowAdsActivity;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.CheckCourse;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.HelperClasses.Model;
import com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.HelperClasses.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowAndActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference referenceLecture= FirebaseDatabase.getInstance().getReference("lecture").child("AND");
    private MyAdapter adapter;
    private ArrayList<Model> list;
    private ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_and);

        goBack=(ImageView)findViewById(R.id.toolbar_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowAndActivity.this, CheckCourse.class);
                startActivity(intent);
            }
        });

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        adapter=new MyAdapter(this,list);

        recyclerView.setAdapter(adapter);

        referenceLecture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Model model=dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}