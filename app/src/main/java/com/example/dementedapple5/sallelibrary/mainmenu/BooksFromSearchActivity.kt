package com.example.dementedapple5.sallelibrary.mainmenu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.SearchResultAdapter
import com.example.dementedapple5.sallelibrary.model.Book
import kotlinx.android.synthetic.main.activity_books_from_search.*

class BooksFromSearchActivity : AppCompatActivity() {

    var books: ArrayList<Book> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_from_search)
        setSupportActionBar(result_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val bundleArray = intent.extras
        books = bundleArray.getSerializable("mResultArray") as ArrayList<Book>

        result_toolbar.title = "Libros de ${bundleArray.getString("query")}"

        mRecyclerBookSearchResult.setHasFixedSize(true)

        val mLayoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        mRecyclerBookSearchResult.layoutManager = mLayoutManager

        val mAdapter = SearchResultAdapter(books)

        mRecyclerBookSearchResult.adapter = mAdapter

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
