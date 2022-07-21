package com.example.appmovitec.Equipos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appmovitec.Empleados.EmpleadosActivity
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityNuevoEquipoBinding

class NuevoEquipoActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityNuevoEquipoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNuevoEquipoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncrearEquipo.setOnClickListener(this)
        binding.btnCancelarNewEquipo.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btncrearEquipo -> CrearEquipo()
            R.id.btnCancelarNewEquipo ->startActivity(Intent(this,EquiposActivity::class.java))
        }
    }


    private fun CrearEquipo() {
        val url = "http://192.168.10.19/movitec/insertarequipo.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost= object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Equipo registrado exitosamente", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, EquiposActivity::class.java))

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error $error", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String,String>()

                parametros.put("marca",binding.edtMarca.text.toString())
                parametros.put("modelo",binding.edtModelo.text.toString())
                parametros.put("serie",binding.edtSerie.text.toString())
                parametros.put("estado",binding.edtEstado.text.toString())

                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}