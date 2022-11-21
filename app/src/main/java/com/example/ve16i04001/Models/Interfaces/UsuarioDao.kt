package com.example.ve16i04001.Models.Interfaces

import androidx.room.Dao
import androidx.room.Insert
import com.example.ve16i04001.Models.UsuarioEntity

@Dao
interface UsuarioDao {

    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)
}