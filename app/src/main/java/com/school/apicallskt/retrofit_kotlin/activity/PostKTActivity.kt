package com.school.apicallskt.retrofit_kotlin.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.JsonArray
import com.school.apicallskt.R
import com.school.apicallskt.databinding.ActivityPostKtactivityBinding
import com.school.apicallskt.retrofit_kotlin.RetrofitInstance
import java.util.concurrent.Callable
import javax.security.auth.callback.Callback


class PostKTActivity : AppCompatActivity() {
    lateinit var binding: ActivityPostKtactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityPostKtactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
}