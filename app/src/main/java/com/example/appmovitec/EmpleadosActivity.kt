package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityEmpleadosBinding

class EmpleadosActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var bindingempl:ActivityEmpleadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingempl = ActivityEmpleadosBinding.inflate(layoutInflater)
        setContentView(bindingempl.root)

        bindingempl.ivinicio.setOnClickListener(this)
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