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

    @Query("SELECT * FROM course_table_java")
    List<CourseJvRoomModel> getAllCourses();

    @Query("SELECT * FROM course_table_java WHERE id = :id")
    CourseJvRoomModel getCourseById(long id);

    @Query("UPDATE course_table_java SET courseName = :newName WHERE id = :id")
    void updateCourseName(long id, String newName);

    @Query("DELETE FROM course_table_java WHERE id = :id")
    void deleteCourse(long id);


}
