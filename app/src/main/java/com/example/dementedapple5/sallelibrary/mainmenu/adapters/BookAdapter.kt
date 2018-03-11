package com.example.dementedapple5.sallelibrary.mainmenu.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks.SetBookImages
import com.example.dementedapple5.sallelibrary.model.Book
import kotlinx.android.synthetic.main.book_item.view.*


/**
 * Created by dementedapple5 on 22/02/2018.
 */
class BookAdapter(val mBooks: ArrayList<Book>): RecyclerView.Adapter<BookAdapter.Companion.MyBookViewHolder>() {

    companion object {
        class MyBookViewHolder(val mConstraint: ConstraintLayout): RecyclerView.ViewHolder(mConstraint)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyBookViewHolder {
        val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.book_item, parent, false)
        return MyBookViewHolder(v as ConstraintLayout)
    }

    override fun getItemCount(): Int {
        return mBooks.size
    }

    override fun onBindViewHolder(holder: MyBookViewHolder, position: Int) {
        holder.mConstraint.mBookTitle.text = mBooks[position].title
        holder.mConstraint.mBookPrice.text = mBooks[position].author
        SetBookImages(holder.mConstraint.mBookImg).execute(mBooks[position].img)
    }

}