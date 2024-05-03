package assign.technology.roomwithkotlinpractice

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "your_table_name")
data class YourEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int
)

