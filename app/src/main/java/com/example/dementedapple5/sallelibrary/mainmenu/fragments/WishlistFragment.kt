package com.example.dementedapple5.sallelibrary.mainmenu.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.BookShelfAdapter
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.FavBookAdapter
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.model.BookShelf
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.android.synthetic.main.fragment_wishlist.*

class WishlistFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mArrayColor: ArrayList<Int> = ArrayList()

        mArrayColor.add(R.color.material_blue_grey_800)
        mArrayColor.add(R.color.material_deep_teal_200)
        mArrayColor.add(R.color.material_grey_100)
        mArrayColor.add(R.color.colorPrimary)
        mArrayColor.add(R.color.colorAccent)
        mArrayColor.add(R.color.colorPrimaryDark)

        val mFavBookArray = ArrayList<Book>()

//        mFavBookArray.add(Book("Harry Potter", 5.0f, mArrayColor[0]))
//        mFavBookArray.add(Book("Lord of the Rings", 7.55f, mArrayColor[1]))
//        mFavBookArray.add(Book("Game of Thrones", 6.99f, mArrayColor[2]))
//        mFavBookArray.add(Book("Frankestain", 5.99f, mArrayColor[3]))
//        mFavBookArray.add(Book("Avengers", 5.33f, mArrayColor[4]))
//        mFavBookArray.add(Book("Superman", 8.44f, mArrayColor[5]))

        wishlist_recycler.setHasFixedSize(true)

        val mLayoutManager = GridLayoutManager(activity.applicationContext, 2, GridLayoutManager.VERTICAL, false)
        wishlist_recycler.layoutManager = mLayoutManager

        val mAdapter = FavBookAdapter(mFavBookArray)
        wishlist_recycler.adapter = mAdapter

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }
}
