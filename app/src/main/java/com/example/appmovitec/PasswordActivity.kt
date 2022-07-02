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

    private lateinit var binding2:ActivityPasswordBinding
    var idGlobal: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2= ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        binding2.tvirlogin.setOnClickListener(this)
        binding2.btncambiarcontrasenia.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tvirlogin -> irLoginActivity()
            R.id.btncambiarcontrasenia->EditarGuardar()
        }
    }

    private fun irLoginActivity() {
        var intentLogin = Intent(this,
            LoginActivity::class.java)
        startActivity(intentLogin)
    }

    fun EditarGuardar(){
        val url = "http://192.168.10.19/movitec/cambiarpass.php"
        val queue= Volley.newRequestQueue(this)
        val resultadoPost=object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                if(binding2.edtcontrasenia.text.toString()==binding2.edtconfcontasenia.text.toString()){
                    Toast.makeText(this,"La contraseña se cambió correctamente", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Contraseñas no son Iguales", Toast.LENGTH_LONG).show();
                }
                val intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)


            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error al cambiar la contraseña $error", Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()

                parametros.put("email",binding2.edtcorreo.text.toString())
                parametros.put("pass",binding2.edtcontrasenia.text.toString())
                return parametros}
        }
        queue.add(resultadoPost)
    }


}