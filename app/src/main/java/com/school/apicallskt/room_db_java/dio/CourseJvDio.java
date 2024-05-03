package com.school.apicallskt.room_db_java.dio;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.school.apicallskt.room_db_java.model.CourseJvRoomModel;

import java.util.List;

@Dao
public interface CourseJvDio {
    @Insert
    void insert(CourseJvRoomModel course);

    @Query("SELECT * FROM course_database_room")
    List<CourseJvRoomModel> getAllCourses();

    @Query("SELECT * FROM course_database_room WHERE id = :id")
    CourseJvRoomModel getCourseById(long id);

    @Query("UPDATE course_database_room SET courseName = :newName WHERE id = :id")
    void updateCourseName(long id, String newName);

    @Query("DELETE FROM course_database_room WHERE id = :id")
    void deleteCourse(long id);


}
