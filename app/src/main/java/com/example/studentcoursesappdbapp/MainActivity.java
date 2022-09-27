package com.example.studentcoursesappdbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
  private Button addCourseBtn, readCourseBtn;
  private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing all our variables;
        addCourseBtn =  findViewById(R.id.idBtnAddCourse);
        readCourseBtn = findViewById(R.id.idBtnReadCourse);
        courseNameEdt  = findViewById(R.id.idEditCourseName);
        courseTracksEdt  = findViewById(R.id.idEditCourseTracks);
        courseDurationEdt  = findViewById(R.id.idEditCourseDuration);
        courseDescriptionEdt  = findViewById(R.id.idEditCourseDescription);

        // creating a new dbhandler class
        // and passin our context to it
        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // below line is to get data from all edit text fields
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();


                // validating if the text fields are empty or not.
                if(courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all the data", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line  we are calling a method to add new courses to sqLite data and
                // pass all our alues to it
                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);

                // after adding te data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "course has been added", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseTracksEdt.setText("");
                courseDurationEdt.setText("");
                courseDescriptionEdt.setText("");



            }
        });


        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // opening a ne activity via an intent
                Intent i = new Intent(MainActivity.this, ViewCourse.class);
                startActivity(i);
            }
        });
    }
}