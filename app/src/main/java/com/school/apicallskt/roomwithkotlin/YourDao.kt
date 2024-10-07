package assign.technology.roomwithkotlinpractice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface YourDao {
    @Insert
     fun insert(entity: YourEntity)

    @Update
     fun update(entity: YourEntity)

    @Delete
     fun delete(entity: YourEntity)



}
