package com.school.apicallskt.room_db_kotlin

import CourseDatabaseKt
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.school.apicallskt.R
import com.school.apicallskt.databinding.ActivityRoomDbKtBinding
import com.school.apicallskt.room_db_kotlin.dio.CourseDaoKt
import com.school.apicallskt.room_db_kotlin.model.CourseRoomKtModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomDbKtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomDbKtBinding
    private lateinit var courseDatabase: CourseDatabaseKt
    private lateinit var courseDao:CourseDaoKt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRoomDbKtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        courseDatabase = CourseDatabaseKt.getDatabase(application)
       // courseDao=courseDatabase.CourseDao()

        /*binding.idBtnAddCourse.setOnClickListener {
            val courseName = binding.idEdtCourseName.text.toString().trim()
            val courseTracks = binding.idEdtCourseTracks.text.toString().trim()
            val courseDuration = binding.idEdtCourseDuration.text.toString().trim()
            val courseDescription = binding.idEdtCourseDescription.text.toString().trim()

            if (courseName.isEmpty() || courseTracks.isEmpty() || courseDuration.isEmpty() || courseDescription.isEmpty()) {
                Toast.makeText(this, "Please enter all the data..", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val model = CourseRoomKtModel(
                courseName = courseName,
                courseDuration = courseDuration,
                courseTracks = courseTracks,
                courseDescription = courseDescription
            )

            // Insert data in a background thread using coroutines
            GlobalScope.launch(Dispatchers.IO) {
                courseDatabase.courseDao().insert(model)
                // Once data is inserted, update UI on the main thread
                runOnUiThread {
                    Toast.makeText(
                        this@RoomDbKtActivity,
                        "Course has been added.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Clear input fields
                    binding.idEdtCourseName.text.clear()
                    binding.idEdtCourseTracks.text.clear()
                    binding.idEdtCourseDuration.text.clear()
                    binding.idEdtCourseDescription.text.clear()
                }
            }
        }*/
    }
}
