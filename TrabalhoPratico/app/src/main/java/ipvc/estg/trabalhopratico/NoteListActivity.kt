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
    private val newNoteActivityRequestCode =1
    private val editNoteActivityRequestCode=2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotesAdapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this) { notes->notes?.let{adapter.setNotes(it)} }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, newNoteActivity::class.java)
            // aqui comeca a atividade newNote com o codigo =1
            startActivityForResult(intent, newNoteActivityRequestCode)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
// aqui ele deteta se recebeu o intent do criar nota , caso crie ele adiciona as notas
        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {

            data?.getStringExtra(newNoteActivity.EXTRA_REPLY)?.let {
             var d= data.getStringExtra("description")
                // NOTA POR ALGUM MOTIVO AO CRIAR AS NOTAS ELE ESTA A TROCAR A DESCRICAO PELO TITUTLO
                if(d!=null){
                val note = Notes(title =it, description =d )
                noteViewModel.insert(note)}
            }
        } else if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_CANCELED) {
// caso nao detete qualquer texto devolve um toast a dizer que estava vazia
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
            // aqui deteta se o reply vem da atividade de editar/ apagar
        } else if(requestCode == editNoteActivityRequestCode && resultCode == Activity.RESULT_OK){
            // primeiro verifica se e para a pagar a nota
                if(data?.getStringExtra("delete") =="1"){
                    var title= data?.getStringExtra("title")
                    if (title != null) {
                        noteViewModel.deleteByTittle(title)
                    }
                    Toast.makeText( this,"Note $title has been deleted ", Toast.LENGTH_SHORT).show()
                    // caso contrario vai verificar o que e para editar
                } else{
                    // edit =0 e porque o titulo e igual mas a descricao foi alterada
                    if(data?.getStringExtra("edit") =="0"){
                        var t =  data?.getStringExtra("title")
                        var d =  data?.getStringExtra("description")
                        if(t!=null && d!=null){
                            noteViewModel.updateDescriptionFromTitle(t,d)
                        }
                        // edit =1 e porque o titulo foi alterado
                    } else if (data?.getStringExtra("edit") =="1"){
                        var idN =  data?.getIntExtra("id",0)

                        var t =  data?.getStringExtra("title")
                        var d =  data?.getStringExtra("description")
                        if(t!=null && d!=null&& idN!=null&& idN!=0){
                            noteViewModel.updateNoteFromId(idN,t,d)
                            Toast.makeText( this,"$idN has been edited. ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }

    }

    override fun onItemClick(id:Int?,title: String,description:String) {
        // simples toast para saber se esta a registar o on click corretamente
        Toast.makeText( this,"$title has been click. ", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SelectedNoteActivity::class.java)
        intent.putExtra("id",id)
        intent.putExtra("title",title)
        intent.putExtra("description",description)

        // aqui comeca a atividade selectedNote com o codigo = 2
        startActivityForResult(intent, editNoteActivityRequestCode)

    }
}