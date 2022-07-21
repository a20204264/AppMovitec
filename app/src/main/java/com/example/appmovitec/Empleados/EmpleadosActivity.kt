package com.example.appmovitec.Empleados

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
import com.example.appmovitec.databinding.ActivityEmpleadosBinding
import org.json.JSONException

class EmpleadosActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityEmpleadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmpleadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivinicio.setOnClickListener(this)
        binding.btnNuevoEmpleado.setOnClickListener(this)
        CargaTabla()
    }



    override fun onClick(v: View) {
       when(v.id){
           R.id.ivinicio -> startActivity(Intent(this, HomeActivity::class.java))
           R.id.btnNuevoEmpleado -> startActivity(Intent(this, NuevoEmpleadoActivity::class.java))

       }
    }

    fun clickTablaEditarempleado(view: View){
        var txtId=view.id.toString()
        val intent = Intent(this, EditarEmpleadoActivity::class.java)
        intent.putExtra("id", txtId)
        startActivity(intent)
    }
    fun CargaTabla(){
        binding.tbempleados.removeAllViews()
        var queue= Volley.newRequestQueue(this)
        var url="http://192.168.10.19/movitec/registrosempl.php"
        var jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro=
                            LayoutInflater.from(this).inflate(R.layout.table_row_empleados,null,false)
                        val colNombre= registro.findViewById<View>(R.id.colNombre) as TextView
                        val colTelefono= registro.findViewById<View>(R.id.colTelefono) as TextView
                        val colEmail = registro.findViewById<View>(R.id.colEmail) as TextView
                        val colEditar = registro.findViewById<View>(R.id.colEditar)
                        val colBorrar= registro.findViewById<View>(R.id.colBorrar)

                        colNombre.text=jsonObject.getString("nombre")
                        colTelefono.text=jsonObject.getString("telefono")
                        colEmail.text=jsonObject.getString("email")
                        colEditar.id=jsonObject.getString("id").toInt()
                        colBorrar.id=jsonObject.getString("id").toInt()

                        binding.tbempleados.addView(registro)
                    }

                }catch (e: JSONException){
                    e.printStackTrace()
                }

            }, { error ->

            })
        queue.add(jsonObjectRequest)

    }

    fun clickTablaBorrarempleado(view: View){
        val url = "http://192.168.10.19/movitec/borrarempl.php"
        val queue= Volley.newRequestQueue(this)
        val resultadoPost= object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this,"El Empleado se elimino de forma exitosa", Toast.LENGTH_LONG).show();
                CargaTabla()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error al eliminar al empleado $error", Toast.LENGTH_LONG).show();
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