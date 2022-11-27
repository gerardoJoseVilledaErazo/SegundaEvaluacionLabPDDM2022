package com.example.ve16i04001

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ve16i04001.UsuarioApplication.Companion.prefs
import com.example.ve16i04001.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Variable para manejar el viewBinding
    private lateinit var binding: ActivityMainBinding

    var dificultad: Int = 0
    private lateinit var nickname: String
    var nFacil = 50 //  n es el número hasta que quieres que llegue (1-50)
    var nMedio = 100 //  n es el número hasta que quieres que llegue (1-100)
    var nDificil = 150 //  n es el número hasta que quieres que llegue (1-150)
    private var respuestaCorrecta: Int = 0
    var intentoI: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuración de ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        configuracionBotones()
    }

    private fun configuracionBotones() {
        // Configurar evento click de los botones
        binding.layoutPrincipal.btnIniciarJuego.setOnClickListener(this)
        binding.layoutPrincipal.btnPuntaje.setOnClickListener(this)
        binding.layoutPrincipal.btnConfiguracion.setOnClickListener(this)
        binding.layoutPrincipal.btnMostrarDatos.setOnClickListener(this)
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
            binding.layoutPrincipal.btnIniciarJuego.id -> {

//                val intent = Intent(
//                    this,
//                    IniciarJuegoActivity::class.java
//                )
//                startActivity(intent)
//                configProgressDialog()


                //bueno
//                dificultad = intent.extras!!.getInt("dificultad")
//                nickname = intent.extras!!.getString("nickname").toString()
//
//                val intent = Intent(
//                    this,
//                    IniciarJuegoActivity::class.java
//                )
//                intent.putExtra("dificultad", dificultad)
//                intent.putExtra("nickname", nickname)
//                startActivity(intent)
//                configProgressDialog()

//                dificultad = intent.extras!!.getInt("dificultad")
//                nickname = intent.extras!!.getString("nickname").toString()
//                intentoI = intent.extras!!.getInt("intentoInicial")

                dificultad = prefs.getDificultad()
                nickname = prefs.getName()
                intentoI = prefs.getIntento()

                if (/*dificultad == 0*/!prefs.getName().isNotEmpty()) {
                    // alert dialog
                    AlertDialog.Builder(this)

                        .setTitle(this.resources.getString(R.string.titulo_nickname))

                        .setMessage(this.resources.getString(R.string.registre_su_nickname_primero_en_configuraciones))
                        .setPositiveButton(android.R.string.ok,
                            DialogInterface.OnClickListener
                            { dialogInterface, i ->
                                finish()
                            }).show()
                    /// Snackbar
//                    Snackbar.make(
//                        binding.root, R.string.registre_su_nickname_primero_en_configuraciones,
//                        Snackbar.LENGTH_SHORT
//                    ).show()
                } else {

                    if (dificultad == 1) {
                        // Facil
                        respuestaCorrecta = (Math.random() * nFacil).toInt() + 1

                        val intent = Intent(
                            this,
                            IniciarJuegoActivity::class.java
                        )
                        intent.putExtra("dificultad", dificultad)
                        intent.putExtra("nickname", nickname)
                        intent.putExtra("respuestaCorrecta", respuestaCorrecta)
                        intent.putExtra("intentoInicial", intentoI)
                        startActivity(intent)
                        configProgressDialog()
                    } else if (dificultad == 2) {
                        // Medio
                        respuestaCorrecta = (Math.random() * nMedio).toInt() + 1

                        val intent = Intent(
                            this,
                            IniciarJuegoActivity::class.java
                        )
                        intent.putExtra("dificultad", dificultad)
                        intent.putExtra("nickname", nickname)
                        intent.putExtra("respuestaCorrecta", respuestaCorrecta)
                        intent.putExtra("intentoInicial", intentoI)
                        startActivity(intent)
                        configProgressDialog()
                    } else if (dificultad == 3) {
                        // Dificil
                        respuestaCorrecta = (Math.random() * nDificil).toInt() + 1

                        val intent = Intent(
                            this,
                            IniciarJuegoActivity::class.java
                        )
                        intent.putExtra("dificultad", dificultad)
                        intent.putExtra("nickname", nickname)
                        intent.putExtra("respuestaCorrecta", respuestaCorrecta)
                        intent.putExtra("intentoInicial", intentoI)
                        startActivity(intent)
                        configProgressDialog()
                    }
                }
            }
            binding.layoutPrincipal.btnPuntaje.id -> {
                startActivity(Intent(this, PuntajeActivity::class.java))
            }
            binding.layoutPrincipal.btnConfiguracion.id -> {
                startActivity(Intent(this, ConfiguracionActivity::class.java))
            }
            binding.layoutPrincipal.btnMostrarDatos.id -> {
                // Mostrar datos del desarrollador
//                // Permite ir a la vista para mostrar los datos del desarrollador de la APP
                startActivity(Intent(this, MostrarDatosActivity::class.java))
            }
        }
    }
}