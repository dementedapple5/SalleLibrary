package com.example.dementedapple5.sallelibrary.splashscreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.userauth.activities.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
