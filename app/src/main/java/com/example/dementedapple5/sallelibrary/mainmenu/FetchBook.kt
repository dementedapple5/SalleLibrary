package com.example.dementedapple5.sallelibrary.mainmenu

import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.BookShelfAdapter
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.model.BookShelf
import org.json.JSONObject

/**
 * Created by dementedapple5 on 10/03/2018.
 */
class FetchBook(val recyclerView: RecyclerView, val activity: FragmentActivity): AsyncTask<String, Void, String>() {


    override fun doInBackground(vararg p0: String?): String {
        return NetworkUtils.getBookInfo(p0[0]!!)!!
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        val jsonObject = JSONObject(result)
        val booksArray = jsonObject.getJSONArray("items")

        val mBookArray: ArrayList<Book> = ArrayList()
        val mArray: ArrayList<BookShelf> = ArrayList()
        var book: Book

        for (i in 0 until booksArray.length()) {
            val bookJSON = booksArray.getJSONObject(i)

            var title: String = ""
            var author: String = ""

            val volumeInfo = bookJSON.getJSONObject("volumeInfo")

            try {
                title = volumeInfo.getString("title")
                author = volumeInfo.getString("authors")
            }catch (e: Exception){
                e.printStackTrace()
            }

            book = Book(title, author)

            mBookArray.add(book)

        }

        mArray.add(BookShelf("Ficcion", mBookArray))
        mArray.add(BookShelf("Aventura", mBookArray))
        mArray.add(BookShelf("Accion", mBookArray))
        mArray.add(BookShelf("Suspenso", mBookArray))
        mArray.add(BookShelf("Thriller", mBookArray))


        recyclerView.setHasFixedSize(true)

        val mLayoutManager = LinearLayoutManager (activity.applicationContext, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = mLayoutManager

        val mAdapter = BookShelfAdapter(mArray)

        recyclerView.adapter = mAdapter
    }

}