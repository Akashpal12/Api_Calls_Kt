package com.school.apicallskt.room_db_java.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.school.apicallskt.R;
import com.school.apicallskt.databinding.ActivityRoomBinding;
import com.school.apicallskt.room_db_java.CourseDatabaseJv;
import com.school.apicallskt.room_db_java.RoomDbJavaAdapter;
import com.school.apicallskt.room_db_java.model.CourseJvRoomModel;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
    ActivityRoomBinding binding;
    CourseDatabaseJv courseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        courseDatabase = CourseDatabaseJv.getInstance(this);
        setData();

        binding.idBtnAddCourse.setOnClickListener(v -> {
            String courseName = binding.idEdtCourseName.getText().toString().trim();
            String courseTracks = binding.idEdtCourseTracks.getText().toString();
            String courseDuration = binding.idEdtCourseDuration.getText().toString();
            String courseDescription = binding.idEdtCourseDescription.getText().toString();

            if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                Toast.makeText(RoomActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }

            CourseJvRoomModel model = new CourseJvRoomModel();
            model.setCourseName(courseName);
            model.setCourseDutration(courseDuration);
            model.setCourseTracks(courseTracks);
            model.setCourseDescription(courseDescription);

            courseDatabase.courseDao().insert(model);

            Toast.makeText(RoomActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
            binding.idEdtCourseName.setText("");
            binding.idEdtCourseTracks.setText("");
            binding.idEdtCourseDuration.setText("");
            binding.idEdtCourseDescription.setText("");
            setData();
        });
    }
    private void setData() {
        List<CourseJvRoomModel> list = courseDatabase.courseDao().getAllCourses();
        Toast.makeText(this, "" + list.size(), Toast.LENGTH_SHORT).show();
        if (!list.isEmpty()) {
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(RoomActivity.this, RecyclerView.VERTICAL, false));
            binding.recyclerView.setAdapter(new RoomDbJavaAdapter(RoomActivity.this, list));
        }
    }
}