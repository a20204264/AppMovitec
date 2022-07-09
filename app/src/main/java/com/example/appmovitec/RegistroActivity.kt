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

    private lateinit var bindingReg:ActivityRegistroBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReg = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(bindingReg.root)

        bindingReg.tviniciarsesion.setOnClickListener(this)
        bindingReg.btnregistrar.setOnClickListener(this)
        bindingReg.chkCaptcha.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tviniciarsesion -> irLoginActivity()
            R.id.btnregistrar -> Insertar()
            R.id.chkCaptcha -> ValidarCaptcha()

        }
    }

    private fun ValidarCaptcha() {

    }

    private fun irLoginActivity() {
        val logIntent = Intent(this, LoginActivity::class.java)
        startActivity(logIntent)
    }


    private fun Insertar() {
        val url = "http://192.168.10.19/movitec/insertar.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost= object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                val intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error $error", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()

                parametros.put("nombre",bindingReg.edtNombre?.text.toString())
                parametros.put("documento",bindingReg.edtDocumento?.text.toString())
                parametros.put("email",bindingReg.edtCorreo?.text.toString())
                parametros.put("telefono",bindingReg.edtCelular?.text.toString())
                parametros.put("pass",bindingReg.edtContrasenia?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}