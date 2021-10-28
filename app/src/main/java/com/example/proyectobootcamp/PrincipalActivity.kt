package com.example.proyectobootcamp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.proyectobootcamp.databinding.ActivityPrincipalBinding
import com.example.proyectobootcamp.fragments.AjustesFragment
import com.example.proyectobootcamp.fragments.BuscarFragment
import com.example.proyectobootcamp.fragments.HomeFragment
import com.example.proyectobootcamp.fragments.TusLibrosFragment
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrincipalActivity : AppCompatActivity() {

    private lateinit var Binding: ActivityPrincipalBinding
    private val ajustesFragment = AjustesFragment()
    private val buscarFragment = BuscarFragment()
    private val homeFragment = HomeFragment()
    private val tusLibrosFragment = TusLibrosFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = getSharedPreferences("login",Context.MODE_PRIVATE)
        val isSessionActivated = preferences.getBoolean("session", false)

        if(isSessionActivated){
            Binding = ActivityPrincipalBinding.inflate(layoutInflater)
            setContentView(Binding.root)

            val idUser = preferences.getInt("idUser", 1)
            doAsync {
                val user = UsuarioApplication.database.usuarioDao().consultarNombre(idUser)
                uiThread {
                    Binding.tvUsuario.text = "Hola $user"
                }
            }

            replaceFragment(homeFragment)

            Binding.btnLogOut.setOnClickListener {
                preferences.edit().putBoolean("session", false).apply()
                startActivity(Intent(this, MainActivity::class.java))
            }

            Binding.bottomNavigation.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.ic_home -> replaceFragment(homeFragment)
                    R.id.ic_buscar -> replaceFragment(buscarFragment)
                    R.id.ic_tus_libros -> replaceFragment(tusLibrosFragment)
                    R.id.ic_ajustes -> replaceFragment(ajustesFragment)
                }
                true
            }
        }
        else{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        //verificamos sesión activada
        val preferences = getSharedPreferences("login",Context.MODE_PRIVATE)
        val isSessionActivated = preferences.getBoolean("session", false)
        if(!isSessionActivated) startActivity(Intent(this, MainActivity::class.java))
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}