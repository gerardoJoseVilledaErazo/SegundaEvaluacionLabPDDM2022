package com.example.ve16i04001.Models.Interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ve16i04001.Models.UsuarioEntity

@Dao
interface UsuarioDao {

    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)

    @Query("SELECT * FROM UsuarioEntity ORDER BY puntaje ASC")
    fun getAllUsuariosByPuntaje(): List<UsuarioEntity>

    @Query("DELETE FROM UsuarioEntity")
    fun deleteAllUsuarios()

    @Delete
    fun delete(usuarioEntity: UsuarioEntity)
}