package ipvc.estg.trabalhopratico.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.estg.trabalhopratico.db.NotesDB
import ipvc.estg.trabalhopratico.db.NotesRepository
import ipvc.estg.trabalhopratico.entities.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (application: Application): AndroidViewModel(application) {

    private val repository: NotesRepository
    val allNotes: LiveData<List<Notes>>

    init {
        val notesDao = NotesDB.getDatabase(application, viewModelScope).NotesDao()
        repository = NotesRepository(notesDao)
        allNotes= repository.allNotes
    }

    fun insert(notes:Notes) = viewModelScope.launch {
        repository.insert(notes)
    }
    fun deleteAll()= viewModelScope.launch(Dispatchers.IO){repository.deleteAll()}

    fun deleteByTittle(city: String )= viewModelScope.launch(Dispatchers.IO){
        repository.deleteByTittle(city)
    }

    fun updateNote(notes:Notes)= viewModelScope.launch(Dispatchers.IO){
        repository.updateNote(notes)
    }

    fun updateNoteFromId(id:Int, title: String,description: String)=viewModelScope.launch(Dispatchers.IO){
        repository.updateNoteFromId(id,title,description)}

    fun updateDescriptionFromTitle(title: String,description: String)=viewModelScope.launch(Dispatchers.IO){
        repository.updateDescriptionFromTitle(title,description)}


}