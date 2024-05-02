package com.school.apicallskt.room_db_java.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CourseJvRoomModel {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String CourseName;
    private String CourseDutration;
    private String CourseTracks;
    private String CourseDescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}
