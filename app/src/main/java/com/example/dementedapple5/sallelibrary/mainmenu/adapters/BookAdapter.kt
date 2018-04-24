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

/**
 * Adaptador para [Book].
 *
 * Esta clase consiste en generar un Adaptador para poder mostrar los datos en pantalla de un [ArrayList] de [Book].
 *
 * @constructor Crea una instancia de [BookAdapter] con una serie de atributos
 * @property mBooks [ArrayList] de [Book].
 *
 * @see [MyBookViewHolder]
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class BookAdapter(val mBooks: ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.Companion.MyBookViewHolder>() {

    companion object {
        /**
         * Intermediario entre los datos recogidos y la vista.
         *
         * Esta clase consiste en mantener los datos en la vista sin tener que recargarlos y perder rendimiento en la renderización.
         *
         * @property mConstraint Tipo de layout utilizado en la vista.
         */
        class MyBookViewHolder(val mConstraint: ConstraintLayout) : RecyclerView.ViewHolder(mConstraint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return MyBookViewHolder(v as ConstraintLayout)
    }

    override fun getItemCount(): Int {
        return mBooks.size
    }

    override fun onBindViewHolder(holder: MyBookViewHolder, position: Int) {
        holder.mConstraint.mBookTitle.text = mBooks[position].title
        holder.mConstraint.mBookPrice.text = mBooks[position].author
        SetBookImages(holder.mConstraint.mBookImg).execute(mBooks[position].img)

        holder.mConstraint.mBookImg.setOnClickListener({
            val intent = Intent(holder.mConstraint.context, BookPage::class.java)
            val bundle = Bundle()
            bundle.putSerializable("bookData", mBooks[position])
            intent.putExtras(bundle)
            holder.mConstraint.context.startActivity(intent)
        })
    }

}