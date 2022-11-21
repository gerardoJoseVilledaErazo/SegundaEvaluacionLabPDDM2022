package com.example.ve16i04001

import android.app.Application
import androidx.room.Room
import com.example.ve16i04001.database.UsuarioDatabase

class UsuarioApplication : Application() {

    companion object {
        lateinit var database: UsuarioDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            UsuarioDatabase::class.java,
            "UsuarioDB"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration() // esta linea destruye la base de datos y la vuelve a recrear con los cambios agregados
            .build()
    }
}