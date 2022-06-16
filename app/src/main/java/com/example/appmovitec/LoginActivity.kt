package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.databinding.ActivityLoginBinding
import javax.net.ssl.SSLSessionBindingEvent

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvcontrasenia.setOnClickListener(this)
        binding.btningresar.setOnClickListener(this)
        binding.tvcrearcuenta.setOnClickListener(this)


    }

    override fun onClick(v: View) {
       when(v.id){
           R.id.tvcontrasenia ->irContraseniaActivity()
           R.id.btningresar -> ingresar()
           R.id.tvcrearcuenta -> irRegistroActivity()

       }
    }

    private fun irRegistroActivity() {
        val regIntent= Intent(this, RegistroActivity::class.java)
        startActivity(regIntent)
    }

    private fun ingresar() {
        val homeintent = Intent(this,
            HomeActivity::class.java)
        startActivity(homeintent)
    }

    private fun irContraseniaActivity() {
        val passActivity = Intent(this,
            PasswordActivity::class.java)
        startActivity(passActivity)
    }
}