package com.example.dementedapple5.sallelibrary.bookpage.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
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
    lateinit var bookObject: Book

    override fun onAddedToWishlist(source: BookLayout, textToDisplay: String) {
        book.setWishlistButtonIcon(R.drawable.ic_done)
        book.setButtonText("En tu Wishlist")

        sharedPreference.addToWishlist(this, bookObject)

        Snackbar.make(findViewById(R.id.coordinator), "${textToDisplay} ha sido añadido a tu Wishlist", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)
        setSupportActionBar(book_toolbar)

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

        bookObject = Book(title, author, releaseDate, publisher, genre, numPages.toInt(), description, img)

        book_toolbar.title = title
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
