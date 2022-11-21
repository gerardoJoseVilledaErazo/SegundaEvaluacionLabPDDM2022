package com.example.ve16i04001.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ve16i04001.Models.UsuarioEntity
import com.example.ve16i04001.Models.Interfaces.UsuarioDao

@Database(entities = [UsuarioEntity::class], version = 1)
abstract class UsuarioDatabase : RoomDatabase() {
    abstract fun getUsuarioDao(): UsuarioDao
}