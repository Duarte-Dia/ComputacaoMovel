package ipvc.estg.trabalhopratico.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.trabalhopratico.R
import ipvc.estg.trabalhopratico.api.User

class UserAdapter(val users:List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        return holder.bind(users[position])

    }

    override fun getItemCount() : Int {
        return users.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val email: TextView = itemView.findViewById(R.id.textView_Recycler_item)


        @SuppressLint("SetTextI18n")
        fun bind(users : User){
            email.text = "Latitude: " + users.email

        }
    }
}