package ipvc.estg.trabalhopratico

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

    private lateinit var shared_preferences: SharedPreferences
    private var loggedIn = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        shared_preferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)

        loggedIn = shared_preferences.getBoolean("loggedIn",false)

        if(loggedIn){
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent);
            finish()
        }

    }

}




