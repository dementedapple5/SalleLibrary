package com.example.dementedapple5.sallelibrary.mainmenu.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dementedapple5.sallelibrary.R

class PlaceHolderFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tabbed, container, false)
    }

    companion object {
        fun newInstance(sectionNumber: Int): Fragment {
            lateinit var fragment: Fragment

            when (sectionNumber) {
                1 -> fragment = MainPageFragment()
                2 -> fragment = WishlistFragment()
            }

            return fragment
        }
    }
}