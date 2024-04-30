package com.school.apicallskt.sqLite_java.model;

public class CourseModelJv {
    private String CourseName;
    private String CourseDutration;
    private String CourseTracks;
    private String CourseDescription;

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseDutration() {
        return CourseDutration;
    }

    public void setCourseDutration(String courseDutration) {
        CourseDutration = courseDutration;
    }

    public String getCourseTracks() {
        return CourseTracks;
    }

    public void setCourseTracks(String courseTracks) {
        CourseTracks = courseTracks;
    }

    public String getCourseDescription() {
        return CourseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }


    // Creating table Variables
    public static final String TABLE_NAME = "course_details";
    public static final String COLUMN_ID = "id";
    public static final String COURSE_NAME = "name";
    public static final String COURSE_DURATION = "duration";
    public static final String COURSE_TRACKS = "tracks";
    public static final String COURSE_DESCRIPTION = "description";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COURSE_NAME + " TEXT,"
                    + COURSE_DURATION + " TEXT,"
                    + COURSE_TRACKS + " TEXT,"
                    + COURSE_DESCRIPTION + " TEXT"
                    + ")";
}
