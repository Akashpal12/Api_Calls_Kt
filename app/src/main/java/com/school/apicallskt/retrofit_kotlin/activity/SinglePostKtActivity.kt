package com.school.apicallskt.retrofit_kotlin.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.JsonObject
import com.school.apicallskt.R
import com.school.apicallskt.databinding.ActivityPostKtactivityBinding
import com.school.apicallskt.databinding.ActivitySinglePostJvBinding
import com.school.apicallskt.databinding.ActivitySinglePostKtBinding
import com.school.apicallskt.retrofit_kotlin.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SinglePostKtActivity : AppCompatActivity() {
   lateinit var binding: ActivitySinglePostKtBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivitySinglePostKtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id: Int? =intent.getIntExtra("id",1)

        RetrofitInstance.getClient().getSinglePosts(id).enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
               if (response.isSuccessful){
                   val jsonObject=response.body();
                   binding.userId.text= jsonObject?.get("userId")?.asInt.toString()
                   binding.Id.text=jsonObject?.get("id")?.asInt.toString()
                   binding.body.text=jsonObject?.get("body")?.asString
                   binding.tittle.text=jsonObject?.get("title")?.asString
               }
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {

            }
        })

    }
}