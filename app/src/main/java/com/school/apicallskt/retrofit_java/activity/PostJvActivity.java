package com.school.apicallskt.retrofit_java.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.school.apicallskt.R;
import com.school.apicallskt.databinding.ActivityPostJvActtivityBinding;
import com.school.apicallskt.retrofit_java.model.PostJvModel;
import com.school.apicallskt.retrofit_java.RetrofitClientJv;
import com.school.apicallskt.retrofit_java.adapter.PostJvAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostJvActivity extends AppCompatActivity {
    ActivityPostJvActtivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostJvActtivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RetrofitClientJv.getClient().getPostsList().enqueue(new Callback<List<PostJvModel>>() {
            @Override
            public void onResponse(Call<List<PostJvModel>> call, Response<List<PostJvModel>> response) {
                if (response.isSuccessful()){
                    List<PostJvModel> postJvModelList=response.body();
                    if (!postJvModelList.isEmpty()){
                        binding.recylerView.setLayoutManager(new LinearLayoutManager(PostJvActivity.this, RecyclerView.VERTICAL,false));
                        binding.recylerView.setAdapter(new PostJvAdapter(PostJvActivity.this,postJvModelList));
                    }
                }

            }

            @Override
            public void onFailure(Call<List<PostJvModel>> call, Throwable t) {

            }
        });

    }

}