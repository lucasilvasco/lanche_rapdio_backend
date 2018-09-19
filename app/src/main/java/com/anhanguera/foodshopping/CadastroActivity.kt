package com.anhanguera.foodshopping

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val buttonCadastro = findViewById<Button>(R.id.submitCadastro)
        val buttonCancel = findViewById<Button>(R.id.cancelCadastro)
        val nameUser = findViewById<EditText>(R.id.edNameUser)
        val emailUser = findViewById<EditText>(R.id.edEmailUser)
        val passwordUser = findViewById<EditText>(R.id.edPasswordUser)
        val confirmePasswordUser = findViewById<EditText>(R.id.edCofirmePasswordUser)

        buttonCadastro.setOnClickListener {
            val name = nameUser.text.toString()
            val email = emailUser.text.toString()
            val password = passwordUser.text.toString()
            val confirmePassword = confirmePasswordUser.text.toString()

            if (name.isEmpty()){
                Toast.makeText(applicationContext, "Informe o nome!", Toast.LENGTH_LONG).show()
            }else if(email.isEmpty()){
                Toast.makeText(applicationContext, "Informe o email!", Toast.LENGTH_LONG).show()
            }else if(password.isEmpty()){
                Toast.makeText(applicationContext, "Informe a senha!", Toast.LENGTH_LONG).show()
            }else if(password!=confirmePassword){
                Toast.makeText(applicationContext, "Senhas não conferem!", Toast.LENGTH_LONG).show()
            }else{
            cadastrar(name, email, password)
            }

        }

        buttonCancel.setOnClickListener {
            this.finish()
        }
    }

    private fun cadastrar(name: String, email: String, password: String) {
        AsyncTask.execute {
            try {
                val url = URL("http://10.32.22.164:8080/usuarios/insere")
                var urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.addRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                urlConnect.requestMethod = "POST"
                urlConnect.doOutput = true

                val printStream = PrintStream(urlConnect.outputStream)
                printStream.println("name=$name&email=$email&password=$password") //seta o que voce vai enviar

                urlConnect.connect() //envia para o servidor

                val response = Scanner(urlConnect.inputStream).next()
                Log.v("response", response)
                if (response.toBoolean()) {
                    runOnUiThread {
                        Toast.makeText(applicationContext , "Usuário cadastrado!", Toast.LENGTH_LONG).show()
                    }
                    this.finish()
                } else {
                }

            } catch (e: Exception) {
                println(">>>>>>>>>>>>>>>. CATCH $e")
            }
        }
    }
}
