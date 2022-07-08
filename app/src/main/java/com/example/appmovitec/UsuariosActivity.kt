package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityUsuariosBinding
import org.json.JSONException

class UsuariosActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var bindingusu:ActivityUsuariosBinding
    var idGlobal: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingusu = ActivityUsuariosBinding.inflate(layoutInflater)
        setContentView(bindingusu.root)

        bindingusu.ivinicio.setOnClickListener(this)
        bindingusu.btnNuevoUsuario.setOnClickListener(this)
        CargaTabla()

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ivinicio -> irHomeActivity()
            R.id.btnNuevoUsuario->irNuevoUsuarioActivity()
        }
    }

    private fun irNuevoUsuarioActivity() {
        val intent=Intent(this, NuevoUsuarioActivity::class.java)
        startActivity(intent)
    }

    private fun irHomeActivity() {
        val homeIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
    }

    fun clickTablaEditar(view: View){
        var txtId=view.id.toString()
        val intent = Intent(this, EditarUsuarioActivity::class.java)
        intent.putExtra("id", txtId.toString())
        startActivity(intent)
    }


    fun CargaTabla(){
        bindingusu.tbUsuarios.removeAllViews()
        var queue= Volley.newRequestQueue(this)
        var url="http://192.168.10.19/movitec/registros.php"
        var jsonObjectRequest= JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro=
                            LayoutInflater.from(this).inflate(R.layout.table_row_p,null,false)
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

                        bindingusu.tbUsuarios?.addView(registro)
                    }

                }catch (e: JSONException){
                    e.printStackTrace()
                }

            }, Response.ErrorListener { error ->

            })
        queue.add(jsonObjectRequest)

    }

    fun clickTablaBorrar(view: View){
        val url = "http://192.168.10.19/movitec/borrar.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost= object : StringRequest(Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this,"El usuario se elimino de forma exitosa", Toast.LENGTH_LONG).show();
                CargaTabla()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error al eliminar el usuario $error", Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()
                parametros.put("id",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
        //Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
    }

}

