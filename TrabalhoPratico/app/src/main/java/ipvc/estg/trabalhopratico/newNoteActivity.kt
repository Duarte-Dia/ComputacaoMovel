package ipvc.estg.trabalhopratico

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class newNoteActivity : AppCompatActivity() {

    private lateinit var editTitleView: EditText
    private lateinit var editDescriptionView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        editTitleView = findViewById(R.id.edit_title)
        editDescriptionView = findViewById(R.id.edit_description)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTitleView.text )&& TextUtils.isEmpty(editDescriptionView.text )) {
                // isto vai fazer com que o resultado seja =0 logo nao guardou nada
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = editTitleView.text.toString()
                val description = editDescriptionView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, title)
                replyIntent.putExtra("description", description)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "ipvc.estg.trabalhopratico.wordlistsql.REPLY"
    }
}