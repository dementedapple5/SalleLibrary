package com.example.dementedapple5.sallelibrary.bookpage.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.example.booklayout.BookLayout
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks.SetBookImages
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.model.SharedPreference
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_book_page.*


class BookPage : AppCompatActivity(), BookLayout.OnAddedToWishlistListener {
    private lateinit var mAuth: FirebaseAuth
    var sharedPreference = SharedPreference()
    var booksInWishlist: ArrayList<Book> = ArrayList<Book>()
    var book = Book()

    override fun onAddedToWishlist(source: BookLayout, textToDisplay: String) {
        mBookLayout.setWishlistButtonIcon(R.drawable.ic_done)
        mBookLayout.setButtonText("En tu Wishlist")

        sharedPreference.addToWishlist(this, book)

        Snackbar.make(findViewById(R.id.coordinator), "${textToDisplay} ha sido añadido a tu Wishlist", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)
        setSupportActionBar(book_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mAuth = FirebaseAuth.getInstance()

        mBookLayout.setOnAddedToWishlistListener(this)

        val bundleBook = intent.extras
        book = bundleBook.getSerializable("bookData") as Book

        book_toolbar.title = book.title
        mBookLayout.setBookGenre(book.genre)
        mBookLayout.setBookAuthor(book.author)
        mBookLayout.setBookDate(book.releaseDate)
        mBookLayout.setBookPages(book.numPages.toString())
        mBookLayout.setBookDescription(book.description)
        mBookLayout.setBookTitle(book.title)
        mBookLayout.setBookPublisher(book.publisher)

        SetBookImages(mBookLayout.getImageOfBook()).execute(book.img)

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
