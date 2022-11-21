package com.example.ve16i04001.model.`interface`

import androidx.room.Dao
import androidx.room.Insert
import com.example.ve16i04001.model.UsuarioEntity

@Dao
interface UsuarioDao {

    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)
}