package ipvc.estg.trabalhopratico.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ipvc.estg.trabalhopratico.dao.NotesDao
import ipvc.estg.trabalhopratico.entities.Notes

class NotesRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    fun getCountryFromCity(title:String): LiveData<Notes> {
        return notesDao.getTitleFromNotes(title)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(city: Notes) {
        notesDao.insert(city)
    }

    suspend fun deleteAll() {
        notesDao.deleteAll()
    }

    suspend fun deleteByTittle(title: String) {
        notesDao.deleteByTittle(title)
    }

    suspend fun updateNote(title: Notes) {
        notesDao.updateNote(title)
    }

    suspend fun updateDescriptionFromTitle(title: String, description: String) {
        notesDao.updateDescriptionFromTitle(title,description)
    }

}
