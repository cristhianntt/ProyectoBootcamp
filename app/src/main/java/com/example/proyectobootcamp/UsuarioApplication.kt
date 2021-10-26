package com.example.proyectobootcamp

import android.app.Application
import androidx.room.Room

class UsuarioApplication: Application() {
    companion object{
        lateinit var database: UsuarioDataBase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, UsuarioDataBase::class.java, "UsuarioDatabase").build()
    }
}