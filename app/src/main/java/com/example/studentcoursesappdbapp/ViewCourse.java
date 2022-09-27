package com.example.studentcoursesappdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewCourse extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<CourseModal> courseModalsArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        // initializing our all variables
        courseModalsArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewCourse.this);

        // getting our course array
        //list from db handler class.

        courseModalsArrayList = dbHandler.readCourses();

        // setting layout manager for our recycler  view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewCourse.this, RecyclerView.VERTICAL,false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }
}