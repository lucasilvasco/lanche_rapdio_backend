package com.anhanguera.foodshopping

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.Button
import android.widget.EditText
import khttp.post
import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.content.Intent
import android.widget.Toast


class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)


        val buttonLogin = findViewById<Button>(R.id.login)
        val buttonCadastro = findViewById<Button>(R.id.cadastro)
        val userLoginForm = findViewById<EditText>(R.id.userLogin)
        val userPasswordForm = findViewById<EditText>(R.id.userPassword)


        buttonLogin.setOnClickListener {
            var userLogin = userLoginForm.text.toString()
            var userPassword = userPasswordForm.text.toString()
            logar(userLogin, userPassword)
        }

        buttonCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }


    }

    private fun logar(userLogin: String, userPassword: String) {
        AsyncTask.execute {
            try {
                val url = URL("http://10.32.22.164:8080/auth")
                var urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.addRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                urlConnect.requestMethod = "POST"
                urlConnect.doOutput = true

                val printStream = PrintStream(urlConnect.outputStream)
                printStream.println("name=$userLogin&password=$userPassword") //seta o que voce vai enviar

                urlConnect.connect() //envia para o servidor
                val response = Scanner(urlConnect.inputStream).next()
                if (response.toBoolean()) {
                    val intent = Intent(this, SelectShopping::class.java)
                    startActivity(intent)
                } else {
                    runOnUiThread {
                    Toast.makeText(applicationContext, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show()
                    }
                }

            } catch (e: Exception) {
                println(">>>>>>>>>>>>>>>. CATCH $e")
            }

        }
    }

}
