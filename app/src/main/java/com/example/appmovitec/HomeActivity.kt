package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bindinghome:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindinghome = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindinghome.root)

        bindinghome.ibclientes.setOnClickListener(this)
        bindinghome.ibempleados.setOnClickListener(this)
        bindinghome.ibusuarios.setOnClickListener(this)
        bindinghome.ibplanes.setOnClickListener(this)
        bindinghome.ibequipos.setOnClickListener(this)
        bindinghome.ivlogout.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ibclientes -> irClienteActivity()
            R.id.ibempleados -> irEmpleadoActivity()
            R.id.ibusuarios -> irUsuariosActivity()
            R.id.ibplanes -> irPlanesActivity()
            R.id.ibequipos -> irEquiposActivity()
            R.id.ivlogout -> irLoginActivity()



        }
    }

    private fun irLoginActivity() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }

    private fun irEquiposActivity() {
        val equipIntent = Intent(this, EquiposActivity::class.java)
        startActivity(equipIntent)
    }


    private fun irPlanesActivity() {
        val planesIntent = Intent(this, PlanesActivity::class.java)
        startActivity(planesIntent)
    }

    private fun irEmpleadoActivity() {
        val empleadoIntent= Intent(this, EmpleadosActivity::class.java)
        startActivity(empleadoIntent)
    }

    private fun irUsuariosActivity() {
        val usuariosIntent = Intent(this, UsuariosActivity::class.java)
        startActivity(usuariosIntent)
    }

    private fun irClienteActivity() {
        val clienteIntent = Intent(this, ClienteActivity::class.java)
        startActivity(clienteIntent)
    }
}