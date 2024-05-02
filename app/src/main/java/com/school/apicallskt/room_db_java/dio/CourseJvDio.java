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

    @Query("SELECT * FROM CourseJvRoomModel")
    List<CourseJvRoomModel> getAllCourses();

    @Query("SELECT * FROM CourseJvRoomModel WHERE id = :id")
    CourseJvRoomModel getCourseById(long id);

    @Query("UPDATE CourseJvRoomModel SET courseName = :newName WHERE id = :id")
    void updateCourseName(long id, String newName);

    @Query("DELETE FROM CourseJvRoomModel WHERE id = :id")
    void deleteCourse(long id);
}
