package com.school.apicallskt.retrofit_java.activity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.school.apicallskt.R;
import com.school.apicallskt.databinding.ActivityPostJvBinding;
import com.school.apicallskt.retrofit_java.RetrofitClientJv;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostJvActivity extends AppCompatActivity {
    ActivityPostJvBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostJvBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RetrofitClientJv.getClient().getPosts().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()){
                    JsonArray jsonArray = response.body();
                    Toast.makeText(PostJvActivity.this, ""+jsonArray, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });


    }

}