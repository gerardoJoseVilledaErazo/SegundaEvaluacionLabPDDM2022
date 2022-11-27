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
import com.example.ve16i04001.UsuarioApplication.Companion.prefs
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
                Toast.makeText(applicationContext, "Ya existe este usuario!!! Se actualizara el puntaje. Si prosigue", Toast.LENGTH_SHORT)
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

    private fun configSharedPreference() {
        val preferences = getSharedPreferences("Users", MODE_PRIVATE)
        with(preferences.edit()) {
            putString("nickname", binding.layoutRegistrar.edtNickname.text.toString())
            putInt("dificultad", 1)
            putInt("intentoInicial", 0)
                .apply()
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onClick(p0: View?) {
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
                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()

                            prefs.saveDificultad(1)
                            prefs.saveName(nickname)
                            prefs.saveIntentoInicial(0)

                            configProgressDialog()
                            goToMainActivity()
                        } else {
                            Toast.makeText(
                                this,
                                "El puntaje de este Nickname se actualizará!!!",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()

//                            intent.putExtra("dificultad", 1)/// 1 Facil
//                            intent.putExtra("nickname", nickname)
//                            intent.putExtra("intentoInicial", 1)
//                            startActivity(intent)

                            prefs.saveDificultad(1)
                            prefs.saveName(nickname)
                            prefs.saveIntentoInicial(1)

                            configProgressDialog()
                            goToMainActivity()
                        }
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
                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()

//                            intent.putExtra("dificultad", 2)/// 2 medio
//                            intent.putExtra("nickname", nickname)
//                            intent.putExtra("intentoInicial", 0)
//                            startActivity(intent)

                            prefs.saveDificultad(2)
                            prefs.saveName(nickname)
                            prefs.saveIntentoInicial(0)

                            configProgressDialog()
                            goToMainActivity()
                        } else {
                            Toast.makeText(
                                this,
                                "El puntaje de este Nickname se actualizará!!!",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()

                            prefs.saveDificultad(2)
                            prefs.saveName(nickname)
                            prefs.saveIntentoInicial(1)

                            configProgressDialog()
                            goToMainActivity()
                        }
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
                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()

//                            intent.putExtra("dificultad", 3)/// 3 dificil
//                            intent.putExtra("nickname", nickname)
//                            intent.putExtra("intentoInicial", 0)
//                            startActivity(intent)

                            prefs.saveDificultad(3)
                            prefs.saveName(nickname)
                            prefs.saveIntentoInicial(0)

                            configProgressDialog()
                            goToMainActivity()
                        } else {
                            Toast.makeText(
                                this,
                                "El puntaje de este Nickname se actualizará!!!",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                            val nickname: String =
                                binding.layoutRegistrar.edtNickname.text.toString()

                            prefs.saveDificultad(3)
                            prefs.saveName(nickname)
                            prefs.saveIntentoInicial(1)

                            configProgressDialog()
                            goToMainActivity()
                        }
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