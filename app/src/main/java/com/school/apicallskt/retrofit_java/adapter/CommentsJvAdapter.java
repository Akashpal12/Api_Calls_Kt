package com.school.apicallskt.retrofit_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.school.apicallskt.R;
import com.school.apicallskt.retrofit_java.model.CommentsModel;
import com.school.apicallskt.retrofit_java.model.PostJvModel;

import java.util.List;

public class CommentsJvAdapter extends RecyclerView.Adapter<CommentsJvAdapter.MyViewHolder> {
    Context context;
    List<CommentsModel> list;

    public CommentsJvAdapter(Context context, List<CommentsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_item_lists,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CommentsModel model = list.get(position);
        holder.postId.setText(String.valueOf(model.getPostId()));
        holder.id.setText(String.valueOf(model.getId()));
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.body.setText(model.getBody());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView postId,id,name,email,body;
        MaterialCardView card_item;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            postId=itemView.findViewById(R.id.postId);
            id=itemView.findViewById(R.id.Id);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            body=itemView.findViewById(R.id.body);
            card_item=itemView.findViewById(R.id.card_item);
        }
    }
}
