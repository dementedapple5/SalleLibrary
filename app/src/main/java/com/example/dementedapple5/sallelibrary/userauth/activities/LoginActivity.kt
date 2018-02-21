package com.example.dementedapple5.sallelibrary.userauth.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.userauth.adapters.SectionsPagerAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSectionsPageAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso: GoogleSignInOptions = GoogleSignInOptions.DEFAULT_SIGN_IN
        var mGoogleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this, gso)

        signup_button.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

    }


}
