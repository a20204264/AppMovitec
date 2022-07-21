package com.example.appmovitec.Planes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appmovitec.Clientes.EditarClienteActivity
import com.example.appmovitec.HomeActivity
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityPlanesBinding
import org.json.JSONException

class PlanesActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var binding:ActivityPlanesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivinicio.setOnClickListener(this)
        binding.btnNuevoPlan.setOnClickListener(this)
        CargaTabla()

    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.ivinicio ->startActivity(Intent(this, HomeActivity::class.java))
            R.id.btnNuevoPlan -> startActivity(Intent(this, NuevoPlanActivity::class.java))
        }
    }

    fun clickTablaEditarplan(view: View){
        var txtId=view.id.toString()
        val intent = Intent(this, EditarPlanActivity::class.java)
        intent.putExtra("id", txtId)
        startActivity(intent)
    }

    fun CargaTabla(){
        binding.tbplanes.removeAllViews()
        var queue= Volley.newRequestQueue(this)
        var url="http://192.168.10.19/movitec/registrosplan.php"
        var jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro=
                            LayoutInflater.from(this).inflate(R.layout.table_row_planes,null,false)
                        val colNombre= registro.findViewById<View>(R.id.colNombre) as TextView
                        val colVelocidad= registro.findViewById<View>(R.id.colVelocidad) as TextView
                        val colCosto = registro.findViewById<View>(R.id.colCosto) as TextView
                        val colEditar = registro.findViewById<View>(R.id.colEditar)
                        val colBorrar= registro.findViewById<View>(R.id.colBorrar)

                        colNombre.text=jsonObject.getString("nombre")
                        colVelocidad.text=jsonObject.getString("velocidad")
                        colCosto.text="S/."+ jsonObject.getString("costo")
                        colEditar.id=jsonObject.getString("id").toInt()
                        colBorrar.id=jsonObject.getString("id").toInt()

                        binding.tbplanes.addView(registro)
                    }

                }catch (e: JSONException){
                    e.printStackTrace()
                }

            }, { error ->

            })
        queue.add(jsonObjectRequest)

    }

    fun clickTablaBorrarPlan(view: View){
        val url = "http://192.168.10.19/movitec/borrarplan.php"
        val queue= Volley.newRequestQueue(this)
        val resultadoPost= object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this,"El Plan se elimino de forma exitosa", Toast.LENGTH_LONG).show();
                CargaTabla()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error al eliminar el Plan $error", Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String>{
                val parametros = HashMap<String, String>()
                parametros.put("id",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
        //Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
    }

}