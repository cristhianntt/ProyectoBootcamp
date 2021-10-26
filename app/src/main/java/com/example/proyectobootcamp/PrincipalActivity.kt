package com.example.proyectobootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.proyectobootcamp.databinding.ActivityPrincipalBinding
import com.example.proyectobootcamp.fragments.AjustesFragment
import com.example.proyectobootcamp.fragments.BuscarFragment
import com.example.proyectobootcamp.fragments.HomeFragment
import com.example.proyectobootcamp.fragments.TusLibrosFragment

class PrincipalActivity : AppCompatActivity() {

    private lateinit var Binding: ActivityPrincipalBinding
    private val ajustesFragment = AjustesFragment()
    private val buscarFragment = BuscarFragment()
    private val homeFragment = HomeFragment()
    private val tusLibrosFragment = TusLibrosFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        replaceFragment(homeFragment)

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

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}