package com.school.apicallskt.volley_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.school.apicallskt.R
import com.school.apicallskt.retrofit_kotlin.model.PostKtModel
import org.json.JSONArray
import org.json.JSONException


class VolleyPostKtActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_volley_post_kt)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gson=Gson()
        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/posts/1"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val postModel = gson.fromJson(response, PostKtModel::class.java)
                Toast.makeText(this,""+postModel.userId,Toast.LENGTH_SHORT).show()

            },
            {

            })

        queue.add(stringRequest)


    }
}