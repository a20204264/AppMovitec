package com.example.appmovitec.Clientes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityNuevoClienteBinding

class NuevoClienteActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityNuevoClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNuevoClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncrearCli.setOnClickListener(this)
        binding.btnCancelarNewCliente.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btncrearCli -> CrearCliente()
            R.id.btnCancelarNewCliente -> CancelarNuevoCliente()
        }

    }

    private fun CancelarNuevoCliente() {
        val intent= Intent(this, ClienteActivity::class.java)
        startActivity(intent)
    }

    private fun CrearCliente() {
        val url = "http://192.168.10.19/movitec/insertarcli.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost= object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Cliente registrado exitosamente", Toast.LENGTH_LONG).show()
                val intent= Intent(this, ClienteActivity::class.java)
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
                parametros.put("direccion",binding.edtdireccion?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}