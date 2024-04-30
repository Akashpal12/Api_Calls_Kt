package com.school.apicallskt.sqLite_kotlin.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.school.apicallskt.R
import com.school.apicallskt.databinding.ActivityPostKtactivityBinding
import com.school.apicallskt.databinding.ActivitySqLiteJvBinding
import com.school.apicallskt.sqLite_kotlin.dbhelper.DbHelperKt
import com.school.apicallskt.sqLite_kotlin.model.CourseKtModel

class SqLiteKtActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySqLiteJvBinding
    private lateinit var dbHelper: DbHelperKt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySqLiteJvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbHelper = DbHelperKt(this)
        setData()
        binding.idBtnAddCourse.setOnClickListener {
            val courseName = binding.idEdtCourseName.text.toString().trim()
            val courseTracks = binding.idEdtCourseTracks.text.toString().trim()
            val courseDuration = binding.idEdtCourseDuration.text.toString().trim()
            val courseDescription = binding.idEdtCourseDescription.text.toString().trim()

            if (courseName.isEmpty() || courseTracks.isEmpty() || courseDuration.isEmpty() || courseDescription.isEmpty()) {
                Toast.makeText(this, "Please enter all the data..", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val model = CourseKtModel().apply {
                this.courseName = courseName
                this.courseDuration = courseDuration
                this.courseTracks = courseTracks
                this.courseDescription = courseDescription
            }

            dbHelper.insertCourse(model)

            Toast.makeText(this, "Course has been added.", Toast.LENGTH_SHORT).show()

            binding.idEdtCourseName.text.clear()
            binding.idEdtCourseTracks.text.clear()
            binding.idEdtCourseDuration.text.clear()
            binding.idEdtCourseDescription.text.clear()
            setData()
        }

    }
    fun setData(){
        val list: List<CourseKtModel> = dbHelper.getAllCourses()
        Toast.makeText(this, "" + list.size, Toast.LENGTH_SHORT).show()

    }
}