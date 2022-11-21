package com.example.ve16i04001

import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ve16i04001.adapter.UsuarioAdapter
import com.example.ve16i04001.databinding.ActivityPuntajeBinding

class PuntajeActivity : AppCompatActivity() {

    // Se agrega variable para manejar viewBinding
    private lateinit var binding: ActivityPuntajeBinding
    private lateinit var usuarioAdapter: UsuarioAdapter
    private val llmanager = LinearLayoutManager(this)
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuracion de viewBinding
        binding = ActivityPuntajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // Habilitar action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Titulo para la actividad
        title = "Puntaje"
        // Configurar SwipeRefreshLayout
        configSwipe()
        // Configurar RecyclerView
        configRecyclerView()
    }

    // Método que configura el recyclerview
    private fun configRecyclerView() {
        usuarioAdapter = UsuarioAdapter(
            lstUsuarios = mutableListOf(),
            this
        )

        binding.rcUsuarios.setHasFixedSize(true)
        binding.rcUsuarios.layoutManager = llmanager
        binding.rcUsuarios.adapter = usuarioAdapter
//        recyclerView.apply {
//            recyclerView.setHasFixedSize(true)
//            recyclerView.layoutManager = linearLayoutManager
//            recyclerView.adapter = usuarioAdapter
//        }
    }

    private fun configSwipe() {
        binding.refreshLayout.setColorSchemeResources(R.color.teal_700, R.color.purple_200)
        binding.refreshLayout.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )
        binding.refreshLayout.setOnRefreshListener {
            Log.i("Gerardo", "FUNIONA")
            Handler(Looper.getMainLooper()).postDelayed({
                binding.refreshLayout.isRefreshing = false
            }, 2000)
            binding.refreshLayout.setRefreshing(false)
            //your code on swipe refresh
            //we are checking networking connectivity
            val connection = isNetworkAvailable()
            if (connection) {
                // Configurar RecyclerView
                configRecyclerView()
            }
        }
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            this.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null
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
}