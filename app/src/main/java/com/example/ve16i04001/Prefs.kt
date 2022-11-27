package com.example.ve16i04001

import android.annotation.SuppressLint
import android.content.Context

class Prefs(context: Context) {
    val SHARED_NAME = "Mydtb"
    val SHARED_NICK_NAME = "nickname"
    val SHARED_DIFICULTAD = "dificultad"
    val SHARED_INTENTO = "intentoInicial"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)
    fun saveName(name: String) {
        storage.edit().putString(SHARED_NICK_NAME, name).apply()
    }

    fun saveDificultad(dificultad: Int) {
        storage.edit().putInt(SHARED_DIFICULTAD, dificultad).apply()
    }

    fun saveIntentoInicial(intentoInicial: Int) {
        storage.edit().putInt(SHARED_INTENTO, intentoInicial).apply()
    }

    fun getName(): String {
        return storage.getString(SHARED_NICK_NAME, "")!!
    }

    fun getDificultad(): Int {
        return storage.getInt(SHARED_DIFICULTAD, 0)!!
    }

    fun getIntento(): Int {
        return storage.getInt(SHARED_INTENTO, 0)!!
    }

    @SuppressLint("CommitPrefEdits")
    fun wipe() {
        storage.edit().clear()
    }
}