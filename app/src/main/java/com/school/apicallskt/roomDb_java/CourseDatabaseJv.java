package com.school.apicallskt.roomDb_java;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.school.apicallskt.roomDb_java.dio.CourseJvDio;
import com.school.apicallskt.roomDb_java.model.CourseJvRoomModel;

@Database(entities = {CourseJvRoomModel.class}, version = 1, exportSchema = false)
public abstract class CourseDatabaseJv extends RoomDatabase {

    private static CourseDatabaseJv instance;

    public abstract CourseJvDio courseDao();

    public static synchronized CourseDatabaseJv getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CourseDatabaseJv.class, "course_database_room")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
