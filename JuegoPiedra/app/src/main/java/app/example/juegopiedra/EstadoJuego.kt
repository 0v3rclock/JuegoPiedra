package app.example.juegopiedra

sealed class EstadoJuego {
    class Empate (val title:String,val mensaje:String) : EstadoJuego()
    class UsuarioGanador (val title:String,val mensaje:String) : EstadoJuego()
    class RobotGanador (val title:String,val mensaje:String) : EstadoJuego()
}