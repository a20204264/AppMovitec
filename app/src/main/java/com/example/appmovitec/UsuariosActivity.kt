package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityUsuariosBinding

class UsuariosActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var bindingusu:ActivityUsuariosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingusu = ActivityUsuariosBinding.inflate(layoutInflater)
        setContentView(bindingusu.root)

        bindingusu.ivinicio.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ivinicio -> irHomeActivity()
        }
    }

    private fun irHomeActivity() {
        val homeIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
    }
}