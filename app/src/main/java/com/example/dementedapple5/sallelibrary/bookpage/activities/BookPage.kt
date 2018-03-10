package com.example.dementedapple5.sallelibrary.bookpage.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.booklayout.BookLayout
import com.example.dementedapple5.sallelibrary.R
import kotlinx.android.synthetic.main.activity_book_page.*
import android.support.design.widget.Snackbar



class BookPage : AppCompatActivity(), BookLayout.OnAddedToWishlistListener {
    override fun onAddedToWishlist(source: BookLayout, textToDisplay: String) {
        book.setWishlistButtonIcon(R.drawable.ic_done)
        book.setButtonText("En tu Wishlist")
        Snackbar.make(findViewById(R.id.coordinator), "${textToDisplay} ha sido añadido a tu Wishlist", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)

        book.setOnAddedToWishlistListener(this)
    }
}
