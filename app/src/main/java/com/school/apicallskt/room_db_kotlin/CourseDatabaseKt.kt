import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.school.apicallskt.room_db_kotlin.dio.CourseDaoKt
import com.school.apicallskt.room_db_kotlin.model.CourseRoomKtModel

@Database(entities = [CourseRoomKtModel::class], version = 1, exportSchema = false)
abstract class CourseDatabaseKt : RoomDatabase() {

    abstract fun courseDao(): CourseDaoKt

    companion object {
        private var instance: CourseDatabaseKt? = null

        @Synchronized
        fun getInstance(ctx: Context): CourseDatabaseKt {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    ctx.applicationContext,
                    CourseDatabaseKt::class.java,
                    "course_database_kotlin"
                ).fallbackToDestructiveMigration().build()

                instance = newInstance
                newInstance
            }
        }
    }
}
