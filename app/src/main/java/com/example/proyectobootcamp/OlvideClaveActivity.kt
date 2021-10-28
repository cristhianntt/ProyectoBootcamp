package com.example.proyectobootcamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.proyectobootcamp.databinding.ActivityOlvideClaveBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class OlvideClaveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOlvideClaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_olvide_clave)

        binding = ActivityOlvideClaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRecuperarClave.setOnClickListener {
            val usuario = binding.etUsuario.text.toString().trim()
            val pregSecreta = binding.etPreguntaSecreta.text.toString().trim()

            if(isCamposEnBlanco(usuario, pregSecreta)){
                startDialog("Completar todos los campos")
            }else{
                doAsync {
                    val clave = UsuarioApplication.database.usuarioDao().recuperarClave(usuario, pregSecreta) ?: "Respuesta incorrecta"
                    uiThread {
                        startDialog(clave)
                    }
                }
            }
        }
    }

    private fun isCamposEnBlanco(usuario: String, pregSecreta: String): Boolean {

        if (usuario.isEmpty() || pregSecreta.isEmpty()){
            return true
        }
        return false
    }

    private fun startDialog(mensaje: String) {
        if(mensaje == "Respuesta incorrecta" || mensaje == "Completar todos los campos"){
            MaterialAlertDialogBuilder(this)
                .setTitle(mensaje)
                .setCancelable(false)
                .setPositiveButton("Aceptar",null)
                .show()
        }
        else{
            MaterialAlertDialogBuilder(this)
                .setTitle("Tu clave es: $mensaje")
                .setCancelable(false)
                .setPositiveButton("Aceptar",{ dialogInterface, i ->
                    startActivity(Intent(this, MainActivity::class.java))
                })
                .show()
        }

    }
}