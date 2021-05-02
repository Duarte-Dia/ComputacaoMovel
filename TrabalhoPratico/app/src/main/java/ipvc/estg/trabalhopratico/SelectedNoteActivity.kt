package ipvc.estg.trabalhopratico

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SelectedNoteActivity : AppCompatActivity() {
    private lateinit var TitleView: TextView
    private lateinit var DescriptionView: TextView
   


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_note)
        TitleView = findViewById(R.id.editTextViewSelectedNoteTittle)
        DescriptionView= findViewById(R.id.editTextViewSelectedNoteDescription)

        var ogTitle= intent.getStringExtra("title")
        var ogDesc = intent.getStringExtra("description")
        //TENS DE CRIAR O ACESSO E A MUDANSA DO SELECT NOTE PARA DEPOIS EDITARES E APAGARES
// utilizar a actividade de criar notas para alterar a nota toda

        TitleView.text= ogTitle
        DescriptionView.text=ogDesc

        val buttonEdit = findViewById<Button>(R.id.buttonEditNote)
        buttonEdit.setOnClickListener {
            /*
            val intent = Intent(this, newNoteActivity::class.java)
            startActivityForResult(intent, editNoteActivityRequestCode)
            intent.putExtra("id",intent.getStringExtra("id"))
            intent.putExtra("title",intent.getStringExtra("title"))
            intent.putExtra("description",intent.getStringExtra("description"))
            */
            Toast.makeText( this,"$ogTitle has been click. ", Toast.LENGTH_SHORT).show()
        }

        val buttonDelete = findViewById<Button>(R.id.buttonDeleteNote)
        buttonDelete.setOnClickListener {
            val replyIntent = Intent()
                val title = TitleView.text.toString()
                replyIntent.putExtra("title", title)
            replyIntent.putExtra("delete","1")
                setResult(Activity.RESULT_OK, replyIntent)

            finish()
        }
    }
}