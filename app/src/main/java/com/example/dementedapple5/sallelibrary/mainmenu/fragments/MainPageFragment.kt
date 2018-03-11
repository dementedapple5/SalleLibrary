package com.example.dementedapple5.sallelibrary.mainmenu.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks.FetchBook
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

        val bookCategories = ArrayList<String>()
        bookCategories.add("Action")
        bookCategories.add("Adventure")
        bookCategories.add("Fiction")
        bookCategories.add("Thriller")
        bookCategories.add("Comedy")

        FetchBook(recycler, activity, bookCategories).execute(bookCategories)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }




}
