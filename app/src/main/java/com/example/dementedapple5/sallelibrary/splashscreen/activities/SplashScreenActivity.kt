package com.example.dementedapple5.sallelibrary.splashscreen.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.dementedapple5.sallelibrary.userauth.activities.LoginActivity
/**
 * Pantalla de carga al iniciar.
 *
 * Se encarga de mostrar una pantalla intermediaria entre la inicialización de la aplicación y la carga de datos.
 *
 * @see [AppCompatActivity]
 * @see [Bundle]
 * @see [startActivity]
 * @see [finish]
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
