package ipvc.estg.trabalhopratico

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EditMapNoteActivity : AppCompatActivity() {
    private lateinit var TitleView: TextView
    private lateinit var DescriptionView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_map_note)
        TitleView = findViewById(R.id.editTextViewSelectedNoteTittle)
        DescriptionView = findViewById(R.id.editTextViewSelectedNoteDescription)

        var id = intent.getIntExtra("ID", 0)
        var ogTitle = intent.getStringExtra("TITLE")
        var ogDesc = intent.getStringExtra("DESC")
        var lat:Float = intent.getFloatExtra("LAT",0f)
        var lon:Float = intent.getFloatExtra("LON",0f)
        var id_user:Int = intent.getIntExtra("ID_USER",0)


        TitleView.text = ogTitle
        DescriptionView.text = ogDesc

        val buttonEdit = findViewById<Button>(R.id.buttonEditNote)
        buttonEdit.setOnClickListener {
            val title = TitleView.text.toString()
            val d = DescriptionView.text.toString()
            val replyIntent = Intent()
            replyIntent.putExtra("delete", "0")

            replyIntent.putExtra("edit", "1")
            replyIntent.putExtra("ID", id)
            replyIntent.putExtra("TITLE", title)
            replyIntent.putExtra("DESC", d)
            replyIntent.putExtra("Lat", lat)
            replyIntent.putExtra("LONG", lon)
            replyIntent.putExtra("ID_USER", id_user)
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
    }
}