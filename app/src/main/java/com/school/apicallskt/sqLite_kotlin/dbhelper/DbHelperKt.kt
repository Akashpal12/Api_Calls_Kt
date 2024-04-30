package com.school.apicallskt.sqLite_kotlin.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.school.apicallskt.sqLite_kotlin.model.CourseKtModel


class DbHelperKt (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(CourseKtModel.CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS ${CourseKtModel.TABLE_NAME}")
        }
        onCreate(db)

    }

    fun insertCourse(model: CourseKtModel): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(CourseKtModel.COURSE_NAME, model.courseName)
            put(CourseKtModel.COURSE_DURATION, model.courseDuration)
            put(CourseKtModel.COURSE_TRACKS, model.courseTracks)
            put(CourseKtModel.COURSE_DESCRIPTION, model.courseDescription)
        }
        val id = db.insert(CourseKtModel.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getAllCourses(): List<CourseKtModel> {
        val allCourses = mutableListOf<CourseKtModel>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM ${CourseKtModel.TABLE_NAME}"
        val cursor = db.rawQuery(selectQuery, null)

        cursor.use {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndexOrThrow(CourseKtModel.COURSE_NAME))
                val duration = it.getString(it.getColumnIndexOrThrow(CourseKtModel.COURSE_DURATION))
                val tracks = it.getString(it.getColumnIndexOrThrow(CourseKtModel.COURSE_TRACKS))
                val description = it.getString(it.getColumnIndexOrThrow(CourseKtModel.COURSE_DESCRIPTION))
                
                val course = CourseKtModel(name, duration, tracks, description)
                allCourses.add(course)
            }
        }
        return allCourses
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "my_database.db"
    }
}