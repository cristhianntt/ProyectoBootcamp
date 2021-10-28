package com.example.proyectobootcamp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)

    @Query("SELECT id FROM UsuriosEntity WHERE usuario = :readUsuario AND clave = :claveUsuario")
    fun consultarUsuario(readUsuario: String, claveUsuario: String): Int

    @Query("SELECT nombre FROM UsuriosEntity WHERE id = :id" )
    fun consultarNombre(id: Int): String

    @Query("SELECT clave FROM usuriosentity WHERE usuario = :usuario AND pregSecreta = :preSecreta")
    fun recuperarClave(usuario: String, preSecreta: String): String
}