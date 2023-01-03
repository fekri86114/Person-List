package fekri.info.ux.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fekri.info.ux.data.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {
    abstract val personDao: PersonDao

    companion object {
        private var database: PersonDatabase? = null
        fun getDatabase(context: Context): PersonDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext, PersonDatabase::class.java, "person_database.db"
                ).allowMainThreadQueries().build()
            }
            return database!!
        }
    }

}