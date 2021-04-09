package ipvc.estg.trabalhopratico

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.trabalhopratico.adapter.NotesAdapter
import ipvc.estg.trabalhopratico.entities.Notes
import ipvc.estg.trabalhopratico.viewModel.NoteViewModel

class NoteListActivity : AppCompatActivity(),NotesAdapter.OnItemClickListener {
    private lateinit var noteViewModel: NoteViewModel
    private val newWordActivityRequestCode =1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotesAdapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this) { notes->notes?.let{adapter.setNotes(it)}
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, newNoteActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
// aqui ele deteta se recebeu o intent do criar nota , caso crie ele adiciona as notas
        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(newNoteActivity.EXTRA_REPLY)?.let {
                val note = Notes(title =it, description = it)
                noteViewModel.insert(note)
            }
        } else {
// caso nao detete qualquer texto devolve um toast a dizer que estava vazia
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
}
    }

    override fun onItemClick(id:Int?,title: String,description:String) {
        Toast.makeText( this,"$title has been click. ", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SelectedNoteActivity::class.java)
        startActivityForResult(intent, newWordActivityRequestCode)
        intent.putExtra("id",id)
        intent.putExtra("title",title)
        intent.putExtra("description",description)
    }
}