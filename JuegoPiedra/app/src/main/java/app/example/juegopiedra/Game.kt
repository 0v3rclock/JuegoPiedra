package app.example.juegopiedra

import kotlin.random.Random

class Game(
    var imagenSeleccionada:Int?=null,
    var vidasUsuario:Int=3,
    var vidasRobot:Int=3
) {
    var numeroRandom=crearNumeroRandom()

    fun logicaJuego(): EstadoJuego {
        /*
        0       1       2
        piedra papel  tijera
         */
       return when{
            numeroRandom==0 && imagenSeleccionada ==2->{
                vidasUsuario--
                EstadoJuego.RobotGanador("Estado juego", "Robot Ganador")
            }
            numeroRandom ==1 && imagenSeleccionada==0->{
                vidasUsuario--
                EstadoJuego.RobotGanador("Estado juego", "Robot Ganador")
            }
            numeroRandom ==2 && imagenSeleccionada==1->{
                vidasUsuario--
                EstadoJuego.RobotGanador("Estado juego", "Robot Ganador")
            }

            imagenSeleccionada==0 && numeroRandom ==2->{
                vidasRobot--
                EstadoJuego.UsuarioGanador("Estado juego", "Usuario ganador")
            }
            imagenSeleccionada==1 && numeroRandom==0->{
                vidasRobot--
                EstadoJuego.UsuarioGanador("Estado juego", "Usuario ganador")
            }
            imagenSeleccionada ==2 && numeroRandom==1->{
                vidasRobot--
                EstadoJuego.UsuarioGanador("Estado juego", "Usuario ganador")
            }
            else-> EstadoJuego.Empate("Estado juego", "Es un empate")

        }
    }

    fun crearNumeroRandom():Int {
        numeroRandom= Random.nextInt(3)
        return numeroRandom
    }
}