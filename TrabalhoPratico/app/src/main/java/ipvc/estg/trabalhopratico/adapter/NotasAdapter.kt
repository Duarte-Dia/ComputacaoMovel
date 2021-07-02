package ipvc.estg.trabalhopratico.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.trabalhopratico.R
import ipvc.estg.trabalhopratico.api.Notas

class NotasAdapter (val notas: List<Notas>): RecyclerView.Adapter<NotasAdapter.ProblemaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ProblemaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProblemaViewHolder, position: Int) {
        return holder.bind(notas[position])

    }

    override fun getItemCount() : Int {
        return notas.size
    }

    class ProblemaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val titleItemView: TextView = itemView.findViewById(R.id.textView_Recycler_item)


        fun bind(notas : Notas){

            titleItemView.text = "Tipo: " + notas.title
        }
    }
}