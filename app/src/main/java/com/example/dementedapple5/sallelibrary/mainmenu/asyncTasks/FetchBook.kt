package com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks

import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.BookShelfAdapter
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.model.BookShelf
import org.json.JSONObject
import com.example.dementedapple5.sallelibrary.mainmenu.utils.NetworkUtils


/**
 * Created by dementedapple5 on 10/03/2018.
 */
class FetchBook(val recyclerView: RecyclerView, val activity: FragmentActivity, val bookCategories: ArrayList<String>) : AsyncTask<ArrayList<String>, Void, ArrayList<String>>() {

    override fun doInBackground(vararg p0: ArrayList<String>?): ArrayList<String> {
        return NetworkUtils.getBookInfo(p0[0]!!)!!
    }


    override fun onPostExecute(result: ArrayList<String>) {
        super.onPostExecute(result)

        var mBookArray: ArrayList<Book>
        val mArray: ArrayList<BookShelf> = ArrayList()
        var book: Book
        var counter = 0

        for (category in result) {
            val jsonObject = JSONObject(category)
            val booksArray = jsonObject.getJSONArray("items")
            mBookArray = ArrayList()

            for (i in 0 until booksArray.length()) {

                Log.d("BOOKAR:", booksArray.length().toString())

                val bookJSON = booksArray.getJSONObject(i)

                var title = ""
                var author = ""
                var img = ""

                val volumeInfo = bookJSON.getJSONObject("volumeInfo")

                try {
                    title = volumeInfo.getString("title")
                    author = volumeInfo.getString("authors")

                    val imageLinks = volumeInfo.getJSONObject("imageLinks")

                    img = imageLinks.getString("thumbnail")

                } catch (e: Exception) {
                    e.printStackTrace()
                }

                book = Book(title, author, img)

                mBookArray.add(book)

            }

            Log.d("BOOKARLEN:", mBookArray.size.toString())

            mArray.add(BookShelf(bookCategories[counter], mBookArray))

            Log.d("ARLEN:", mArray.size.toString())



            counter++
        }

        Log.d("ARLEN:", mArray.size.toString())

        recyclerView.setHasFixedSize(true)

        val mLayoutManager = LinearLayoutManager(activity.applicationContext, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = mLayoutManager

        val mAdapter = BookShelfAdapter(mArray)

        recyclerView.adapter = mAdapter
    }






}