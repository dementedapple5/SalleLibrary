package com.example.dementedapple5.sallelibrary.mainmenu.fragments

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.FavBookAdapter
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.model.SharedPreference
import kotlinx.android.synthetic.main.fragment_wishlist.*

/**
 * Muestra de los [Book] favoritos.
 *
 * Se encarga de mostrar un [Fragment] donde se puede visualizar un listado de [Book] que el usuario ha seleccionado como favoritos.
 *
 * @see [SharedPreference]
 */
class WishlistFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser) {
            val sharedPreference = SharedPreference()
            val booksInWishlist: ArrayList<Book>? = sharedPreference.getBooksInWishlist(activity!!)

            if (booksInWishlist == null || booksInWishlist.count() == 0) {
                val noBooksMessage = Snackbar.make(activity!!.findViewById(R.id.gridlayout), "Aún no tienes ningún libro añadido a tu Wishlist", Snackbar.LENGTH_INDEFINITE)

                noBooksMessage.setAction(R.string.snackbar_ignore, object : View.OnClickListener {
                    override fun onClick(view: View?) {
                        noBooksMessage.dismiss()
                    }
                })

                noBooksMessage.show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPreference = SharedPreference()
        val booksInWishlist: ArrayList<Book>? = sharedPreference.getBooksInWishlist(activity!!)

        if (booksInWishlist != null) {
            val mAdapter = FavBookAdapter(booksInWishlist)
            wishlist_recycler.adapter = mAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        wishlist_recycler.setHasFixedSize(true)

        val mLayoutManager = GridLayoutManager(activity!!.applicationContext, 3, GridLayoutManager.VERTICAL, false)
        wishlist_recycler.layoutManager = mLayoutManager
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }
}
