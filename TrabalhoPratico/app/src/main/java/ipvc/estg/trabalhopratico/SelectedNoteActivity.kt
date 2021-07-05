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

    private lateinit var TitleViewLandscape: TextView
    private lateinit var DescriptionViewLandscape: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_note)
        TitleView = findViewById(R.id.editTextViewSelectedNoteTittle)
        DescriptionView = findViewById(R.id.editTextViewSelectedNoteDescription)

        TitleViewLandscape = findViewById(R.id.editTextViewSelectedNoteTittleLandscape)
        DescriptionViewLandscape = findViewById(R.id.editTextViewSelectedNoteDescriptionLandscape)

        var id = intent.getIntExtra("id", 0)
        var ogTitle = intent.getStringExtra("title")
        var ogDesc = intent.getStringExtra("description")
        //TENS DE CRIAR O ACESSO E A MUDANSA DO SELECT NOTE PARA DEPOIS EDITARES E APAGARES
// utilizar a actividade de criar notas para alterar a nota toda???

        TitleView.text = ogTitle
        DescriptionView.text = ogDesc

        val buttonEdit = findViewById<Button>(R.id.buttonEditNote)
        buttonEdit.setOnClickListener {
            val title = TitleView.text.toString()
            val d = DescriptionView.text.toString()
            val replyIntent = Intent()
            replyIntent.putExtra("delete", "0")

            replyIntent.putExtra("edit", "1")
            replyIntent.putExtra("id", id)
            replyIntent.putExtra("title", title)
            replyIntent.putExtra("description", d)
            setResult(Activity.RESULT_OK, replyIntent)


            finish()
            // Toast.makeText( this,"$ogTitle has been click. ", Toast.LENGTH_SHORT).show()
        }

        val buttonDelete = findViewById<Button>(R.id.buttonDeleteNote)
        buttonDelete.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra("title", ogTitle)
            replyIntent.putExtra("delete", "1")
            setResult(Activity.RESULT_OK, replyIntent)

            finish()
        }

        val buttonEditLandscape = findViewById<Button>(R.id.buttonEditNoteLandscape)
        buttonEditLandscape.setOnClickListener {
            val title = TitleView.text.toString()
            val d = DescriptionView.text.toString()
            val replyIntent = Intent()
            replyIntent.putExtra("delete", "0")

            replyIntent.putExtra("edit", "1")
            replyIntent.putExtra("id", id)
            replyIntent.putExtra("title", title)
            replyIntent.putExtra("description", d)
            setResult(Activity.RESULT_OK, replyIntent)


            finish()
            // Toast.makeText( this,"$ogTitle has been click. ", Toast.LENGTH_SHORT).show()
        }

        val buttonDeleteLandscape = findViewById<Button>(R.id.buttonDeleteNoteLandscape)
        buttonDeleteLandscape.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra("title", ogTitle)
            replyIntent.putExtra("delete", "1")
            setResult(Activity.RESULT_OK, replyIntent)

            finish()
        }
    }
}