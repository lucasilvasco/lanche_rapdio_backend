package com.anhanguera.foodshopping.activity

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.anhanguera.foodshopping.R
import com.anhanguera.foodshopping.utils.MaskEditUtil
import com.anhanguera.foodshopping.utils.Settings
import org.json.JSONObject
import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.URL

class CadastroActivity : AppCompatActivity() {

    val settings = Settings()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val buttonCadastro = findViewById<Button>(R.id.submitCadastro)
        val buttonCancel = findViewById<Button>(R.id.cancelCadastro)
        val nameUser = findViewById<EditText>(R.id.edNameUser)
        val cpfUser = findViewById<EditText>(R.id.edCpfUser)
        val phoneUser = findViewById<EditText>(R.id.edPhoneUser)
        val emailUser = findViewById<EditText>(R.id.edEmailUser)
        val passwordUser = findViewById<EditText>(R.id.edPasswordUser)
        val confirmePasswordUser = findViewById<EditText>(R.id.edCofirmePasswordUser)

        cpfUser.addTextChangedListener(MaskEditUtil().mask(cpfUser,MaskEditUtil().FORMAT_CPF))
        phoneUser.addTextChangedListener(MaskEditUtil().mask(phoneUser,MaskEditUtil().FORMAT_FONE))

        buttonCadastro.setOnClickListener {
            val name = nameUser.text.toString()
            val email = emailUser.text.toString()
            val cpf = cpfUser.text.toString()
            val phone = phoneUser.text.toString()
            val password = passwordUser.text.toString()
            val confirmePassword = confirmePasswordUser.text.toString()

            if (name.isEmpty()){
                Toast.makeText(applicationContext, "Informe o nome!", Toast.LENGTH_LONG).show()
            }else if(cpf.isEmpty()) {
                Toast.makeText(applicationContext, "Informe o cpf!", Toast.LENGTH_LONG).show()
            }else if(phone.isEmpty()) {
                Toast.makeText(applicationContext, "Informe o telefone!", Toast.LENGTH_LONG).show()
            }else if(email.isEmpty()){
                Toast.makeText(applicationContext, "Informe o email!", Toast.LENGTH_LONG).show()
            }else if(password.isEmpty()){
                Toast.makeText(applicationContext, "Informe a senha!", Toast.LENGTH_LONG).show()
            }else if(password!=confirmePassword){
                Toast.makeText(applicationContext, "Senhas não conferem!", Toast.LENGTH_LONG).show()
            }else{
            cadastrar(name, cpf, phone, email, password)
            }

        }

        buttonCancel.setOnClickListener {
            this.finish()
        }
    }

    private fun cadastrar(name: String, cpf: String, phone: String, email: String, password: String) {
        AsyncTask.execute {
            try {
                val url = URL("${settings.SERVER_IP}:${settings.SERVER_PORT}/usuarios/insere")
                var urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.addRequestProperty("Content-Type", "application/json")
                urlConnect.requestMethod = "POST"
                urlConnect.doOutput = true

                val parent = JSONObject()
                parent.put("name",name)
                parent.put("cpf", cpf)
                parent.put("phone", phone)
                parent.put("email",email)
                parent.put("password", password)

                val printStream = PrintStream(urlConnect.outputStream)
                printStream.println(parent) //seta o que voce vai enviar

                urlConnect.connect() //envia para o servidor

                val response = urlConnect.responseCode
                if (response == 200) {
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
