package com.example.ve16i04001.Models.Interfaces

import androidx.room.*
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

    ///

    @Query("SELECT * FROM UsuarioEntity WHERE nickname=:nickname")
    fun login(nickname: String): Boolean

    @Query("SELECT puntaje FROM UsuarioEntity WHERE nickname=:nickname")
    fun valorPuntaje(nickname: String): Int


    @Update
    fun UpdateUser(entityUser: UsuarioEntity)

    @Query("UPDATE UsuarioEntity SET puntaje=:puntaje WHERE nickname=:nickname")
    fun update(nickname: String, puntaje: String)
}