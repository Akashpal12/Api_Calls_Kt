package com.school.apicallskt.room_db_kotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseRoomKtModel(
    var courseName: String? = null,
    var courseDuration: String? = null,
    var courseTracks: String? = null,
    var courseDescription: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
