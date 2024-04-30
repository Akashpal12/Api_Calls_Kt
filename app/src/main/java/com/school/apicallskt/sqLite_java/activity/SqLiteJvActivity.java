package com.school.apicallskt.sqLite_java.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.school.apicallskt.R;
import com.school.apicallskt.databinding.ActivitySqLiteJv2Binding;
import com.school.apicallskt.retrofit_java.activity.PostJvActivity;
import com.school.apicallskt.retrofit_java.adapter.PostJvAdapter;
import com.school.apicallskt.sqLite_java.db_helper.DbHelperJv;
import com.school.apicallskt.sqLite_java.model.CourseModelJv;

import java.util.ArrayList;
import java.util.List;

public class SqLiteJvActivity extends AppCompatActivity {
    ActivitySqLiteJv2Binding binding;
    DbHelperJv dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySqLiteJv2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbh = new DbHelperJv(SqLiteJvActivity.this);
        setData();
        binding.idBtnAddCourse.setOnClickListener(v -> {
            String courseName = binding.idEdtCourseName.getText().toString().trim();
            String courseTracks = binding.idEdtCourseTracks.getText().toString();
            String courseDuration = binding.idEdtCourseDuration.getText().toString();
            String courseDescription = binding.idEdtCourseDescription.getText().toString();

            if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                Toast.makeText(SqLiteJvActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }

            CourseModelJv modelJv=new CourseModelJv();
            modelJv.setCourseName(courseName);
            modelJv.setCourseDutration(courseName);
            modelJv.setCourseTracks(courseTracks);
            modelJv.setCourseDescription(courseDescription);
            dbh.insertCourse(modelJv);

            Toast.makeText(SqLiteJvActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
            binding.idEdtCourseName.setText("");
            binding.idEdtCourseTracks.setText("");
            binding.idEdtCourseDuration.setText("");
            binding.idEdtCourseDescription.setText("");
            setData();
        });
    }

    private void setData(){
        List<CourseModelJv>list=new ArrayList<>();
        list=dbh.getAlCourseList();
        Toast.makeText(this, ""+list.size(), Toast.LENGTH_SHORT).show();
        if (!list.isEmpty()){
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(SqLiteJvActivity.this, RecyclerView.VERTICAL,false));
            binding.recyclerView.setAdapter(new SqLiteJavaAdapter(SqLiteJvActivity.this,list));
        }
    }
}