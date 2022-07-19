package com.example.appmovitec.Empleados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.HomeActivity
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityEmpleadosBinding

class EmpleadosActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityEmpleadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmpleadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivinicio.setOnClickListener(this)
    }



    override fun onClick(v: View) {
       when(v.id){
           R.id.ivinicio -> startActivity(Intent(this, HomeActivity::class.java))
       }
    }

}