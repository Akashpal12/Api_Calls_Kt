package com.school.apicallskt.sqLite_java.db_helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.school.apicallskt.sqLite_java.model.CourseModelJv;

import java.util.ArrayList;
import java.util.List;

public class DbHelperJv extends SQLiteOpenHelper {

    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;

    public DbHelperJv(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CourseModelJv.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CourseModelJv.TABLE_NAME);
        onCreate(db);
    }

    public long insertCourse(CourseModelJv model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CourseModelJv.COURSE_NAME, model.getCourseName());
        values.put(CourseModelJv.COURSE_DURATION, model.getCourseDutration());
        values.put(CourseModelJv.COURSE_TRACKS, model.getCourseTracks());
        values.put(CourseModelJv.COURSE_DESCRIPTION, model.getCourseDescription());
        // insert row
        long id = db.insert(CourseModelJv.TABLE_NAME, null, values);
        // close db connection
        db.close();
        // return newly inserted row id
        return id;
    }

    @SuppressLint("Range")
    public List<CourseModelJv> getAlCourseList() {
        List<CourseModelJv> allData = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + CourseModelJv.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CourseModelJv singleData = new CourseModelJv();
                singleData.setCourseName(cursor.getString(cursor.getColumnIndex(singleData.COURSE_NAME)));
                singleData.setCourseTracks(cursor.getString(cursor.getColumnIndex(singleData.COURSE_TRACKS)));
                singleData.setCourseDutration(cursor.getString(cursor.getColumnIndex(singleData.COURSE_DURATION)));
                singleData.setCourseDescription(cursor.getString(cursor.getColumnIndex(singleData.COURSE_DESCRIPTION)));
                allData.add(singleData);
            } while (cursor.moveToNext());
        }
        db.close();
        return allData;
    }



}
