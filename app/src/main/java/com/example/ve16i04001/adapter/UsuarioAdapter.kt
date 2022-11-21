package com.example.ve16i04001.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.ve16i04001.R
import com.example.ve16i04001.databinding.ItemUsuarioBinding
import com.example.ve16i04001.model.UsuarioEntity

class UsuarioAdapter(
    private var lstUsuarios: MutableList<UsuarioEntity>, private var context: Context
) : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {
    private lateinit var listado_a_eliminar: String

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUsuarioBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layout = LayoutInflater.from(parent.context)
        return ViewHolder(
            layout.inflate(
                R.layout.item_usuario,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lstUsuarios[position]
        // Uso de funcion de alcance para agregar acciones al objeto en un mismo bloque
        with(holder) {
            // Configurando contenido del cardview
            binding.tvNickame.text = item.getNickname()
            binding.tvPuntaje.text = item.getPuntaje()
            binding.btnDelete.setOnClickListener {
                eliminar(position)
            }

        }
    }

    override fun getItemCount(): Int = lstUsuarios.size

    fun eliminar(position: Int) {
        listado_a_eliminar = lstUsuarios[position].toString()
        AlertDialog.Builder(context)
            .setTitle("Confirmacion")
            .setMessage("Esta seguro que desea eliminar: $listado_a_eliminar")
            .setPositiveButton("Aceptar") { dialogInterface: DialogInterface?, i1: Int ->
                lstUsuarios.removeAt(position)
                notifyDataSetChanged()
                Toast.makeText(context, "Eliminado", Toast.LENGTH_SHORT).show();
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}