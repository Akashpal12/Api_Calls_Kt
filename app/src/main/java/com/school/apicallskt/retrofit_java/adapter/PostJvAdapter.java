package com.school.apicallskt.retrofit_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.school.apicallskt.R;
import com.school.apicallskt.retrofit_java.model.PostJvModel;
import com.school.apicallskt.retrofit_java.activity.SInglePostJvActivity;

import java.util.List;

public class PostJvAdapter extends RecyclerView.Adapter<PostJvAdapter.MyViewHolder> {
    Context context;
    List<PostJvModel> postList;

    public PostJvAdapter(Context context, List<PostJvModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PostJvModel model = postList.get(position);
        holder.userId.setText(String.valueOf(model.getUserId()));
        holder.Id.setText(String.valueOf(model.getId()));
        holder.body.setText(""+model.getBody());
        holder.tittle.setText(""+model.getTittle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SInglePostJvActivity.class);
                intent.putExtra("id", ""+model.getId());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userId,Id,body,tittle;
        MaterialCardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userId=itemView.findViewById(R.id.userId);
            Id=itemView.findViewById(R.id.Id);
            body=itemView.findViewById(R.id.body);
            tittle=itemView.findViewById(R.id.tittle);
            cardView=itemView.findViewById(R.id.card_item);
        }
    }
}
