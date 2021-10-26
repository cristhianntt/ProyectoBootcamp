package com.example.proyectobootcamp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuriosEntity")
data class UsuarioEntity(@PrimaryKey(autoGenerate = true)var id: Int = 0,
                         var nombre: String,
                         var usuario: String,
                         var clave: String)
