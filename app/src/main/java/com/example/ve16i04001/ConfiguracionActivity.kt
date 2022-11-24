package com.example.ve16i04001

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.ve16i04001.databinding.ActivityConfiguracionBinding
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConfiguracionActivity : AppCompatActivity(), View.OnClickListener {

    // Variable para manejar el viewBinding
    private lateinit var binding: ActivityConfiguracionBinding
    lateinit var nickName: String

    var intentoInicial: Int = 0

    companion object {
        var isAllowed: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuración de ViewBinding
        binding = ActivityConfiguracionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // Se agrega la flecha para salir de la actividad
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Se configura el nombre de la actividad
        title = "Configuracion"

        binding.layoutRegistrar.edtNickname.addTextChangedListener { it: Editable? ->
//            val nickName: String = it.toString()
            nickName = it.toString()
            if (/*true*/ UsuarioApplication.database.getUsuarioDao().is_taken(nickName)) {
                isAllowed = false
                Toast.makeText(applicationContext, "Ya existe este usuario!!!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                isAllowed = true
            }
        }


        // Configuracion del evento click en los botones
        binding.layoutRegistrar.btnSiguiente.setOnClickListener(this)
    }

    // Se configura la flecha para salir de la actividad
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Finalizar la actividad
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun verifyEmpty(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty()) {
            editText.error = "Required field"
            editText.requestFocus()
            return false
        }
        return true
    }

    fun validar(): Int {
        var checkId: Int = 0

        if (binding.layoutRegistrar.rbtFacil.isChecked) {
            if (verifyEmpty(binding.layoutRegistrar.edtNickname)) {
                checkId = 1
            }
        } else if (binding.layoutRegistrar.rbtMedio.isChecked) {
            if (verifyEmpty(binding.layoutRegistrar.edtNickname)) {
                // Caso Medio
                checkId = 2
            }
        } else {
            if (verifyEmpty(binding.layoutRegistrar.edtNickname)) {
                // Caso Dificil
                checkId = 3
            }
        }
        return checkId
    }

    override fun onClick(p0: View?) {
        var userNick = binding.layoutRegistrar.edtNickname.text.toString()
        when (p0!!.id) {
            binding.layoutRegistrar.btnSiguiente.id -> {
                // Validar si se ha seleccionado uno de los radiobuttons
                if (verifyEmpty(binding.layoutRegistrar.edtNickname) && !binding.layoutRegistrar.rbtFacil.isChecked && !binding.layoutRegistrar.rbtMedio.isChecked && !binding.layoutRegistrar.rbtDificil.isChecked) {
                    /// Snackbar
                    Snackbar.make(
                        binding.root, R.string.seleccionar_opcion,
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else if (binding.layoutRegistrar.rbtFacil.isChecked) {
                    if (verifyEmpty(binding.layoutRegistrar.edtNickname)) {
                        // Caso Facil
                        val intent = Intent(
                            this,
//                            IniciarJuegoActivity::class.java
                            MainActivity::class.java
                        )
                        if (isAllowed) {
                            intent.putExtra("dificultad", 1)/// 1 Facil
                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()
                            intent.putExtra("nickname", nickname)
                            intent.putExtra("intentoInicial", 0)
                            startActivity(intent)
                            configProgressDialog()
                        } else {
                            Toast.makeText(
                                this,
                                "El puntaje de este Nickname se actualizará!!!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
//                            UsuarioApplication.database.getUsuarioDao()
//                                .deleteAnSpecificUsuario(nickName)
                            intent.putExtra("dificultad", 1)/// 1 Facil
                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()
                            intent.putExtra("nickname", nickname)
                            intent.putExtra("intentoInicial", 1)
                            startActivity(intent)
                            configProgressDialog()
                        }
//                        intent.putExtra("dificultad", 1)/// 1 Facil
//                        val nickname: String = binding.layoutRegistrar.edtNickname.text.toString()
//                        intent.putExtra("nickname", nickname)
//                        startActivity(intent)
//                        configProgressDialog()
                    }
                } else if (binding.layoutRegistrar.rbtMedio.isChecked) {
                    if (verifyEmpty(binding.layoutRegistrar.edtNickname)) {
                        // Caso Medio
                        val intent = Intent(
                            this,
//                            IniciarJuegoActivity::class.java
                            MainActivity::class.java
                        )
                        if (isAllowed) {
                            intent.putExtra("dificultad", 2)/// 2 medio
                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()
                            intent.putExtra("nickname", nickname)
                            intent.putExtra("intentoInicial", 0)
                            startActivity(intent)
                            configProgressDialog()
                        } else {
                            Toast.makeText(
                                this,
                                "El puntaje de este Nickname se actualizará!!!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
//                            UsuarioApplication.database.getUsuarioDao()
//                                .deleteAnSpecificUsuario(nickName)
                            intent.putExtra("dificultad", 2)/// 2 Medio
                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()
                            intent.putExtra("nickname", nickname)
                            intent.putExtra("intentoInicial", 1)
                            startActivity(intent)
                            configProgressDialog()
                        }
//                        val intent = Intent(
//                            this,
////                            IniciarJuegoActivity::class.java
//                            MainActivity::class.java
//                        )
//                        intent.putExtra("dificultad", 2)/// 2 Medio
//                        val nickname: String = binding.layoutRegistrar.edtNickname.text.toString()
//                        intent.putExtra("nickname", nickname)
//                        startActivity(intent)
//                        configProgressDialog()
                    }
                } else {
                    if (verifyEmpty(binding.layoutRegistrar.edtNickname)) {
                        // Caso Dificil
                        val intent = Intent(
                            this,
//                            IniciarJuegoActivity::class.java
                            MainActivity::class.java
                        )
                        if (isAllowed) {
                            intent.putExtra("dificultad", 3)/// 3 dificil
                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()
                            intent.putExtra("nickname", nickname)
                            intent.putExtra("intentoInicial", 0)
                            startActivity(intent)
                            configProgressDialog()
                        } else {
                            Toast.makeText(
                                this,
                                "El puntaje de este Nickname se actualizará!!!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
//                            UsuarioApplication.database.getUsuarioDao()
//                                .deleteAnSpecificUsuario(nickName)
                            intent.putExtra("dificultad", 3)/// 3 Dificil
                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()
                            intent.putExtra("nickname", nickname)
                            intent.putExtra("intentoInicial", 1)
                            startActivity(intent)
                            configProgressDialog()
                        }
//                        val intent = Intent(
//                            this,
////                            IniciarJuegoActivity::class.java
//                            MainActivity::class.java
//                        )
//                        intent.putExtra("dificultad", 3)/// 3 Dificil
//                        val nickname: String = binding.layoutRegistrar.edtNickname.text.toString()
//                        intent.putExtra("nickname", nickname)
//                        startActivity(intent)
//                        configProgressDialog()
                    }
                }
            }
        }
    }

    // Configurador del progress dialog
    fun configProgressDialog() {
        val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(
            R.layout.progress_dialog,
            null
        )
        alertBuilder.setView(dialogView)
        alertBuilder.setCancelable(false)
        val dialog = alertBuilder.create()
        dialog.show()
        // Configurando hilo, para asignar tiempo
        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
            finish()
        }, 3000)
    }
}