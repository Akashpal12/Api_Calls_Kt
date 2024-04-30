package com.school.apicallskt.sqLite_java.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.school.apicallskt.R;
import com.school.apicallskt.retrofit_java.activity.SInglePostJvActivity;
import com.school.apicallskt.retrofit_java.model.PostJvModel;
import com.school.apicallskt.sqLite_java.model.CourseModelJv;

import java.util.List;

public class SqLiteJavaAdapter extends RecyclerView.Adapter<SqLiteJavaAdapter.MyViewHolder> {
    Context context;
    List<CourseModelJv> courseList;

    public SqLiteJavaAdapter(Context context, List<CourseModelJv> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CourseModelJv model = courseList.get(position);
        holder.idEdtCourseName.setText(model.getCourseName());
        holder.idEdtCourseDuration.setText(model.getCourseDutration());
        holder.idEdtCourseTracks.setText(model.getCourseTracks());
        holder.idEdtCourseDescription.setText(model.getCourseDescription());

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       EditText idEdtCourseName,idEdtCourseDuration,idEdtCourseTracks,idEdtCourseDescription;
       Button idBtnDelete,idBtnUpdate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idEdtCourseName=itemView.findViewById(R.id.idEdtCourseName);
            idEdtCourseDuration=itemView.findViewById(R.id.idEdtCourseDuration);
            idEdtCourseTracks=itemView.findViewById(R.id.idEdtCourseTracks);
            idEdtCourseDescription=itemView.findViewById(R.id.idEdtCourseDescription);
            idBtnDelete=itemView.findViewById(R.id.idBtnDelete);
            idBtnUpdate=itemView.findViewById(R.id.idBtnUpdate);
        }
    }
}
