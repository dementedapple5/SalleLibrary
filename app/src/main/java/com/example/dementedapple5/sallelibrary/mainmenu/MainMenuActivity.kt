package com.example.dementedapple5.sallelibrary.mainmenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.models.Book
import com.example.dementedapple5.sallelibrary.models.BookShelf
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.book_shelf_item.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val mArrayColor: ArrayList<Int> = ArrayList()

        mArrayColor.add(R.color.material_blue_grey_800)
        mArrayColor.add(R.color.material_deep_teal_200)
        mArrayColor.add(R.color.material_grey_100)
        mArrayColor.add(R.color.colorPrimary)
        mArrayColor.add(R.color.colorAccent)
        mArrayColor.add(R.color.colorPrimaryDark)

        val mBookArray: ArrayList<Book> = ArrayList()
        val mArray: ArrayList<BookShelf> = ArrayList()


        mBookArray.add(Book("Harry Potter", 5.0f, mArrayColor[0]))
        mBookArray.add(Book("Lord of the Rings", 7.55f, mArrayColor[1]))
        mBookArray.add(Book("Game of Thrones", 6.99f, mArrayColor[2]))
        mBookArray.add(Book("Frankestain", 5.99f, mArrayColor[3]))
        mBookArray.add(Book("Avengers", 5.33f, mArrayColor[4]))
        mBookArray.add(Book("Superman", 8.44f, mArrayColor[5]))

        mArray.add(BookShelf("Sci-Fi", mBookArray))
        mArray.add(BookShelf("Adventure", mBookArray))
        mArray.add(BookShelf("Action", mBookArray))
        mArray.add(BookShelf("Suspense", mBookArray))
        mArray.add(BookShelf("Thriller", mBookArray))
        mArray.add(BookShelf("Recommended", mBookArray))




        myRecyclerView.setHasFixedSize(true)

        /*val mLayoutManager = GridLayoutManager( this, 2, GridLayoutManager.VERTICAL, false)


        mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                //stagger rows custom
                return if (position % 3 == 0) 2 else 1
            }
        }*/


        val mLayoutManager = LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false)

        myRecyclerView.layoutManager = mLayoutManager

        val mAdapter = BookShelfAdapter(mArray)

        myRecyclerView.adapter = mAdapter

    }




}
