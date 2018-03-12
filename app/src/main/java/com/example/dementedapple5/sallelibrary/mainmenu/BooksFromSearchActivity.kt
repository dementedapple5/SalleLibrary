package com.example.dementedapple5.sallelibrary.mainmenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.SearchResultAdapter
import com.example.dementedapple5.sallelibrary.model.Book
import kotlinx.android.synthetic.main.activity_book_page.*
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

        val mLayoutManager = GridLayoutManager(this, 3,GridLayoutManager.VERTICAL, false)

        mRecyclerBookSearchResult.layoutManager = mLayoutManager

        val mAdapter = SearchResultAdapter(books)

        mRecyclerBookSearchResult.adapter = mAdapter

    }
}
