package com.example.proyectobootcamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectobootcamp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val usuario = binding.etUsuario.text.toString().trim()
            val clave = binding.etClave.text.toString().trim()

            doAsync {
                val number = UsuarioApplication.database.usuarioDao().consultarUsuario(usuario, clave)
                if(number != 0)     Snackbar.make(it, "El usuario existe", Snackbar.LENGTH_SHORT).show()
                else                Snackbar.make(it, "El usuario o clave son incorrectas", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.loginRegistrarse.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}