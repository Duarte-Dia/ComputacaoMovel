package ipvc.estg.trabalhopratico.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ipvc.estg.trabalhopratico.entities.Notes

@Dao

interface NotesDao {


    @Query("SELECT * FROM note_table ORDER BY title ASC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM note_table WHERE title ==:title")
    fun getTitleFromNotes(title:String):LiveData<Notes>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(title: Notes)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Query("DELETE FROM note_table WHERE title == :title")
    suspend fun deleteByTittle(title:String)

    @Update
    suspend fun updateNote(title:Notes)

    @Query("UPDATE note_table SET description=:description WHERE title ==:title")
    suspend fun updateDescriptionFromTitle(title: String,description: String)



}