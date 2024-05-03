package com.school.apicallskt.room_db_kotlin.dio

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import assign.technology.roomwithkotlinpractice.YourEntity
import com.school.apicallskt.room_db_kotlin.model.CourseRoomKtModel

@Dao
interface CourseDaoKt {
    @Insert
    fun insert(course: CourseRoomKtModel)

    @Update
    fun update(course: CourseRoomKtModel)

    @Delete
    fun delete(course: CourseRoomKtModel)

    @Query("SELECT * FROM course_room_kt")
    fun getAllCourses(): List<CourseRoomKtModel>

    @Query("SELECT * FROM course_room_kt WHERE id = :id")
    fun getCourseById(id: Long): CourseRoomKtModel?

    @Query("UPDATE course_room_kt SET courseName = :courseName WHERE id = :id")
    fun updateCourseName(id: Long, courseName: String)

    @Query("DELETE FROM course_room_kt WHERE id = :id")
    fun deleteCourse(id: Long)
}