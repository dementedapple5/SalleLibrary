package com.example.dementedapple5.sallelibrary.mainmenu.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.model.BookShelf
import kotlinx.android.synthetic.main.book_shelf_item.view.*

/**
 * Created by dementedapple5 on 22/02/2018.
 */
class BookShelfAdapter(val mShelfArray: ArrayList<BookShelf>): RecyclerView.Adapter<BookShelfAdapter.Companion.MyViewHolder>() {

    companion object {
        class MyViewHolder(var mCardView: CardView) : RecyclerView.ViewHolder(mCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.book_shelf_item, parent, false)
        return MyViewHolder(v as CardView)
    }

    override fun getItemCount(): Int {
        return mShelfArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder!!.mCardView.mTextView.text = mShelfArray[position].title
        holder.mCardView.mBookRecycle.adapter = BookAdapter(mShelfArray[position].books)
        holder.mCardView.mBookRecycle.setHasFixedSize(true)
        holder.mCardView.mBookRecycle.layoutManager = LinearLayoutManager(holder.mCardView.context, LinearLayoutManager.HORIZONTAL, false)
    }


}