package com.example.dementedapple5.sallelibrary.userauth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.userauth.adapters.SectionsPagerAdapter

class LoginActivity : AppCompatActivity() {
    private lateinit var mSectionsPageAdapter: SectionsPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mSectionsPageAdapter = SectionsPagerAdapter(supportFragmentManager)
        
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
