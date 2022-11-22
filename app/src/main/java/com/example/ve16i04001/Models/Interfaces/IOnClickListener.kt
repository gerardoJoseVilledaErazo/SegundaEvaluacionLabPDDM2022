package com.example.ve16i04001.Models.Interfaces

import com.example.ve16i04001.Models.UsuarioEntity

interface IOnClickListener {
    fun onDeleteUsuario(usuarioEntity: UsuarioEntity, position: Int)
}