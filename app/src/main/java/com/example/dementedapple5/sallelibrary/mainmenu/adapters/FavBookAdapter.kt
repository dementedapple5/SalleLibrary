package com.example.dementedapple5.sallelibrary.mainmenu.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.model.Book
import kotlinx.android.synthetic.main.fav_book_item.view.*

class FavBookAdapter(val mFavBooks: ArrayList<Book>): RecyclerView.Adapter<FavBookAdapter.Companion.MyFavBookViewHolder>() {
    companion object {
        class MyFavBookViewHolder(val mCardView: CardView): RecyclerView.ViewHolder(mCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyFavBookViewHolder {
        val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.fav_book_item, parent, false)
        return MyFavBookViewHolder(v as CardView)
    }

    override fun getItemCount(): Int {
        return mFavBooks.size
    }

    override fun onBindViewHolder(holder: MyFavBookViewHolder, position: Int) {
        holder.mCardView.book_cover.setImageResource(mFavBooks[position].img)
    }
}