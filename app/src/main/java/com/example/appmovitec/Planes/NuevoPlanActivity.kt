package com.example.appmovitec.Planes

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
import com.example.appmovitec.databinding.ActivityNuevoPlanBinding

class NuevoPlanActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityNuevoPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNuevoPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncrearPlan.setOnClickListener(this)
        binding.btnCancelarNewPlan.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btncrearPlan -> CrearPlan()
            R.id.btnCancelarNewPlan -> startActivity(Intent(this, PlanesActivity::class.java))
        }
    }

    private fun CrearPlan() {
        val url = "http://192.168.10.19/movitec/insertarplan.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost= object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Plan registrado exitosamente", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, PlanesActivity::class.java))

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error $error", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String,String>()

                parametros.put("nombre",binding.edtNombre.text.toString())
                parametros.put("velocidad",binding.edtVelocidad.text.toString())
                parametros.put("costo",binding.edtCosto.text.toString())

                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}