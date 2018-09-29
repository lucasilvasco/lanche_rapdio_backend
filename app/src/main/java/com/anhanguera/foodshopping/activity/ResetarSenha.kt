package com.anhanguera.foodshopping.activity

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.anhanguera.foodshopping.R
import com.anhanguera.foodshopping.utils.Settings
import org.json.JSONObject
import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.URL

class ResetarSenha : AppCompatActivity() {
    val settings = Settings()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetar_senha)

        val newPassword = findViewById<EditText>(R.id.edPasswordUser)
        val confirmNewPassword = findViewById<EditText>(R.id.edCofirmePasswordUser)
        val submitRedefinir = findViewById<Button>(R.id.submitRedefinir)
        val email = intent.extras["email"]

        submitRedefinir.setOnClickListener {

            val password = newPassword.text.toString()
            val confirmPassword = confirmNewPassword.text.toString()
            if(password!=confirmPassword){
                Toast.makeText(this, "As senhas informadas não conferem!",Toast.LENGTH_LONG).show()
            }else{

            redefinirSenha(email, password)
            }
        }


    }

    private fun redefinirSenha(email: Any?, password: String) {
        AsyncTask.execute {
            try {
                val url = URL("${settings.SERVER_IP}:${settings.SERVER_PORT}/auth/newpassword")
                var urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.addRequestProperty("Content-Type", "application/json")
                urlConnect.requestMethod = "PUT"
                urlConnect.doOutput = true

                val parent = JSONObject()

                parent.put("email", email)
                parent.put("password", password)

                val printStream = PrintStream(urlConnect.outputStream)
                printStream.println(parent) //seta o que voce vai enviar

                urlConnect.connect() //envia para o servidor

                val response = urlConnect.responseCode
                if (response == 200) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Senha Atualizada!", Toast.LENGTH_LONG).show()
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
