package ipvc.estg.trabalhopratico

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var shared_preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shared_preferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
    }

    fun onClickNotas(view: View) {
        var intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }

    fun onClickLogin(view: View){
        val shared_preferences_edit: SharedPreferences.Editor =
            shared_preferences.edit()
        shared_preferences_edit.putBoolean("loggedIn", false)
        shared_preferences_edit.apply()
        var intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    fun onClickMap(view: View){

        var intent = Intent(this,MapsActivity::class.java)
        startActivity(intent)
    }

    fun onClickDefinitions(view: View){
        var intent = Intent(this,SettingsActivity::class.java)
        startActivity(intent)
    }
}