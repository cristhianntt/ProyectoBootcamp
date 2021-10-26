package com.example.proyectobootcamp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UsuarioEntity::class), version = 1)
abstract class UsuarioDataBase: RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}