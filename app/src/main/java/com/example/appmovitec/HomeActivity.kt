package com.example.appmovitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmovitec.Clientes.ClienteActivity
import com.example.appmovitec.Empleados.EmpleadosActivity
import com.example.appmovitec.Equipos.EquiposActivity
import com.example.appmovitec.Planes.PlanesActivity
import com.example.appmovitec.Usuarios.UsuariosActivity
import com.example.appmovitec.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibclientes.setOnClickListener(this)
        binding.ibempleados.setOnClickListener(this)
        binding.ibusuarios.setOnClickListener(this)
        binding.ibplanes.setOnClickListener(this)
        binding.ibequipos.setOnClickListener(this)
        binding.ivlogout.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ibclientes -> startActivity(Intent(this, ClienteActivity::class.java))
            R.id.ibempleados -> startActivity(Intent(this, EmpleadosActivity::class.java))
            R.id.ibusuarios -> startActivity(Intent(this, UsuariosActivity::class.java))
            R.id.ibplanes -> startActivity(Intent(this, PlanesActivity::class.java))
            R.id.ibequipos -> startActivity(Intent(this, EquiposActivity::class.java))
            R.id.ivlogout -> startActivity(Intent(this, LoginActivity::class.java))

        }
    }



}