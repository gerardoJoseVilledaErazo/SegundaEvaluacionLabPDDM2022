package com.example.ve16i04001.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuarioEntity")
class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "nickname") private var nickname: String,
    @ColumnInfo(name = "puntaje") private var puntaje: String,
    @ColumnInfo(name = "imagen") private var imagen: String
)