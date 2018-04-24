package com.example.dementedapple5.sallelibrary.mainmenu.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dementedapple5.sallelibrary.R

/**
 * Marcador de posición de los [Fragment] "hijos".
 *
 * Se encarga de generar y mostrar un [Fragment] u otro dependiendo de la sección actual.
 *
 * @constructor Crea una instancia de [PlaceHolderFragment]
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class PlaceHolderFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tabbed, container, false)
    }

    companion object {
        /**
         * Se encarga de controlar el tipo de [Fragment] a instanciar.
         *
         * [sectionNumber] Sección por la que depende el tipo de [Fragment] a instanciar.
         *
         * @see [MainPageFragment]
         * @see [WishlistFragment]
         *
         * @return [Fragment] que se debe mostrar en la sección indicada.
         */
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