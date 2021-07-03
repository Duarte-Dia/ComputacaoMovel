package ipvc.estg.trabalhopratico

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ipvc.estg.trabalhopratico.api.EndPoints
import ipvc.estg.trabalhopratico.api.OutputLogin
import ipvc.estg.trabalhopratico.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var editUsernameView: EditText
    private lateinit var editPasswordView: EditText
    private lateinit var checkboxRemeber: CheckBox

    private lateinit var shared_preferences: SharedPreferences
    private var loggedIn = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editUsernameView = findViewById(R.id.usernameEditText)
        editPasswordView = findViewById(R.id.passwordEditText)
        checkboxRemeber = findViewById(R.id.checkBox_LoggedIn)

        shared_preferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
// esta variavel serve para a aplicacao guardar a sessao do utilizador
        loggedIn = shared_preferences.getBoolean("loggedIn", false)

        if (loggedIn) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent);
            finish()
        }

        val buttonRegistar = findViewById<Button>(R.id.button_registar)
        buttonRegistar.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent);
            finish()
        }

    }

// funcao que efetua o login

    fun login(view: View) {
        // primeiro verifica se os edit estapo prenchidos
        if (TextUtils.isEmpty(editUsernameView.text) || TextUtils.isEmpty(editPasswordView.text)) {
            Toast.makeText(this@LoginActivity, R.string.login_Error, Toast.LENGTH_LONG).show()
        } // caso tenha texto ira passsar para o request ao servidor
        else {
            val request = ServiceBuilder.buildService(EndPoints::class.java)
            val username = editUsernameView.text.toString()
            val password = editPasswordView.text.toString()
            val checked_remember: Boolean = checkboxRemeber.isChecked
            val call = request.login(username = username, password = password)

            call.enqueue(object : Callback<OutputLogin> {
                override fun onResponse(call: Call<OutputLogin>, response: Response<OutputLogin>) {
                    if (response.isSuccessful) {

                        val c: OutputLogin = response.body()!!
                        // caso o status venha false significa que nao tem esse utilizador na base de dados

                        if (c.status == "false") {
                            Toast.makeText(this@LoginActivity, R.string.login_Error, Toast.LENGTH_LONG).show()
                        } else {
                            val shared_preferences_edit: SharedPreferences.Editor =
                                shared_preferences.edit()
                            shared_preferences_edit.putString("username", username)
                            shared_preferences_edit.putString("password", password)
                            shared_preferences_edit.putInt("id", c.id)
                            shared_preferences_edit.putBoolean("remeber", checked_remember)
                            shared_preferences_edit.apply()

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                    }
                }

                override fun onFailure(call: Call<OutputLogin>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }



}




