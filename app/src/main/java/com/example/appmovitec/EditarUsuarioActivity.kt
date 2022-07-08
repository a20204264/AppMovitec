package com.example.appmovitec

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
import com.example.appmovitec.databinding.ActivityEditarUsuarioBinding

class EditarUsuarioActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityEditarUsuarioBinding
    var idGlobal: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnguardar.setOnClickListener(this)
        binding.btnCancelar.setOnClickListener(this)

        idGlobal =intent.getStringExtra("id").toString()
        val queue=Volley.newRequestQueue(this)

        val url = "http://192.168.10.19/movitec/registro.php?id=$idGlobal"
        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                binding.edtNombre?.setText(response.getString("nombre"))
                binding.edtDocumento?.setText(response.getString("documento"))
                binding.edtCorreo?.setText(response.getString("email"))
                binding.edtCelular?.setText(response.getString("telefono"))
                binding.edtContrasenia?.setText(response.getString("pass"))

            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.btnguardar -> GuardarUsuario()
            R.id.btnCancelar -> CancelarEditarUsuario()


        }
    }

    private fun CancelarEditarUsuario() {
        val intent = Intent(this,UsuariosActivity::class.java)
        startActivity(intent)
    }

    private fun GuardarUsuario() {
        val url = "http://192.168.10.19/movitec/editar.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost=object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"El usuario se edito Correctamente",Toast.LENGTH_LONG).show();
                var intent = Intent(this,UsuariosActivity::class.java)
                startActivity(intent)

            },Response.ErrorListener { error ->
                Toast.makeText(this,"Error al editar el usuario $error",Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()
                parametros.put("id",idGlobal!!)
                parametros.put("nombre",binding.edtNombre.text.toString())
                parametros.put("documento",binding.edtDocumento.text.toString())
                parametros.put("email",binding.edtCorreo.text.toString())
                parametros.put("telefono",binding.edtCelular.text.toString())
                parametros.put("pass",binding.edtContrasenia.text.toString())
                return parametros}
        }
        queue.add(resultadoPost)
    }


}