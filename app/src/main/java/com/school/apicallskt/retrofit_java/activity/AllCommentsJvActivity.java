package com.school.apicallskt.retrofit_java.activity;

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
import com.school.apicallskt.databinding.ActivityAllCommentsJvBinding;
import com.school.apicallskt.retrofit_java.RetrofitClientJv;
import com.school.apicallskt.retrofit_java.adapter.CommentsJvAdapter;
import com.school.apicallskt.retrofit_java.adapter.PostJvAdapter;
import com.school.apicallskt.retrofit_java.model.CommentsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCommentsJvActivity extends AppCompatActivity {

    ActivityAllCommentsJvBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityAllCommentsJvBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (!getIntent().getStringExtra("id").equalsIgnoreCase("")){
            RetrofitClientJv.getClient().getComments(Integer.parseInt(getIntent().getStringExtra("id"))).enqueue(new Callback<List<CommentsModel>>() {
                @Override
                public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                    if (response.isSuccessful()){
                        List<CommentsModel>list=response.body();

                        if (!list.isEmpty()){
                            binding.recylerView.setLayoutManager(new LinearLayoutManager(AllCommentsJvActivity.this, RecyclerView.VERTICAL,false));
                            binding.recylerView.setAdapter(new CommentsJvAdapter(AllCommentsJvActivity.this,list));
                        }

                    }
                }

                @Override
                public void onFailure(Call<List<CommentsModel>> call, Throwable t) {

                }
            });

        }

    }
}