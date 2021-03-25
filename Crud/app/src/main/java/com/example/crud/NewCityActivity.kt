package com.example.crud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewCityActivity : AppCompatActivity() {
    private lateinit var editCityView: EditText
    private lateinit var editCapitalView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_city)
        editCityView = findViewById(R.id.edit_city)
        editCapitalView = findViewById(R.id.edit_city)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editCityView.text )&&TextUtils.isEmpty(editCapitalView.text )) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val city = editCityView.text.toString()
                val capital = editCapitalView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, city)
                replyIntent.putExtra(EXTRA_REPLY, capital)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.crud.wordlistsql.REPLY"
    }
}