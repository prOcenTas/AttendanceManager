package com.example.androidattendance.UserActivitys.AdminActivitys.addStudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidattendance.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdminAddStudent extends AppCompatActivity {

    private EditText studentNumber,studentName,lectureName;
    private Button addButton,delButton;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referenceStudent,referenceLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_student);
        Toolbar toolbar = findViewById(R.id.AppBar);
        setSupportActionBar(toolbar);

        studentNumber=(EditText)findViewById(R.id.studentId);
        studentName=(EditText)findViewById(R.id.studentName);
        lectureName=(EditText)findViewById(R.id.lecture);

        addButton=findViewById(R.id.addStudentButton);
        delButton=findViewById(R.id.deleteButton);

        referenceStudent=FirebaseDatabase.getInstance().getReference("student");
        referenceLecture=FirebaseDatabase.getInstance().getReference("lecture");

        //check if the fields are not empty and then proceed with inputting the data
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studentId=studentNumber.getText().toString();
                String name=studentName.getText().toString();
                String lecture=lectureName.getText().toString();
                if(validate())
                {
                    Student studentHelper=new Student(studentId,name);
                    //adding to the Student database tab
                    referenceStudent.child(studentId).setValue(studentHelper);

                    //adding student to the specific lecture
                    referenceLecture.child(lecture).child(studentId).setValue(studentHelper);
                }
                else{
                    Toast.makeText(AdminAddStudent.this,"Please Fill Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //deleting student from the course
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId=studentNumber.getText().toString();
                String name=studentName.getText().toString();
                String lecture=lectureName.getText().toString();
                if(validate())
                {
                    Student studentHelper=new Student(studentId,name);
                    referenceLecture.child(lecture).child(studentId).removeValue();
                    studentName.setText("");
                    studentName.setText("");
                    lectureName.setText("");

                    Toast.makeText(AdminAddStudent.this,"Student Deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AdminAddStudent.this,"Please Fill Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean validate()
    {
        boolean result = false;
        String studentId=studentNumber.getText().toString();
        String name=studentName.getText().toString();


        if(studentId.isEmpty() || name.isEmpty()){
            Toast.makeText(this,"Please Enter all details",Toast.LENGTH_SHORT).show();
        }else{
            result =true;
        }
        return result;
    }
}