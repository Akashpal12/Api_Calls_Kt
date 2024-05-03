package com.school.apicallskt.room_db_kotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table_kotlin")
data class CourseRoomKtModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val courseName: String,
    val courseDuration: String,
    val courseTracks: String,
    val courseDescription: String
)