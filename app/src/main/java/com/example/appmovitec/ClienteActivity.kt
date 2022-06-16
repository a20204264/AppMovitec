package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.R
import com.example.appmovitec.databinding.ActivityClienteBinding

class ClienteActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var bindingcli:ActivityClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingcli = ActivityClienteBinding.inflate(layoutInflater)
        setContentView(bindingcli.root)

        bindingcli.ivinicio.setOnClickListener(this)

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