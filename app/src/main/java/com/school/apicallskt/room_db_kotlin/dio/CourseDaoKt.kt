package com.school.apicallskt.room_db_kotlin.dio

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.school.apicallskt.room_db_kotlin.model.CourseRoomKtModel

@Dao
interface CourseDaoKt {
    @Insert
    fun insert(course: CourseRoomKtModel)

    @Query("SELECT * FROM CourseRoomKtModel")
    fun getAllCourses(): List<CourseRoomKtModel>

    @Query("SELECT * FROM CourseRoomKtModel WHERE id = :id")
    fun getCourseById(id: Long): CourseRoomKtModel?

    @Query("UPDATE CourseRoomKtModel SET courseName = :courseName WHERE id = :id")
    fun updateCourseName(id: Long, courseName: String)

    @Query("DELETE FROM CourseRoomKtModel WHERE id = :id")
    fun deleteCourse(id: Long)
}