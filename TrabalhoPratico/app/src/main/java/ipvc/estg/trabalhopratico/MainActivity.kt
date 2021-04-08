package ipvc.estg.trabalhopratico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickNotas(){
        var intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }

    fun onClickLogin(){
        var intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }

    fun onClickMap(){
        var intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }

    fun onClickDefinitions(){
        var intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }
}