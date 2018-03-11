package com.example.dementedapple5.sallelibrary.bookpage.activities

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.booklayout.BookLayout
import com.example.dementedapple5.sallelibrary.R
import kotlinx.android.synthetic.main.activity_book_page.*
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks.SetBookImages
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.userauth.activities.LoginActivity
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

        val author: String = intent.getBundleExtra("bookData").getString("author")
        val title: String = intent.getBundleExtra("bookData").getString("title")
        val img: String = intent.getBundleExtra("bookData").getString("img")
        val releaseDate: String = intent.getBundleExtra("bookData").getString("releaseDate")
        val numPages: String = intent.getBundleExtra("bookData").getString("numPages")
        val description: String = intent.getBundleExtra("bookData").getString("description")
        val genre: String = intent.getBundleExtra("bookData").getString("genre")
        val publisher: String = intent.getBundleExtra("bookData").getString("publisher")

        book.setBookGenre(genre)
        book.setBookAuthor(author)
        book.setBookDate(releaseDate)
        book.setBookPages(numPages)
        book.setBookDescription(description)
        book.setBookTitle(title)
        book.setBookPublisher(publisher)

        SetBookImages(book.getImageOfBook()).execute(img)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.only_settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
