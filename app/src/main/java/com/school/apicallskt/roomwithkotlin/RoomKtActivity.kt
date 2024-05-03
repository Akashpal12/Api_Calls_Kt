package com.school.apicallskt.roomwithkotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import assign.technology.roomwithkotlinpractice.YourDao
import assign.technology.roomwithkotlinpractice.YourEntity
import assign.technology.roomwithkotlinpractice.YourRoomDatabase
import com.school.apicallskt.R
import com.school.apicallskt.databinding.ActivityRoomKtBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomKtActivity : AppCompatActivity() {
    private lateinit var yourDao: YourDao
    private lateinit var yourDatabase: YourRoomDatabase
    private lateinit var binding: ActivityRoomKtBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRoomKtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        yourDatabase = YourRoomDatabase.getDatabase(application)
        yourDao = yourDatabase.yourDao()

        // Example: Insert Operation
        val newEntity = YourEntity(id = 0, name = "John", age = 30)
        insertEntity(newEntity)
    }

    // Example: Update Operation
//        val entityToUpdate = YourEntity(id = 1, name = "Jane", age = 25)
//        updateEntity(entityToUpdate)
//
//        // Example: Delete Operation
//        val entityToDelete = YourEntity(id = 1, name = "Jane", age = 25)
//        deleteEntity(entityToDelete)
    private fun insertEntity(entity: YourEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            yourDao.insert(entity)
        }
    }

    //    private fun updateEntity(entity: YourEntity) {
//        CoroutineScope(Dispatchers.IO).launch {
//            yourDao.update(entity)
//        }
//    }
//
//    private fun deleteEntity(entity: YourEntity) {
//        CoroutineScope(Dispatchers.IO).launch {
//            yourDao.delete(entity)
//        }
//    }

}