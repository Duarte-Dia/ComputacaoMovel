package ipvc.estg.trabalhopratico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class SelectedNoteActivity : AppCompatActivity() {
    private lateinit var TitleView: TextView
    private lateinit var DescriptionView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_note)

        //TENS DE CRIAR O ACESSO E A MUDANSA DO SELECT NOTE PARA DEPOIS EDITARES E APAGARES
    }
}