package com.example.proyectobootcamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectobootcamp.databinding.ActivityRegistroBinding
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.doAsync

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegistro.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val usuario = binding.etUsuario.text.toString().trim()
            val clave = binding.etClave.text.toString().trim()
            val confirClave = binding.etConfirmarClave.text.toString().trim()

            when(binding.cbTerminosCondiciones.isChecked){
                true -> {
                    if (isCamposEnBlanco(nombre, usuario, clave, confirClave)){
                        Snackbar.make(it, "Completar todos los campos", Snackbar.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    if (isConfirmarClave(clave, confirClave)){
                        //Snackbar.make(it, "Guardamos en la BD", Snackbar.LENGTH_SHORT).show()

                        val user = UsuarioEntity(nombre = nombre, usuario = usuario, clave = clave)
                        doAsync {
                            UsuarioApplication.database.usuarioDao().addUsuario(user)
                        }

                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    else{
                        Snackbar.make(it, "Las claves no coinciden", Snackbar.LENGTH_SHORT).show()
                    }
                }
                false -> Snackbar.make(it, "Debes aceptar los términos y condiciones", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun isCamposEnBlanco(nombre: String, usuario: String, clave: String, confirClave: String): Boolean {

        if (nombre.isEmpty() || usuario.isEmpty() || clave.isEmpty() || confirClave.isEmpty()){
            return true
        }
        return false
    }

    private fun isConfirmarClave(clave: String, confirClave: String): Boolean = clave == confirClave

}