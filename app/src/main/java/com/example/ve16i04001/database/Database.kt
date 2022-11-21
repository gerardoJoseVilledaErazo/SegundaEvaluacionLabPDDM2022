package com.example.ve16i04001.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ve16i04001.model.UsuarioEntity
import com.example.ve16i04001.model.`interface`.UsuarioDao

@Database(
    entities = [UsuarioEntity::class], version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun getUsuarioDao(): UsuarioDao
}