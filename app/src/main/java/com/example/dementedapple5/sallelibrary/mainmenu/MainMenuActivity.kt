package com.example.dementedapple5.sallelibrary.mainmenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.dementedapple5.sallelibrary.R
import kotlinx.android.synthetic.main.genre_item.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val layoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mRecyclerItem.layoutManager = layoutManager

    }




}
