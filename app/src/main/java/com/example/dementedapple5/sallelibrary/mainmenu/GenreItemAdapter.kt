package com.example.dementedapple5.sallelibrary.mainmenu

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by dementedapple5 on 21/02/2018.
 */
class GenreItemAdapter(val genres: ArrayList<Genre>): Adapter<GenreItemAdapter.Companion.ViewHolder>() {


    companion object {
        class ViewHolder: RecyclerView.ViewHolder{
            lateinit var cardView: CardView

            constructor(mGenreCard: CardView) : super(mGenreCard){
                cardView = mGenreCard
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}