package com.school.apicallskt.retrofit_kotlin.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.school.apicallskt.R
import com.school.apicallskt.databinding.ActivityPostKtactivityBinding
import com.school.apicallskt.retrofit_java.activity.PostJvActivity
import com.school.apicallskt.retrofit_kotlin.RetrofitInstance
import com.school.apicallskt.retrofit_kotlin.adapter.PostKtAdapter
import com.school.apicallskt.retrofit_kotlin.model.PostKtModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostKTActivity : AppCompatActivity() {
    lateinit var binding: ActivityPostKtactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostKtactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        RetrofitInstance.getClient().getPostsList().enqueue(object : Callback<List<PostKtModel>?> {
            override fun onResponse(call: Call<List<PostKtModel>?>, response: Response<List<PostKtModel>?>) {
                if (response.isSuccessful){
                    val list=response.body()
                    if (!list.isNullOrEmpty()){
                        binding.recylerView.layoutManager=LinearLayoutManager(this@PostKTActivity,RecyclerView.VERTICAL,false)
                        binding.recylerView.adapter=PostKtAdapter(this@PostKTActivity,list)
                    }
                }
            }

            override fun onFailure(call: Call<List<PostKtModel>?>, t: Throwable) {

            }
        })


    }
}