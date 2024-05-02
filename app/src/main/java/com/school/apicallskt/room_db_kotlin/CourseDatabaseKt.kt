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
        @Volatile
        private var instance: CourseDatabaseKt? = null

        fun getInstance(context: Context): CourseDatabaseKt {
            return instance ?: synchronized(this) {
                instance ?: try {
                    buildDatabase(context).also { instance = it }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    throw ex
                }
            }
        }
        private fun buildDatabase(context: Context): CourseDatabaseKt {
            return Room.databaseBuilder(
                context.applicationContext,
                CourseDatabaseKt::class.java,
                "course_database"
            ).fallbackToDestructiveMigration().allowMainThreadQueries()
                .build()
        }
    }
}
