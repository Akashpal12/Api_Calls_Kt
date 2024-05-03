package assign.technology.roomwithkotlinpractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [YourEntity::class], version = 1)
abstract class YourRoomDatabase : RoomDatabase() {
    abstract fun yourDao(): YourDao

    companion object {
        @Volatile
        private var INSTANCE: YourRoomDatabase? = null

        fun getDatabase(context: Context): YourRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YourRoomDatabase::class.java,
                    "your_database_name"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
