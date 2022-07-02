package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appmovitec.databinding.ActivityLoginBinding
import kotlin.collections.HashMap

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvcontrasenia.setOnClickListener(this)
        binding.btningresar.setOnClickListener(this)
        binding.tvcrearcuenta.setOnClickListener(this)



    }

    private fun validarUsuario() {
        val url = "http://192.168.10.19/movitec/validar_usuario.php"
        val queue= Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener { response ->
                if(!response.isEmpty()){
                    val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Usuario o Contraseña Incorrecto", Toast.LENGTH_LONG).show()
                }

            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()

            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()
                parametros.put("email",binding.edtcorreo.getText().toString())
                parametros.put("pass",binding.edtcontrasenia.getText().toString())
                return parametros
            }

        }
        queue.add(resultadoPost)
    }





    override fun onClick(v: View) {
       when(v.id){
           R.id.tvcontrasenia ->irContraseniaActivity()
           R.id.btningresar -> validarUsuario()
           R.id.tvcrearcuenta -> irRegistroActivity()

       }
    }

    private fun irRegistroActivity() {
        val regIntent= Intent(this, RegistroActivity::class.java)
        startActivity(regIntent)
    }

    private fun ingresar() {
        val homeintent = Intent(this,
            HomeActivity::class.java)
        startActivity(homeintent)
    }

    private fun irContraseniaActivity() {
        val passActivity = Intent(this,
            PasswordActivity::class.java)
        startActivity(passActivity)
    }
}