package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.databinding.ActivityPasswordBinding

class PasswordActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding2:ActivityPasswordBinding

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
            R.id.btncambiarcontrasenia->irLoginActivity()
        }
    }

    private fun irLoginActivity() {
        var intentLogin = Intent(this,
            LoginActivity::class.java)
        startActivity(intentLogin)
    }


}