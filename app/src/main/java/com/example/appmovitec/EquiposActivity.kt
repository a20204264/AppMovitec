package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.databinding.ActivityEquiposBinding

class EquiposActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var bindingE:ActivityEquiposBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingE = ActivityEquiposBinding.inflate(layoutInflater)
        setContentView(bindingE.root)

        bindingE.ivinicio.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ivinicio ->irhomeActivity()
        }
    }

    private fun irhomeActivity() {
        val homeIntent = Intent(this,HomeActivity::class.java)
        startActivity(homeIntent)
    }
}