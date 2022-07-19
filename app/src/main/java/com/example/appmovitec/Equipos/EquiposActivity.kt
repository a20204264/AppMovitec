package com.example.appmovitec.Equipos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.HomeActivity
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityEquiposBinding

class EquiposActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityEquiposBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEquiposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivinicio.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ivinicio ->startActivity(Intent(this, HomeActivity::class.java))
        }
    }

}