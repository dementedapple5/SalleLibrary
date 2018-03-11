package com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks

import android.content.Intent

import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import com.example.dementedapple5.sallelibrary.bookpage.activities.BookPage
import android.view.View
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.BookShelfAdapter
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.model.BookShelf
import org.json.JSONObject
import com.example.dementedapple5.sallelibrary.mainmenu.utils.NetworkUtils
import kotlinx.android.synthetic.main.book_shelf_item.view.*
import org.json.JSONException


/**
 * Created by dementedapple5 on 10/03/2018.
 */
class FetchBook(val recyclerView: RecyclerView, val activity: FragmentActivity, val bookCategories: ArrayList<String>) : AsyncTask<ArrayList<String>, Void, ArrayList<String>>(){


    override fun doInBackground(vararg p0: ArrayList<String>?): ArrayList<String> {
        return NetworkUtils.getBookInfo(p0[0]!!)!!
    }


    override fun onPostExecute(result: ArrayList<String>) {
        super.onPostExecute(result)

        var mBookArray: ArrayList<Book>
        val mArray: ArrayList<BookShelf> = ArrayList()
        var book: Book
        var counter = 0
        var img_counter = 0

        for (category in result) {
            val jsonObject = JSONObject(category)
            val booksArray = jsonObject.getJSONArray("items")
            mBookArray = ArrayList()

            for (i in 0 until booksArray.length()) {

                val bookJSON = booksArray.getJSONObject(i)

                var title: String = ""
                var publisher: String = ""
                var genre: String = ""
                var numPages: Int = 0
                var img: String = ""
                var author: String = ""
                var releaseDate: String = ""
                var description: String = ""

                val volumeInfo = bookJSON.getJSONObject("volumeInfo")

                try {

                    // Get title
                    if (volumeInfo.has("title")){
                        title = volumeInfo.getString("title")
                    }

                    // Get authors
                    if (volumeInfo.has("authors")){
                        val authors = volumeInfo.getJSONArray("authors")
                        author = authors.getString(0)
                    }

                    // Get imageURL
                    if (volumeInfo.has("imageLinks")) {
                        val imageLinks = volumeInfo.getJSONObject("imageLinks")
                        img = imageLinks.getString("thumbnail")
                    }

                    // Get numPages
                    if (volumeInfo.has("pageCount")){
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
                    if (volumeInfo.has("description")){
                        description = volumeInfo.getString("description")
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }catch (e1: JSONException) {
                    e1.printStackTrace()
                }

                Log.d("IMG_URL::", "$img_counter:: $img")

                book = Book(title, author, releaseDate, publisher, genre, numPages, description, img)

                mBookArray.add(book)

                img_counter++

            }

            mArray.add(BookShelf(bookCategories[counter], mBookArray))

            counter++
        }

        recyclerView.setHasFixedSize(true)

        val mLayoutManager = LinearLayoutManager(activity.applicationContext, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = mLayoutManager

        val mAdapter = BookShelfAdapter(mArray)

        recyclerView.adapter = mAdapter
    }




}