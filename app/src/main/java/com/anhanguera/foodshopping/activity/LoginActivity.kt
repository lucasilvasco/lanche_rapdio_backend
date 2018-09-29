package com.anhanguera.foodshopping.activity

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.Button
import android.widget.EditText
import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.URL
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.anhanguera.foodshopping.R
import com.anhanguera.foodshopping.utils.Settings
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.json.JSONObject
import android.view.LayoutInflater






class LoginActivity : AppCompatActivity(){
    val settings = Settings()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)


        val userLoginForm = findViewById<EditText>(R.id.userLogin)
        val userPasswordForm = findViewById<EditText>(R.id.userPassword)
        val buttonLogin = findViewById<Button>(R.id.login)
        val buttonCadastro = findViewById<Button>(R.id.cadastro)
        val forgetPassword = findViewById<Button>(R.id.forget)


        buttonLogin.setOnClickListener {
            var userLogin = userLoginForm.text.toString()
            var userPassword = userPasswordForm.text.toString()
            logar(userLogin, userPassword)
        }

        buttonCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        forgetPassword.setOnClickListener {
            val factory = layoutInflater
            val forgetLayout = factory.inflate(R.layout.forget_password, null)

            var alertDialog = AlertDialog.Builder(this)
                    .setTitle("Informe o E-Mail")
                    .setView(forgetLayout)
            val user = forgetLayout.findViewById<EditText>(R.id.forgetInput)

            alertDialog.setPositiveButton("OK") { _, _ ->
                val email = user.text.toString()
                validarEmailForget(email)
            }.show()
        }


    }

    private fun validarEmailForget(email: String) {
        AsyncTask.execute {
            try {
                val url = URL("${settings.SERVER_IP}:${settings.SERVER_PORT}/auth/forgetpassword")
                var urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.addRequestProperty("Content-Type", "application/json")
                urlConnect.requestMethod = "POST"
                urlConnect.doOutput = true

                val parent = JSONObject()
                parent.put("email", email)

                val printStream = PrintStream(urlConnect.outputStream)
                printStream.println(parent) //seta o que voce vai enviar

                urlConnect.connect() //envia para o servidor

                val response = urlConnect.responseCode
                if (response == 200) {
                    val intent = Intent(this, ResetarSenha::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                } else {
                    runOnUiThread {
                        Toast.makeText(this, "Email inexistente!", Toast.LENGTH_LONG).show()
                    }
                }

            } catch (e: Exception) {
                println(">>>>>>>>>>>>>>>. CATCH $e")
            }

        }
    }

    private fun logar(userLogin: String, userPassword: String) {
        AsyncTask.execute {
            try {
                val url = URL("${settings.SERVER_IP}:${settings.SERVER_PORT}/auth")
                var urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.addRequestProperty("Content-Type", "application/json")
                urlConnect.requestMethod = "POST"
                urlConnect.doOutput = true

                val parent = JSONObject()
                parent.put("email",userLogin)
                parent.put("password", userPassword)

                val printStream = PrintStream(urlConnect.outputStream)
                printStream.println(parent) //seta o que voce vai enviar
                urlConnect.connect() //envia para o servidor
                val response = urlConnect.responseCode
                if (response ==200) {
                    val intent = Intent(this, SelectShopping::class.java)
                    startActivity(intent)
                } else {
                    runOnUiThread {
                    Toast.makeText(applicationContext, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show()
                    }
                }

            } catch (e: Exception) {
                println(">>>>>>>>>>>>>>> $e")
            }

        }
    }

}
