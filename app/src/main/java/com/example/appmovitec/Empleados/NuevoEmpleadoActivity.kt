package com.example.appmovitec.Empleados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appmovitec.Clientes.ClienteActivity
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityNuevoEmpleadoBinding

class NuevoEmpleadoActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityNuevoEmpleadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNuevoEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncrearEmpl.setOnClickListener(this)
        binding.btnCancelarNewEmpl.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btncrearEmpl -> CrearEmpleado()
            R.id.btnCancelarNewEmpl -> startActivity(Intent(this, EmpleadosActivity::class.java))

        }
    }


    private fun CrearEmpleado() {
        val url = "http://192.168.10.19/movitec/insertarempl.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost= object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Empleado registrado exitosamente", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, EmpleadosActivity::class.java))

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error $error", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String,String>()

                parametros.put("nombre",binding.edtNombre.text.toString())
                parametros.put("documento",binding.edtDocumento.text.toString())
                parametros.put("email",binding.edtCorreo.text.toString())
                parametros.put("telefono",binding.edtCelular.text.toString())
                parametros.put("direccion",binding.edtdireccion.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}