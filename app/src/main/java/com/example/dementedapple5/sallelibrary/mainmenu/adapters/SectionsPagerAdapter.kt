package com.example.dementedapple5.sallelibrary.mainmenu.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.dementedapple5.sallelibrary.mainmenu.fragments.PlaceHolderFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return PlaceHolderFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return 2
    }
}