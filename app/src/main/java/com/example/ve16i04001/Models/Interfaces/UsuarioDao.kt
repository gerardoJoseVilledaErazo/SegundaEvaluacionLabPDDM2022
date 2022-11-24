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

    @Query("SELECT * FROM UsuarioEntity ORDER BY puntaje ASC LIMIT 3")
    fun getAllUsuariosByPuntaje(): List<UsuarioEntity>

    @Query("SELECT EXISTS (SELECT * FROM UsuarioEntity WHERE nickname=:nickname)")
    fun is_taken(nickname: String): Boolean

    @Query("DELETE FROM UsuarioEntity WHERE nickname=:nickname")
    fun deleteAnSpecificUsuario(nickname: String)

    @Delete
    fun delete(usuarioEntity: UsuarioEntity)
}