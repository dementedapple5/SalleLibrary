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
import kotlinx.android.synthetic.main.search_result_item.view.*

/**
 * Adaptador para [Book].
 *
 * Esta clase consiste en generar un Adaptador para poder mostrar los datos en pantalla de un [ArrayList] de [Book] resultantes tras una búsqueda realizada por el usuario.
 *
 * @constructor Crea una instancia de [SearchResultAdapter] con una serie de atributos
 * @property mResults [ArrayList] de [Book].
 *
 * @see [MyResultViewHolder]
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class SearchResultAdapter(val mResults: ArrayList<Book>) : RecyclerView.Adapter<SearchResultAdapter.Companion.MyResultViewHolder>() {

    companion object {
        /**
         * Intermediario entre los datos recogidos y la vista.
         *
         * Esta clase consiste en mantener los datos en la vista sin tener que recargarlos y perder rendimiento en la renderización.
         *
         * @property mResultConst Tipo de layout utilizado en la vista.
         */
        class MyResultViewHolder(val mResultConst: ConstraintLayout) : RecyclerView.ViewHolder(mResultConst)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyResultViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
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