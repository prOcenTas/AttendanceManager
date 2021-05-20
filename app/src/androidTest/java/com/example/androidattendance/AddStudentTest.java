package com.example.androidattendance;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.androidattendance.UserActivitys.AdminActivitys.AddingStudent.AdminAddStudent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4.class)
public class AddStudentTest {

    @Rule
    public ActivityTestRule<AdminAddStudent> adminAddStudentActivityTestRule = new ActivityTestRule<AdminAddStudent>(AdminAddStudent.class);

    @Test
    public void clikcAddStudent() throws Exception{
       // onView().perform().check();
    }


}
