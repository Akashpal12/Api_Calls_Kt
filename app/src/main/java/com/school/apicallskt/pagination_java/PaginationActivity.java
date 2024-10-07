package com.school.apicallskt.pagination_java;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.school.apicallskt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaginationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IssueAdapter issueAdapter;
    private boolean isLoading = false;
    private int currentPage = 1;
    private ProgressBar progressBar; // ProgressBar declaration
    JsonArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagination);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        jsonArray = new JsonArray();
        recyclerView = findViewById(R.id.recycler_view);
        issueAdapter = new IssueAdapter(jsonArray);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(issueAdapter);

        loadIssues(currentPage);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading && layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == jsonArray.size() - 1) {
                    currentPage++;
                    loadIssues(currentPage);
                }
            }
        });
    }

    private void loadIssues(int page) {
        isLoading = true;
        RetrofitClientPagination.getClient().getIssues(page).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonArray newJsonArray = response.body();
                    issueAdapter.addItems(newJsonArray); // Now this will work because issueAdapter is initialized
                } else {
                    Toast.makeText(PaginationActivity.this, "No more issues to load", Toast.LENGTH_SHORT).show();
                }
                isLoading = false;

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("API_CALL", "Error: " + t.getMessage());
                Toast.makeText(PaginationActivity.this, "Error loading issues", Toast.LENGTH_SHORT).show();
                isLoading = false;

            }
        });
    }
}
