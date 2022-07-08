package com.example.appmovitec.Clientes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityEditarClienteBinding

class EditarClienteActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityEditarClienteBinding
    var idGlobal: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditarClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btneditarCli.setOnClickListener(this)
        binding.btnCancelarEditCliente.setOnClickListener(this)

        idGlobal =intent.getStringExtra("id").toString()
        val queue= Volley.newRequestQueue(this)

        val url = "http://192.168.10.19/movitec/registrocli.php?id=$idGlobal"
        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                binding.edtNombre?.setText(response.getString("nombre"))
                binding.edtDocumento?.setText(response.getString("documento"))
                binding.edtCorreo?.setText(response.getString("email"))
                binding.edtCelular?.setText(response.getString("telefono"))
                binding.edtdireccion?.setText(response.getString("direccion"))

            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)




    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btneditarCli -> GuardarCliente()
            R.id.btnCancelarEditCliente ->CancelarEditarcliente()
        }

    }

    private fun CancelarEditarcliente() {
        val intent = Intent(this, ClienteActivity::class.java)
        startActivity(intent)
    }

    private fun GuardarCliente() {
        val url = "http://192.168.10.19/movitec/editarcli.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost=object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"El Cliente se edito Correctamente",Toast.LENGTH_LONG).show();
                var intent = Intent(this, ClienteActivity::class.java)
                startActivity(intent)

            },Response.ErrorListener { error ->
                Toast.makeText(this,"Error al editar al cliente $error",Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()
                parametros.put("id",idGlobal!!)
                parametros.put("nombre",binding.edtNombre.text.toString())
                parametros.put("documento",binding.edtDocumento.text.toString())
                parametros.put("email",binding.edtCorreo.text.toString())
                parametros.put("telefono",binding.edtCelular.text.toString())
                parametros.put("direccion",binding.edtdireccion.text.toString())
                return parametros}
        }
        queue.add(resultadoPost)
    }

}