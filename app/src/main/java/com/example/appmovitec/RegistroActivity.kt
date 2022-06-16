package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var bindingReg:ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReg = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(bindingReg.root)

        bindingReg.tviniciarsesion.setOnClickListener(this)
        bindingReg.btnregistrar.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tviniciarsesion -> irLoginActivity()
            R.id.btnregistrar -> irLoginActivity()
        }
    }

    private fun irLoginActivity() {
        val logIntent = Intent(this, LoginActivity::class.java)
        startActivity(logIntent)
    }
}