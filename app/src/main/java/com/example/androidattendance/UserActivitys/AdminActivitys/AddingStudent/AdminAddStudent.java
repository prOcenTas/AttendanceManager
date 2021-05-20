package com.example.androidattendance.UserActivitys.AdminActivitys.AddingStudent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidattendance.R;
import com.example.androidattendance.UserActivitys.AdminActivitys.AdminActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminAddStudent extends AppCompatActivity {

    private EditText studentNumber,studentName,lectureName;
    private Button addButton;
    private Button delButton;
    private ImageView backOut;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referenceStudent,referenceLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_student);


        studentNumber=(EditText)findViewById(R.id.studentId);
        studentName=(EditText)findViewById(R.id.studentName);
        lectureName=(EditText)findViewById(R.id.lecture);

        addButton=(Button) findViewById(R.id.addButton);
        delButton=(Button) findViewById(R.id.deleteButton);

        Intent intent=getIntent();
        final String name=intent.getStringExtra("name");
        final String phone=intent.getStringExtra("phoneNu");
        final String type=intent.getStringExtra("type");
        backOut=(ImageView)findViewById(R.id.toolbar_back);
        backOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminAddStudent.this, AdminActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("phoneNu",phone);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });

        referenceStudent=FirebaseDatabase.getInstance().getReference("student");
        referenceLecture=FirebaseDatabase.getInstance().getReference("lecture");

        //check if the fields are not empty and then proceed with inputting the data


    }
    public void addStudents(View view) {
        String studentId=studentNumber.getText().toString();
        String name=studentName.getText().toString();
        String lecture=lectureName.getText().toString();
        if(checkLectureName())
        {
            if(validate())
            {
                Student studentHelper=new Student(studentId,name);
                //adding to the Student database tab
                referenceStudent.child(studentId).setValue(studentHelper);

                //adding student to the specific lecture
                referenceLecture.child(lecture).child(studentId).setValue(studentHelper);
                Toast.makeText(AdminAddStudent.this,"Success!",Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void deleteStudents(View v)
    {
        String studentId=studentNumber.getText().toString();
        String name=studentName.getText().toString();
        String lecture=lectureName.getText().toString();
        if(checkLectureName()) {
            if (validate()) {
                Student studentHelper = new Student(studentId, name);
                referenceLecture.child(lecture).child(studentId).removeValue();
                studentName.setText("");
                studentName.setText("");
                lectureName.setText("");

                Toast.makeText(AdminAddStudent.this, "Student Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AdminAddStudent.this, "Please Fill Fields", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //making sure that the Lecture name is indeed correct
    private boolean checkLectureName()
    {
        String lecture=lectureName.getText().toString();

        if(lecture.matches("ADS") || lecture.matches("ESW") || lecture.matches("AND") || lecture.matches("DAI"))
        {
            return true;
        }
        else{
            Toast.makeText(this,"Please Enter the right Lecture",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //making sure the data fields are not empty
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