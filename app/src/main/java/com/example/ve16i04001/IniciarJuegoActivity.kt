package com.example.ve16i04001

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.ve16i04001.databinding.ActivityIniciarJuegoBinding
import com.google.android.material.snackbar.Snackbar

class IniciarJuegoActivity : AppCompatActivity(), View.OnClickListener {

    // Se agrega variable para manejar viewBinding
    private lateinit var binding: ActivityIniciarJuegoBinding

    private var dificultad: Int = 0
    private lateinit var nickname: String
    private var numeroIntentos: Int = 0
    private var miRespuesta: Int = 0
    private var respuestaCorrecta: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuracion de viewBinding
        binding = ActivityIniciarJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // Habilitar action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Titulo para la actividad
        title = "Iniciar Juego"

        dificultad = intent.extras!!.getInt("dificultad")
        nickname = intent.extras!!.getString("nickname").toString()

        respuestaCorrecta = intent.extras!!.getInt("respuestaCorrecta")

        binding.layoutIniciarJuego.txtAttempts.text = "Numero de intentos: $numeroIntentos"
        binding.layoutIniciarJuego.txtUser.text = "Nickname: $nickname"

        // Configuracion de evento click para el botón de registro
        binding.layoutIniciarJuego.btnDone.setOnClickListener(this)

        if (dificultad == 1) {
            // En este caso la dificultad es Facil

//            val valor: String = binding.layoutIniciarJuego.edtFacil.text.toString()
//            miRespuesta = Integer.parseInt(valor)

            binding.layoutIniciarJuego.txtDificultad.text = "Dificultad: Facil"
            binding.layoutIniciarJuego.tilMedio.visibility = View.GONE
            binding.layoutIniciarJuego.tilDificil.visibility = View.GONE
        }

        if (dificultad == 2) {
            // En este caso la dificultad es Medio

//            val valor: String = binding.layoutIniciarJuego.edtMedio.text.toString()
//            miRespuesta = Integer.parseInt(valor)

            binding.layoutIniciarJuego.txtDificultad.text = "Dificultad: Medio"
            binding.layoutIniciarJuego.tilFacil.visibility = View.GONE
            binding.layoutIniciarJuego.tilDificil.visibility = View.GONE
        }

        if (dificultad == 3) {
            // En este caso la dificultad es Dificil

//            val valor: String = binding.layoutIniciarJuego.edtDificil.text.toString()
//            miRespuesta = Integer.parseInt(valor)

            binding.layoutIniciarJuego.txtDificultad.text = "Dificultad: Dificil"
            binding.layoutIniciarJuego.tilMedio.visibility = View.GONE
            binding.layoutIniciarJuego.tilFacil.visibility = View.GONE
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    // Configurar action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Debe permitir regresar a la actividad anterior
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
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

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            binding.layoutIniciarJuego.btnDone.id -> {
                // Hay que evaluar si el tipo de dificultad es Facil o Medio o Dificil
                if (dificultad == 1) {
                    // Facil
                    if (verifyEmpty(binding.layoutIniciarJuego.edtFacil)) {
                        if (miRespuesta == respuestaCorrecta) {
                            /// Snackbar
                            Snackbar.make(
                                binding.root, R.string.respuesta_correcta,
                                Snackbar.LENGTH_SHORT
                            ).show()
                            val intent = Intent(
                                this,
                                MainActivity::class.java
                            )
                            startActivity(intent)
                            configProgressDialog()
                        } else {
                            binding.layoutIniciarJuego.edtFacil.setText("")
                            if (miRespuesta < respuestaCorrecta) {
                                // El numero correcto es mayor
                                /// Snackbar
                                Snackbar.make(
                                    binding.root, R.string.numero_es_mayor,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            } else if (miRespuesta > respuestaCorrecta) {
                                // El numero correcto es menor
                                /// Snackbar
                                Snackbar.make(
                                    binding.root, R.string.numero_es_menor,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                        numeroIntentos += 1
                        binding.layoutIniciarJuego.txtAttempts.text =
                            "Numero de intentos: $numeroIntentos"
//                        doAsync {
//                            UsuarioApplication.database.getUsuarioDao().addUsuario(
//                                UsuarioEntity(
//                                    nickname = nickname,
//                                    puntaje = numeroIntentos.toString()
//                                )
//                            )
//                            uiThread {
//                                goToMainActivity()
//                            }
//                        }
                    }
                } else if (dificultad == 2) {
//
                    if (verifyEmpty(binding.layoutIniciarJuego.edtMedio)) {
                        if (miRespuesta == respuestaCorrecta) {
                            /// Snackbar
                            Snackbar.make(
                                binding.root, R.string.respuesta_correcta,
                                Snackbar.LENGTH_SHORT
                            ).show()
                            val intent = Intent(
                                this,
                                MainActivity::class.java
                            )
                            startActivity(intent)
                            configProgressDialog()
                        } else {
                            binding.layoutIniciarJuego.edtMedio.setText("")
                            if (miRespuesta < respuestaCorrecta) {
                                // El numero correcto es mayor
                                /// Snackbar
                                Snackbar.make(
                                    binding.root, R.string.numero_es_mayor,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            } else if (miRespuesta > respuestaCorrecta) {
                                // El numero correcto es menor
                                /// Snackbar
                                Snackbar.make(
                                    binding.root, R.string.numero_es_menor,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                        numeroIntentos += 1
                        binding.layoutIniciarJuego.txtAttempts.text =
                            "Numero de intentos: $numeroIntentos"
//                        doAsync {
//                            UsuarioApplication.database.getUsuarioDao().addUsuario(
//                                UsuarioEntity(
//                                    nickname = nickname,
//                                    puntaje = numeroIntentos.toString()
//                                )
//                            )
//                            uiThread {
//                                goToMainActivity()
//                            }
//                        }
                    }
                } else if (dificultad == 3) {
//
                    if (verifyEmpty(binding.layoutIniciarJuego.edtDificil)) {
                        if (miRespuesta == respuestaCorrecta) {
                            /// Snackbar
                            Snackbar.make(
                                binding.root, R.string.respuesta_correcta,
                                Snackbar.LENGTH_SHORT
                            ).show()
                            val intent = Intent(
                                this,
                                MainActivity::class.java
                            )
                            startActivity(intent)
                            configProgressDialog()
                        } else {
                            binding.layoutIniciarJuego.edtDificil.setText("")
                            if (miRespuesta < respuestaCorrecta) {
                                // El numero correcto es mayor
                                /// Snackbar
                                Snackbar.make(
                                    binding.root, R.string.numero_es_mayor,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            } else if (miRespuesta > respuestaCorrecta) {
                                // El numero correcto es menor
                                /// Snackbar
                                Snackbar.make(
                                    binding.root, R.string.numero_es_menor,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                        numeroIntentos += 1
                        binding.layoutIniciarJuego.txtAttempts.text =
                            "Numero de intentos: $numeroIntentos"
//                        doAsync {
//                            UsuarioApplication.database.getUsuarioDao().addUsuario(
//                                UsuarioEntity(
//                                    nickname = nickname,
//                                    puntaje = numeroIntentos.toString()
//                                )
//                            )
//                            uiThread {
//                                goToMainActivity()
//                            }
//                        }
                    }
                }
            }
        }
    }

    fun verifyEmpty(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty()) {
            editText.error = "Required field"
            editText.requestFocus()
            return false
        }
        return true
    }
}