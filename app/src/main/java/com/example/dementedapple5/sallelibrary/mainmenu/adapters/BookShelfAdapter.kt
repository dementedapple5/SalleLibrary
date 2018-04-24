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
 * Adaptador para [BookShelf].
 *
 * Esta clase consiste en generar un Adaptador para poder mostrar los datos en pantalla de un [ArrayList] de [BookShelf].
 *
 * @constructor Crea una instancia de [BookShelfAdapter] con una serie de atributos
 * @property mShelfArray [ArrayList] de [BookShelf].
 *
 * @see [MyViewHolder]
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class BookShelfAdapter(val mShelfArray: ArrayList<BookShelf>) : RecyclerView.Adapter<BookShelfAdapter.Companion.MyViewHolder>() {

    companion object {
        /**
         * Intermediario entre los datos recogidos y la vista.
         *
         * Esta clase consiste en mantener los datos en la vista sin tener que recargarlos y perder rendimiento en la renderización.
         *
         * @property mCardView Tipo de layout utilizado en la vista.
         */
        class MyViewHolder(var mCardView: CardView) : RecyclerView.ViewHolder(mCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.book_shelf_item, parent, false)
        return MyViewHolder(v as CardView)
    }

    override fun getItemCount(): Int {
        return mShelfArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mCardView.mTextView.text = mShelfArray[position].title
        holder.mCardView.mBookRecycle.adapter = BookAdapter(mShelfArray[position].books)
        holder.mCardView.mBookRecycle.setHasFixedSize(true)
        holder.mCardView.mBookRecycle.setItemViewCacheSize(20)
        holder.mCardView.mBookRecycle.isDrawingCacheEnabled = true
        holder.mCardView.mBookRecycle.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        holder.mCardView.mBookRecycle.layoutManager = LinearLayoutManager(holder.mCardView.context, LinearLayoutManager.HORIZONTAL, false)

    }


}