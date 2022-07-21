package com.example.appmovitec.Equipos

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
import com.example.appmovitec.Empleados.EmpleadosActivity
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityEditarEquipoBinding

class EditarEquipoActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityEditarEquipoBinding
    var idGlobal: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditarEquipoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btneditarEquipo.setOnClickListener(this)
        binding.btnCancelarEditEquipo.setOnClickListener(this)


        idGlobal =intent.getStringExtra("id").toString()
        val queue= Volley.newRequestQueue(this)

        val url = "http://192.168.10.19/movitec/registroequipo.php?id=$idGlobal"
        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                binding.edtMarca.setText(response.getString("marca"))
                binding.edtModelo.setText(response.getString("modelo"))
                binding.edtSerie.setText(response.getString("serie"))
                binding.edtEstado.setText(response.getString("estado"))

            }, { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

    }

    override fun onClick(v: View) {
       when(v.id){
           R.id.btneditarEquipo ->GuardarEquipo()
           R.id.btnCancelarEditEquipo ->startActivity(Intent(this, EquiposActivity::class.java))
       }
    }

    private fun GuardarEquipo() {
        val url = "http://192.168.10.19/movitec/editarequipo.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost=object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"El Equipo se edito Correctamente",Toast.LENGTH_LONG).show();
                startActivity(Intent(this, EquiposActivity::class.java))

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error al editar el equipo $error",Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String, String>()
                parametros.put("id",idGlobal!!)
                parametros.put("marca",binding.edtMarca.text.toString())
                parametros.put("modelo",binding.edtModelo.text.toString())
                parametros.put("serie",binding.edtSerie.text.toString())
                parametros.put("estado",binding.edtEstado.text.toString())

                return parametros}
        }
        queue.add(resultadoPost)
    }
}