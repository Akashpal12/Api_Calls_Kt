package com.school.apicallskt.room_db_kotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_room_kt")
data class CourseRoomKtModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val courseName: String?=null,
    val courseDuration: String?=null,
    val courseTracks: String?=null,
    val courseDescription: String?=null
)