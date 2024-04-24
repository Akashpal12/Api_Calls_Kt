package com.school.apicallskt.retrofit_java.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.school.apicallskt.R;
import com.school.apicallskt.databinding.ActivitySinglePostJvBinding;
import com.school.apicallskt.retrofit_java.RetrofitClientJv;
import com.school.apicallskt.retrofit_java.model.CommentsModel;
import com.school.apicallskt.retrofit_java.model.PostJvModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SInglePostJvActivity extends AppCompatActivity {
    ActivitySinglePostJvBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySinglePostJvBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (!getIntent().getStringExtra("id").equalsIgnoreCase("")){
            RetrofitClientJv.getClient().getSinglePost(Integer.parseInt(getIntent().getStringExtra("id"))).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()){
                        JsonObject jsonObject = response.body();
                        binding.userId.setText(String.valueOf(jsonObject.get("userId").getAsInt()));
                        binding.Id.setText(String.valueOf(jsonObject.get("id").getAsInt()));
                        binding.body.setText(jsonObject.get("body").getAsString());
                        binding.tittle.setText(jsonObject.get("title").getAsString());

                        binding.cardItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(SInglePostJvActivity.this,AllCommentsJvActivity.class);
                                intent.putExtra("id",getIntent().getStringExtra("id"));
                                startActivity(intent);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });

        }
    }
}