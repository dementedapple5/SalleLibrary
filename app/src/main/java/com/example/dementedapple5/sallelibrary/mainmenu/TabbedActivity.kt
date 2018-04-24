package com.example.dementedapple5.sallelibrary.mainmenu

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.SectionsPagerAdapter
import com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks.SearchBook
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.userauth.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_tabbed.*
import kotlinx.android.synthetic.main.tabs.*
import kotlinx.android.synthetic.main.toolbar.*
import org.json.JSONException
import org.json.JSONObject

/**
 * Pantalla principal de la aplicación.
 *
 * Se encarga de mostrar una pantalla con dos pestañas que corresponden a diferentes Fragments: en uno se muestra una selección de [Book] por géneros y en otro se muestra un listado de [Book] favoritos.
 *
 * @see [AppCompatActivity]
 * @see [LoaderManager]
 */
class TabbedActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    /**
     * Listado de [Book] que se muestran en la pantalla principal.
     */
    private var mBookArray: ArrayList<Book> = ArrayList<Book>()

    /**
     * Adaptador para cada sección del Fragment.
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    /**
     * Referencia al Authentication de Firebase para recoger datos del usuario.
     */
    private lateinit var mAuth: FirebaseAuth

    /**
     * Barra de búsqueda en la parte superior de la pantalla.
     */
    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)
        setSupportActionBar(toolbar)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter

        mAuth = FirebaseAuth.getInstance()

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        if (supportLoaderManager.getLoader<Any>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        mSearchView = searchItem?.actionView as SearchView
        mSearchView.queryHint = getString(R.string.search_title)

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val queryBundle = Bundle()
                queryBundle.putString("queryString", query)
                supportLoaderManager.initLoader(0, queryBundle, this@TabbedActivity)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_logout -> {
                confirmDialog()
                return true
            }

            R.id.action_search -> {
                circleReveal(R.id.toolbar, 1, true, true)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Se encarga de mostrar un [AlertDialog] de confirmación cuando el usuario presiona en el botón de cierre de sesión.
     */
    private fun confirmDialog() {
        AlertDialog.Builder(this)
                .setTitle(R.string.action_log_out)
                .setMessage(R.string.logout_confirmation)
                .setPositiveButton(R.string.logout_ok, DialogInterface.OnClickListener { _, _ ->
                    mAuth.signOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                })
                .setNegativeButton(R.string.logout_cancel, null).show()
    }

    /**
     * Se encarga de realizar una animación circular en la barra de búsqueda cuando el usuario presiona en el icono.
     *
     * [viewId] Identificador de la vista donde se quiere hacer la animación.
     * [startingPos] Posición inicial de la animación.
     * [hasOverflow] Indica si el icono se tapa durante la animación para cambiar los puntos de origen.
     * [isShow] Indica si la barra de búsqueda tras la animación es visible para realizar una animación de apertura o cierre.
     */
    private fun circleReveal(viewId: Int, startingPos: Int, hasOverflow: Boolean, isShow: Boolean) {
        val view = findViewById<View>(viewId)
        var width = view.width

        if (startingPos > 0) {
            width -= (startingPos * resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)) - (resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material))
        }

        if (hasOverflow) {
            width -= resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material)
        }

        val centerX = width
        val centerY = view.height / 2
        val anim: Animator

        if (isShow) {
            anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0f, width.toFloat())
        } else {
            anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, width.toFloat(), 0f)
        }

        anim.duration = 220L

        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (!isShow) {
                    super.onAnimationEnd(animation)
                    view.visibility = View.INVISIBLE
                }
            }
        })

        if (isShow) {
            view.visibility = View.VISIBLE
        }

        anim.start()
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        return SearchBook(this, args!!.getString("queryString"))
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        mBookArray.clear()
        var book: Book

        val jsonObject = JSONObject(data)
        val booksArray = jsonObject.getJSONArray("items")

        for (i in 0 until booksArray.length()) {

            val bookJSON = booksArray.getJSONObject(i)

            var title = ""
            var publisher = ""
            var genre = ""
            var numPages = 0
            var img = ""
            var author = ""
            var releaseDate = ""
            var description = ""

            val volumeInfo = bookJSON.getJSONObject("volumeInfo")

            try {
                // Get title
                if (volumeInfo.has("title")) {
                    title = volumeInfo.getString("title")
                }

                // Get authors
                if (volumeInfo.has("authors")) {
                    val authors = volumeInfo.getJSONArray("authors")
                    author = authors.getString(0)
                }

                // Get imageURL
                if (volumeInfo.has("imageLinks")) {
                    val imageLinks = volumeInfo.getJSONObject("imageLinks")
                    img = imageLinks.getString("thumbnail")
                }

                // Get numPages
                if (volumeInfo.has("pageCount")) {
                    numPages = volumeInfo.getInt("pageCount")
                }

                // Get year of release
                if (volumeInfo.has("publishedDate")) {
                    releaseDate = volumeInfo.getString("publishedDate")
                }

                // Get publisher
                if (volumeInfo.has("publisher")) {
                    publisher = volumeInfo.getString("publisher")
                }

                // Get genre
                if (volumeInfo.has("categories")) {
                    val genres = volumeInfo.getJSONArray("categories")
                    genre = genres.getString(0)
                }

                // Get Description
                if (volumeInfo.has("description")) {
                    description = volumeInfo.getString("description")
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } catch (e1: JSONException) {
                e1.printStackTrace()
            }

            book = Book(title, author, releaseDate, publisher, genre, numPages, description, img)

            mBookArray.add(book)
        }

        val intent = Intent(this, BooksFromSearchActivity::class.java)
        val bundle = Bundle()

        bundle.putSerializable("mResultArray", mBookArray)
        bundle.putString("query", mSearchView.query.toString())

        intent.putExtras(bundle)
        startActivity(intent)

        supportLoaderManager.destroyLoader(0)
    }

    override fun onLoaderReset(loader: Loader<String>) {}

}
