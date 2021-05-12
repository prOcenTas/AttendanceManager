package com.example.androidattendance.UserActivitys.AdminActivitys.AddingStudent;

public class Student {
    private String fullName;
    private String studentId;

    public Student(String studentId, String fullName)
    {
        this.studentId=studentId;
        this.fullName=fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
