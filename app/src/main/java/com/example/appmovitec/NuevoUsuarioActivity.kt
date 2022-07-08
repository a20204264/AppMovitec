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
import com.example.appmovitec.databinding.ActivityNuevoUsuarioBinding

class NuevoUsuarioActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityNuevoUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNuevoUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncrear.setOnClickListener(this)
        binding.btnCancelarNewUsus.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btncrear->Insertar()
            R.id.btnCancelarNewUsus-> cancelarNuevoUsuario()

        }
    }

    private fun cancelarNuevoUsuario() {
        val intent= Intent(this, UsuariosActivity::class.java)
        startActivity(intent)
    }

    private fun Insertar() {
        val url = "http://192.168.10.19/movitec/insertar.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost= object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                val intent= Intent(this,UsuariosActivity::class.java)
                startActivity(intent)

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error $error", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()

                parametros.put("nombre",binding.edtNombre?.text.toString())
                parametros.put("documento",binding.edtDocumento?.text.toString())
                parametros.put("email",binding.edtCorreo?.text.toString())
                parametros.put("telefono",binding.edtCelular?.text.toString())
                parametros.put("pass",binding.edtContrasenia?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}