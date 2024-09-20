package app.example.juegopiedra

import android.app.Activity
import android.app.AlertDialog

fun Activity.createDialogo(titulo:String,mensaje:String){
    val alert=AlertDialog.Builder(this)
        .setTitle(titulo)
        .setMessage(mensaje)
        .setPositiveButton("Aceptar"){dialog,_->
            dialog.dismiss()

        }
    alert.setCancelable(false)
    alert.create().show()
}