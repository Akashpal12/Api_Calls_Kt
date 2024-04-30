package com.school.apicallskt.sqLite_kotlin.model

data class CourseKtModel(
    var courseName: String? = null,
    var courseDuration: String? = null,
    var courseTracks: String? = null,
    var courseDescription: String? = null
)
{
    companion object {
        const val TABLE_NAME = "course_details"
        const val COLUMN_ID = "id"
        const val COURSE_NAME = "name"
        const val COURSE_DURATION = "duration"
        const val COURSE_TRACKS = "tracks"
        const val COURSE_DESCRIPTION = "description"

        const val CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COURSE_NAME TEXT," +
                    "$COURSE_DURATION TEXT," +
                    "$COURSE_TRACKS TEXT," +
                    "$COURSE_DESCRIPTION TEXT" +
                    ")"
    }
}