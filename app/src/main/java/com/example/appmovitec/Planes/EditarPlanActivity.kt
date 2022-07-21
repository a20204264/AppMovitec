package com.example.appmovitec.Planes

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
import com.example.appmovitec.databinding.ActivityEditarPlanBinding

class EditarPlanActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityEditarPlanBinding
    var idGlobal: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditarPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btneditarPlan.setOnClickListener(this)
        binding.btnCancelarEditPlan.setOnClickListener(this)

        idGlobal =intent.getStringExtra("id").toString()
        val queue= Volley.newRequestQueue(this)

        val url = "http://192.168.10.19/movitec/registroplan.php?id=$idGlobal"
        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                binding.edtNombre.setText(response.getString("nombre"))
                binding.edtVelocidad.setText(response.getString("velocidad"))
                binding.edtCosto.setText(response.getString("costo"))

            }, { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btneditarPlan -> GuardarPlan()
            R.id.btnCancelarEditPlan -> startActivity(Intent(this,PlanesActivity::class.java))
        }

    }


    private fun GuardarPlan() {
        val url = "http://192.168.10.19/movitec/editarplan.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost=object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"El Plan se edito Correctamente",Toast.LENGTH_LONG).show();
                startActivity(Intent(this, PlanesActivity::class.java))

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error al editar el Plan $error",Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String, String>()
                parametros.put("id",idGlobal!!)
                parametros.put("nombre",binding.edtNombre.text.toString())
                parametros.put("velocidad",binding.edtVelocidad.text.toString())
                parametros.put("costo",binding.edtCosto.text.toString())

                return parametros}
        }
        queue.add(resultadoPost)
    }
}