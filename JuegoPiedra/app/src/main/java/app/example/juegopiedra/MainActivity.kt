package app.example.juegopiedra

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.example.juegopiedra.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //                                  0                   1               2
    val imagenesJuego = arrayListOf(R.drawable.piedra, R.drawable.papel, R.drawable.tijera)
    val vidasJuego = arrayListOf(
        R.drawable.cero_vidas,
        R.drawable.unavida,
        R.drawable.dosvidas,
        R.drawable.tresvidas
    )

    private val game by lazy { Game() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        game.crearNumeroRandom()
        imagenSeleccionadaUsuario()
        binding.btnJugar.setOnClickListener { juego() }
        binding.btnResetearJuego.setOnClickListener { resetJuego() }
    }

    private fun resetJuego() {
        game.imagenSeleccionada=null
        resetearImagenes()
        binding.vidasRobot.setImageResource(R.drawable.tresvidas)
        binding.vidasUsuario.setImageResource(R.drawable.tresvidas)
        game.vidasRobot=3
        game.vidasUsuario=3
        binding.btnJugar.visibility=View.VISIBLE
        binding.imagenRobot.setImageResource(R.drawable.robot)
        binding.imagenUsuario.setImageResource(R.drawable.joven)

    }

    private fun juego() {
        if (game.imagenSeleccionada == null) {
            createDialogo("Error", "Primero selacciona una imagen")
            return
        }
        when (game.logicaJuego()) {
            is EstadoJuego.Empate ->{
                createDialogo("", "Es un empate")
            }

           is EstadoJuego.RobotGanador -> {
                createDialogo("", "El robot es el ganador")
                binding.vidasUsuario.setImageResource(vidasJuego[game.vidasUsuario])
                if (game.vidasUsuario == 0) {
                    createDialogo("Juego Terminado", "El robot es el ganador")
                    binding.imagenUsuario.setImageResource(R.drawable.jove_triste)
                    binding.btnJugar.visibility = View.INVISIBLE
                    return
                }
            }

           is EstadoJuego.UsuarioGanador -> {
                createDialogo("Estado Juego", "El Usuario es el ganador")
                binding.vidasRobot.setImageResource(vidasJuego[game.vidasRobot])
                if (game.vidasRobot == 0) {
                    createDialogo("Juego Terminado", "El Usuario es el ganador")
                    binding.imagenRobot.setImageResource(R.drawable.robot_triste)
                    binding.btnJugar.visibility = View.INVISIBLE
                    return
                }
            }
        }
        game.crearNumeroRandom()
        resetearImagenes()
    }

    fun resetearImagenes() {
        binding.incognitoUsuario.setImageResource(R.drawable.incognito)
        binding.incognitoRobot.setImageResource(R.drawable.incognito)
    }

    private fun imagenSeleccionadaUsuario() {
        binding.apply {
            imagenPiedra.setOnClickListener {
                incognitoUsuario.setImageResource(R.drawable.piedra)
                game.imagenSeleccionada = 0
                incognitoRobot.setImageResource(imagenesJuego[game.numeroRandom])
            }
            imagenPapel.setOnClickListener {
                incognitoUsuario.setImageResource(R.drawable.papel)
                game.imagenSeleccionada = 1
                incognitoRobot.setImageResource(imagenesJuego[game.numeroRandom])
            }
            imagenTijera.setOnClickListener {
                incognitoUsuario.setImageResource(R.drawable.tijera)
                game.imagenSeleccionada = 2
                incognitoRobot.setImageResource(imagenesJuego[game.numeroRandom])
            }

        }

    }
}