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
import com.example.appmovitec.databinding.ActivityPasswordBinding

class PasswordActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityPasswordBinding
    var idGlobal: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvirlogin.setOnClickListener(this)
        binding.btncambiarcontrasenia.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tvirlogin -> startActivity(Intent(this,LoginActivity::class.java))
            R.id.btncambiarcontrasenia->EditarGuardar()
        }
    }



    fun EditarGuardar(){
        val url = "http://192.168.10.19/movitec/cambiarpass.php"
        val queue= Volley.newRequestQueue(this)
        val resultadoPost=object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                if(binding.edtcontrasenia.text.toString()==binding.edtconfcontasenia.text.toString()){
                    Toast.makeText(this,"La contraseña se cambió correctamente", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Contraseñas no son Iguales", Toast.LENGTH_LONG).show();
                }
                 startActivity(Intent(this,LoginActivity::class.java))


            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error al cambiar la contraseña $error", Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String>{
                val parametros = HashMap<String, String>()

                parametros.put("email",binding.edtcorreo.text.toString())
                parametros.put("pass",binding.edtcontrasenia.text.toString())
                return parametros}
        }
        queue.add(resultadoPost)
    }


}