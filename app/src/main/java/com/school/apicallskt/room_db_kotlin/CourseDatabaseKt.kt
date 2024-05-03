import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.school.apicallskt.room_db_kotlin.dio.CourseDaoKt
import com.school.apicallskt.room_db_kotlin.model.CourseRoomKtModel


@Database(entities = [CourseRoomKtModel::class], version = 1)
abstract class CourseDatabaseKt : RoomDatabase() {
    abstract fun CourseDao(): CourseDaoKt

    companion object {
        @Volatile
        private var INSTANCE: CourseDatabaseKt? = null

        fun getDatabase(context: Context): CourseDatabaseKt {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CourseDatabaseKt::class.java,
                    "course_room_kt"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
