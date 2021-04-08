package ipvc.estg.trabalhopratico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClickNotas(view: View) {
        var intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }

    fun onClickLogin(view: View){
        var intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }

    fun onClickMap(view: View){
        var intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }

    fun onClickDefinitions(view: View){
        var intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }
}