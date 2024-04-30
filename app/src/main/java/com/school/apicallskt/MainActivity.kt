package com.school.apicallskt

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.school.apicallskt.databinding.ActivityMainBinding
import com.school.apicallskt.retrofit_java.activity.PostJvActivity
import com.school.apicallskt.retrofit_kotlin.activity.PostKTActivity
import com.school.apicallskt.sqLite_java.activity.SqLiteJvActivity
import com.school.apicallskt.sqLite_kotlin.activity.SqLiteKtActivity
import com.school.apicallskt.volley_kotlin.activity.VolleyPostKtActivity

class MainActivity : AppCompatActivity() {
      lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.kotlin.setOnClickListener {
            val intent=Intent(this, PostKTActivity::class.java)
            startActivity(intent)
        }
        binding.java.setOnClickListener {
            val intent=Intent(this,
                PostJvActivity::class.java)
            startActivity(intent)
        }
        binding.vKotlin.setOnClickListener {
            val intent=Intent(this,
                VolleyPostKtActivity::class.java)
            startActivity(intent)
        }
        binding.SqLiteJava.setOnClickListener {
            val intent=Intent(this,
                SqLiteJvActivity::class.java)
            startActivity(intent)
        }
        binding.SqLiteKt.setOnClickListener {
            val intent=Intent(this,
                SqLiteKtActivity::class.java)
            startActivity(intent)
        }
    }

}