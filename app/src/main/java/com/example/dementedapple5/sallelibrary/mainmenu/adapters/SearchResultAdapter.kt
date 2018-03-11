package com.example.dementedapple5.sallelibrary.mainmenu.adapters

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.bookpage.activities.BookPage
import com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks.SetBookImages
import com.example.dementedapple5.sallelibrary.model.Book
import kotlinx.android.synthetic.main.book_item.view.*
import kotlinx.android.synthetic.main.search_result_item.view.*

/**
 * Created by dementedapple5 on 11/03/2018.
 */
class SearchResultAdapter(val mResults: ArrayList<Book>): RecyclerView.Adapter<SearchResultAdapter.Companion.MyResultViewHolder>() {


    companion object {
        class MyResultViewHolder(val mResultConst: ConstraintLayout): RecyclerView.ViewHolder(mResultConst)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyResultViewHolder {
        val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.search_result_item, parent, false)
        return MyResultViewHolder(v as ConstraintLayout)
    }

    override fun getItemCount(): Int {
        return mResults.size
    }

    override fun onBindViewHolder(holder: MyResultViewHolder, position: Int) {
        holder.mResultConst.mTitleTv.text = mResults[position].title
        holder.mResultConst.mAuthorTv.text = mResults[position].author
        SetBookImages(holder.mResultConst.mFrontPageIv).execute(mResults[position].img)

        holder.mResultConst.mFrontPageIv.setOnClickListener({
            val intent = Intent(holder.mResultConst.context, BookPage::class.java)
            val bundle = Bundle()
            bundle.putSerializable("bookData", mResults[position])
            intent.putExtras(bundle)
            holder.mResultConst.context.startActivity(intent)
        })

    }

}