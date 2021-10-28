package com.example.proyectobootcamp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectobootcamp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = getSharedPreferences("login",Context.MODE_PRIVATE)
        val isSessionActivated = preferences.getBoolean("session", false)

        if(isSessionActivated){
            startActivity(Intent(this, PrincipalActivity::class.java))
        }
        else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnLogin.setOnClickListener {
                val usuario = binding.etUsuario.text.toString().trim()
                val clave = binding.etClave.text.toString().trim()

                doAsync {
                    val idUser = UsuarioApplication.database.usuarioDao().consultarUsuario(usuario, clave)
                    uiThread {
                        if(idUser is Int){
                            preferences.edit().putBoolean("session", true).apply()
                            preferences.edit().putInt("idUser", idUser).apply()
                            val intent = Intent(this@MainActivity,PrincipalActivity::class.java)
                            startActivity(intent)
                        }
                        else                startDialog()
                    }
                }

            }

            binding.loginRegistrarse.setOnClickListener {
                startActivity(Intent(this, RegistroActivity::class.java))
            }

            binding.loginOlvidoClave.setOnClickListener {
                startActivity(Intent(this, OlvideClaveActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //verificamos sesión activada
        val preferences = getSharedPreferences("login",Context.MODE_PRIVATE)
        val isSessionActivated = preferences.getBoolean("session", false)
        if(isSessionActivated) startActivity(Intent(this, PrincipalActivity::class.java))
    }

    private fun startDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Usuario o clave incorrecta")
            .setCancelable(false)
            .setPositiveButton("Aceptar",null)
            .show()
    }
}