package com.school.apicallskt.pagination_java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.school.apicallskt.R;

import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {
    private JsonArray jsonArray;

    public IssueAdapter(JsonArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issues_item_lists, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JsonObject jsonObject=jsonArray.get(position).getAsJsonObject();
        holder.postId.setText(jsonObject.get("url").getAsString());
        //holder.bodyTextView.setText(issue.getBody());
    }

    @Override
    public int getItemCount() {
        return jsonArray.size();
    }

    public void addItems(JsonArray NewjsonArray) {
        int startIndex = jsonArray.size();
        jsonArray.addAll(NewjsonArray);
        notifyItemRangeInserted(startIndex, NewjsonArray.size());
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView postId,id,name,email,body;
        public ViewHolder(View itemView) {
            super(itemView);
            postId=itemView.findViewById(R.id.postId);
            id=itemView.findViewById(R.id.Id);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            body=itemView.findViewById(R.id.body);
        }
    }
}
