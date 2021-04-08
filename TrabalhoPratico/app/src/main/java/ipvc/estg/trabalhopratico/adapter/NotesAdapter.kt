package ipvc.estg.trabalhopratico.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.trabalhopratico.R
import ipvc.estg.trabalhopratico.entities.Notes

class NotesAdapter internal constructor(context: Context): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){



    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Notes>()

    class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val notesItemView : TextView = itemView.findViewById(R.id.textView_Recycler_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemView= inflater.inflate(R.layout.recyclerview_item,parent,false)
        return NotesViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val current = notes[position]
        holder.notesItemView.text=current.id.toString()+" - "+current.title+" - "+ current.description
    }
    internal fun setNotes(notes: List<Notes>){
        this.notes=notes
        notifyDataSetChanged()
    }

    override fun getItemCount()= notes.size

}