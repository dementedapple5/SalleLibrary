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

/**
 * Pantalla de detalles de un [Book].
 *
 * Se encarga de mostrar una pantalla con los detalles de un [Book] seleccionado por el usuario donde también puede añadirlo a favoritos.
 *
 * @see [BookLayout.OnAddedToWishlistListener]
 * @see [AppCompatActivity]
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class BookPage : AppCompatActivity(), BookLayout.OnAddedToWishlistListener {
    /**
     * Referencia al Authentication de Firebase para recoger datos del usuario.
     */
    private lateinit var mAuth: FirebaseAuth

    /**
     * Se utiliza para realizar operaciones de borrado o inserción en la [SharedPreference] del usuario.
     */
    var sharedPreference = SharedPreference()

    /**
     * Libro donde se mostrarán los detalles.
     */
    var book = Book()

    override fun onAddedToWishlist(source: BookLayout, textToDisplay: String) {
        if (checkIfBookIsInWishlist(book)) {
            sharedPreference.removeFromWishlist(this, book)
            mBookLayout.setWishlistButtonIcon(R.drawable.ic_action_add)
            mBookLayout.setButtonText(getString(R.string.add_to_wishlist))
            Snackbar.make(findViewById(R.id.coordinator), "${textToDisplay} ha sido eliminado de tu Wishlist", Snackbar.LENGTH_LONG).show()
        } else {
            sharedPreference.addToWishlist(this, book)
            mBookLayout.setWishlistButtonIcon(R.drawable.ic_done)
            mBookLayout.setButtonText("En tu Wishlist")
            Snackbar.make(findViewById(R.id.coordinator), "${textToDisplay} ha sido añadido a tu Wishlist", Snackbar.LENGTH_LONG).show()
        }
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

        updateUI(book)

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

    /**
     * Comprobación de si un [Book] está ya dentro de las [SharedPreference] del usuario.
     *
     * [book] Libro por el que se va a realizar la comprobración
     *
     * @return [Boolean] Resultado de la operación.
     */
    private fun checkIfBookIsInWishlist(book: Book): Boolean {
        val booksInWishlist: ArrayList<Book>? = sharedPreference.getBooksInWishlist(this)

        if (booksInWishlist != null) {
            for (item in booksInWishlist) {
                if (item.title.toLowerCase() == book.title.toLowerCase()) {
                    return true
                }
            }
        }

        return false
    }

    /**
     * Actualiza el estado del botón dependiendo del resultado que ha dado la comprobación del [Book] en la [SharedPreference].
     *
     * [book] Libro por el que se va a realizar la comprobración.
     */
    private fun updateUI(book: Book) {
        if (checkIfBookIsInWishlist(book)) {
            mBookLayout.setWishlistButtonIcon(R.drawable.ic_done)
            mBookLayout.setButtonText(getString(R.string.in_your_wishlist))
        } else {
            mBookLayout.setWishlistButtonIcon(R.drawable.ic_action_add)
            mBookLayout.setButtonText(getString(R.string.add_to_wishlist))
        }
    }
}
