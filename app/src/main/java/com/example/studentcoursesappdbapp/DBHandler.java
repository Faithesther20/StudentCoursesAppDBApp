package com.example.studentcoursesappdbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variable for our database.
    // Below is the variable for our database name.

    private  static final String DB_NAME = "coursedb";

    // Below is the int of the databasee version
    private static final int DB_VERSION = 1;

    // Below variable is four our table name.
    private  static final String TABLE_NAME = "mycourses";

    // Below variable is for our i column
    private  static final String ID_COL = "id";

    // Below variable is for our course name column
    private static final String NAME_COL = "name";

    // Below variable is for our course name column
    private static final String TRACKS_COL = "tracks";

    //Below variable id for our  course duration coulumn.
    private static final String DURATION_COL = "duration";

    // Below variable for our course description column.
    private static final String DESCRIPTION_COL = "description";


    // creating a constructor for our database handler.
    public DBHandler( Context context){

        super(context, DB_NAME, null, DB_VERSION);
    }

    // Below method is for creating a database by running a database by running a sqlite query
    @Override
    public  void onCreate(SQLiteDatabase db){
        // on below line we ar creating an sqlite query an we are setting our column names along
        // with their data types,
        String query = "CREATE TABLE " + TABLE_NAME +" ("
                + ID_COL + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_COL + "TEXT,"
                + DURATION_COL + "TEXT,"
                + DESCRIPTION_COL + "TEXT,"
                + TRACKS_COL + "TEXT)";

        //  Then we call the exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new coursee to our sqlite database.
    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseTracks){


        // Below we are creating a variable for our sqlite database and calling writable method
        // since we are writing data in our database
        SQLiteDatabase db = this.getWritableDatabase();

        // On the line below we are creating a  variable for content values.
        ContentValues values = new ContentValues();


        //on below line we are passing all values along with its key an value pair.
        values.put(NAME_COL,courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);

        // After  adding the values we would be passing the content values to our table
        db.insert(TABLE_NAME, null, values);

        //then close the database after adding values
        db.close();
   }

   // Creating the method for reading all the courses
    public ArrayList<CourseModal>  readCourses(){
        // Now we ould be initializing a database object for reading our database
        // using the getreadabledatabase method, we tell the system that we are trying to read form the database
        SQLiteDatabase db = this.getReadableDatabase();


        // Below we are creating a cursor withh query to rea data from database
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " +TABLE_NAME, null);

        // Here we are creating a new array list.
        ArrayList<CourseModal> coursesModalArrayList = new ArrayList();

        // moving our cursor to first position
        if(cursorCourses.moveToFirst()){
           do{
               // Below the line we ar adding the data from cursor to our array list
               coursesModalArrayList.add(new CourseModal(cursorCourses.getString(1),
               cursorCourses.getString(4),
                       cursorCourses.getString(2),
                       cursorCourses.getString(3)));
           } while(cursorCourses.moveToNext());
           // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list
        cursorCourses.close();
        return coursesModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // this method is called to check if the table exists already.
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

}
