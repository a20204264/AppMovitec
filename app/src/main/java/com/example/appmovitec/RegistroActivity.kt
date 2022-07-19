package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appmovitec.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding:ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tviniciarsesion.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)
        binding.chkCaptcha.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tviniciarsesion -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.btnregistrar -> Insertar()
            R.id.chkCaptcha -> ValidarCaptcha()

        }
    }

    private fun ValidarCaptcha() {
            //en construcción
    }


    private fun Insertar() {
        val url = "http://192.168.10.19/movitec/insertar.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost= object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,LoginActivity::class.java))

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error $error", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>{
                val parametros = HashMap<String,String>()

                parametros.put("nombre",binding.edtNombre.text.toString())
                parametros.put("documento",binding.edtDocumento.text.toString())
                parametros.put("email",binding.edtCorreo.text.toString())
                parametros.put("telefono",binding.edtCelular.text.toString())
                parametros.put("pass",binding.edtContrasenia.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}