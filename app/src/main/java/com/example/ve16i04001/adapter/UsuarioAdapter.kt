package com.example.ve16i04001.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ve16i04001.Models.Interfaces.IOnClickListener
import com.example.ve16i04001.Models.UsuarioEntity
import com.example.ve16i04001.R
import com.example.ve16i04001.UsuarioApplication
import com.example.ve16i04001.databinding.ItemUsuarioBinding

class UsuarioAdapter(
    private var lstUsuarios: MutableList<UsuarioEntity>
//    , private var context: Context
    , private val listener: IOnClickListener
) : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var listado_a_eliminar: String

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUsuarioBinding.bind(view)

        fun setListener(usuarioEntity: UsuarioEntity, position: Int) {
            with(binding.root) {
                setOnLongClickListener {
                    listener.onDeleteUsuario(usuarioEntity, position)
                    true
                }
            }
        }
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
            setListener(item, position)
            // Configurando contenido del cardview
            binding.tvNickame.text = item.getNickname()
            binding.tvPuntaje.text = item.getPuntaje()
            Glide.with(binding.ivUsuario.context).load(item.getImagen())
                .into(binding.ivUsuario)
            binding.btnDelete.setOnClickListener {
//                eliminar(position)
                UsuarioApplication.database.getUsuarioDao().delete(item)
//                updateState(position)
                notifyDataSetChanged()
            }

        }
    }

    fun updateState(position: Int) {
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int = lstUsuarios.size

    fun setUsuarios(usuarios: MutableList<UsuarioEntity>) {
        this.lstUsuarios = usuarios.toMutableList()
        notifyDataSetChanged()
    }

    fun updateUsuario(usuarioEntity: UsuarioEntity) {
        val index = lstUsuarios.indexOf(usuarioEntity)
        if (index != -1) {
            lstUsuarios[index] = usuarioEntity
            notifyItemChanged(index)
        }
    }

    fun deleteUsuario(usuarioEntity: UsuarioEntity) {
        val index = lstUsuarios.indexOf(usuarioEntity)
        if (index != -1) {
            lstUsuarios.removeAt(index)
            notifyItemRemoved(index)
        }
    }

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