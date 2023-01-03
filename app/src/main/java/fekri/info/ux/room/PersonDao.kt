package fekri.info.ux.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fekri.info.ux.data.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(person: Person)

    @Insert
    fun insertAllPerson(data: List<Person>)

    @Delete
    fun deletePerson(person: Person)

    @Query("DELETE FROM person_table")
    fun deleteAllPerson()

    @Query("SELECT * FROM person_table")
    fun getAllPersons() : List<Person>

    @Query("SELECT * FROM person_table WHERE name LIKE '%' || :searching ||'%'")
    fun searchPersons(searching: String): List<Person>

}