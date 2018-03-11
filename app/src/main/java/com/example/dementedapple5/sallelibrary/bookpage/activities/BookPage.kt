package com.example.dementedapple5.sallelibrary.bookpage.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.booklayout.BookLayout
import com.example.dementedapple5.sallelibrary.R
import kotlinx.android.synthetic.main.activity_book_page.*
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth


class BookPage : AppCompatActivity(), BookLayout.OnAddedToWishlistListener {
    private lateinit var mAuth: FirebaseAuth

    override fun onAddedToWishlist(source: BookLayout, textToDisplay: String) {
        book.setWishlistButtonIcon(R.drawable.ic_done)
        book.setButtonText("En tu Wishlist")
        Snackbar.make(findViewById(R.id.coordinator), "${textToDisplay} ha sido añadido a tu Wishlist", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)
        setSupportActionBar(book_toolbar)
        book_toolbar.title = "Título del libro"

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        mAuth = FirebaseAuth.getInstance()

        book.setOnAddedToWishlistListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_logout -> {
                mAuth.signOut()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
