package ipvc.estg.trabalhopratico

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.trabalhopratico.adapter.UserAdapter
import ipvc.estg.trabalhopratico.api.EndPoints
import ipvc.estg.trabalhopratico.api.ServiceBuilder
import ipvc.estg.trabalhopratico.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.InetAddress


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getUsers()
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview_problema)
        Toast.makeText(this@LoginActivity,"Will it work ?? ", Toast.LENGTH_LONG).show()
        call.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>){
                if (response.isSuccessful){
                    Toast.makeText(this@LoginActivity,"WE DID IT ", Toast.LENGTH_LONG).show()
                    Log.i("TESTESITE","tivemos sucesso")
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@LoginActivity)
                        adapter = UserAdapter(response.body()!!)
                    }

                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable){
                Toast.makeText(this@LoginActivity,"${t.message}", Toast.LENGTH_LONG).show()
                Log.i("TESTESITE","fodeu")
            }
        })
    }

    }




