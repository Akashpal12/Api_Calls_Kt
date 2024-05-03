package com.school.apicallskt.room_db_kotlin

import CourseDatabaseKt
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import assign.technology.roomwithkotlinpractice.YourRoomDatabase
import com.school.apicallskt.R

class TestRoomActivity : AppCompatActivity() {
    private lateinit var courseDatabase: CourseDatabaseKt
    private lateinit var yourDatabase: YourRoomDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test_room)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        courseDatabase = CourseDatabaseKt.getDatabase(applicationContext)
        //yourDatabase = YourRoomDatabase.getDatabase(application)
    }
}