package com.example.dementedapple5.sallelibrary.userauth.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.example.dementedapple5.sallelibrary.userauth.fragments.PlaceHolderFragment

class SectionsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return PlaceHolderFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return 2
    }
}