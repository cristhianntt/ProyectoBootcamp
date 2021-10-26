package com.example.proyectobootcamp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)

    @Query("SELECT COUNT(nombre) FROM UsuriosEntity WHERE usuario = :readUsuario AND clave = :claveUsuario")
    fun consultarUsuario(readUsuario: String, claveUsuario: String): Int
}