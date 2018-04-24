package com.example.dementedapple5.sallelibrary.mainmenu.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.dementedapple5.sallelibrary.mainmenu.fragments.PlaceHolderFragment

/**
 * Adaptador para [PlaceHolderFragment].
 *
 * Esta clase consiste en generar un Adaptador para poder generar la instancia de un [Fragment] dependiendo de la sección actual.
 *
 * @constructor Crea una instancia de [SectionsPagerAdapter] con una serie de atributos
 * @property fm Interfaz que permite la interacción de los [Fragment] con la Activity que los corresponda.
 *
 * @see [MyResultViewHolder]
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return PlaceHolderFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return 2
    }
}