package ipvc.estg.trabalhopratico

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import ipvc.estg.trabalhopratico.api.EndPoints
import ipvc.estg.trabalhopratico.api.OutputLogin
import ipvc.estg.trabalhopratico.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var editUsernameView: EditText
    private lateinit var editPasswordView: EditText
    private lateinit var editEmailView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        editUsernameView = findViewById(R.id.registerUsernameEditText)
        editPasswordView = findViewById(R.id.registerPasswordEditText)
        editEmailView = findViewById(R.id.registerEmailEditText)


    }

// funcao que efetua o login

    fun register(view: View) {
        // primeiro verifica se os edit estapo prenchidos
        if (TextUtils.isEmpty(editUsernameView.text) || TextUtils.isEmpty(editPasswordView.text)|| TextUtils.isEmpty(editEmailView.text)) {
            Toast.makeText(this@RegisterActivity, R.string.login_Error, Toast.LENGTH_LONG).show()
        } // caso tenha texto ira passsar para o request ao servidor
        else {
            val request = ServiceBuilder.buildService(EndPoints::class.java)
            val username = editUsernameView.text.toString()
            val password = editPasswordView.text.toString()
            val email = editEmailView.text.toString()
            val call = request.registar(username = username, password = password,email=email )
            call.enqueue(object : Callback<OutputLogin> {
                override fun onResponse(call: Call<OutputLogin>, response: Response<OutputLogin>) {
                    if (response.isSuccessful) {

                        val c: OutputLogin = response.body()!!
                        Toast.makeText(this@RegisterActivity, "enviando ao server", Toast.LENGTH_LONG).show()
                        if (c.status == "false") {
                            Toast.makeText(this@RegisterActivity, c.MSG, Toast.LENGTH_LONG).show()
                        } else {

                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            Toast.makeText(this@RegisterActivity, R.string.login_Error, Toast.LENGTH_LONG).show()
                            startActivity(intent)
                            finish()
                        }

                    }
                }

                override fun onFailure(call: Call<OutputLogin>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}




