package com.example.dementedapple5.sallelibrary.mainmenu.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.BookShelfAdapter
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.FavBookAdapter
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.model.BookShelf
import com.example.dementedapple5.sallelibrary.model.SharedPreference
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.android.synthetic.main.fragment_wishlist.*

class WishlistFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser) {
            val sharedPreference = SharedPreference()
            val booksInWishlist: ArrayList<Book>? = sharedPreference.getBooksInWishlist(activity)

            if (booksInWishlist == null) {
                val noBooksMessage = Snackbar.make(activity.findViewById(R.id.gridlayout), "Aún no tienes ningún libro añadido a tu Wishlist", Snackbar.LENGTH_INDEFINITE)

                noBooksMessage.setAction(R.string.snackbar_ignore, object: View.OnClickListener {
                    override fun onClick(view: View?) {
                        noBooksMessage.dismiss()
                    }
                })

                noBooksMessage.show()
            } else {
                val mAdapter = FavBookAdapter(booksInWishlist)
                wishlist_recycler.adapter = mAdapter
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        wishlist_recycler.setHasFixedSize(true)

        val mLayoutManager = GridLayoutManager(activity.applicationContext, 2, GridLayoutManager.VERTICAL, false)
        wishlist_recycler.layoutManager = mLayoutManager
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }
}
