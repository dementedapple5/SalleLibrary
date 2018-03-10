package com.example.dementedapple5.sallelibrary.mainmenu.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.FetchBook
import com.example.dementedapple5.sallelibrary.mainmenu.NetworkUtils
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.BookShelfAdapter
import com.example.dementedapple5.sallelibrary.model.Book
import com.example.dementedapple5.sallelibrary.model.BookShelf
import kotlinx.android.synthetic.main.fragment_main_page.*

class MainPageFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
/*
        val mArrayColor: ArrayList<Int> = ArrayList()

        mArrayColor.add(R.color.material_blue_grey_800)
        mArrayColor.add(R.color.material_deep_teal_200)
        mArrayColor.add(R.color.material_grey_100)
        mArrayColor.add(R.color.colorPrimary)
        mArrayColor.add(R.color.colorAccent)
        mArrayColor.add(R.color.colorPrimaryDark)*/

        FetchBook(recycler, activity).execute("flowers")

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }




}
