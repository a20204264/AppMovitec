package com.example.appmovitec.Empleados

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
import com.example.appmovitec.Clientes.ClienteActivity
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityEditarEmpleadoBinding

class EditarEmpleadoActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityEditarEmpleadoBinding
    var idGlobal: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditarEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btneditarEmpl.setOnClickListener(this)
        binding.btnCancelarEditEmpleado.setOnClickListener(this)

        idGlobal =intent.getStringExtra("id").toString()
        val queue= Volley.newRequestQueue(this)

        val url = "http://192.168.10.19/movitec/registroempl.php?id=$idGlobal"
        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                binding.edtNombre.setText(response.getString("nombre"))
                binding.edtDocumento.setText(response.getString("documento"))
                binding.edtCorreo.setText(response.getString("email"))
                binding.edtCelular.setText(response.getString("telefono"))
                binding.edtdireccion.setText(response.getString("direccion"))

            }, { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)


    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btneditarEmpl -> GuardarEmpleado()
            R.id.btnCancelarEditEmpleado -> startActivity(Intent(this,EmpleadosActivity::class.java ))
        }
    }

    private fun GuardarEmpleado() {
        val url = "http://192.168.10.19/movitec/editarempl.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost=object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"El Empleado se edito Correctamente",Toast.LENGTH_LONG).show();
                startActivity(Intent(this, EmpleadosActivity::class.java))

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error al editar al empleado $error",Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String> {
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