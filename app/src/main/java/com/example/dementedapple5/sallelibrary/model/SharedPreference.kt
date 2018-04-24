package com.example.dementedapple5.sallelibrary.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

/**
 * Grupo de funcionalidades para las Shared Preferences.
 *
 * Esta clase consiste en manipular y persistir datos en las Shared Preferences del usuario.
 *
 * @constructor Crea una instancia vacía de SharedPreference.
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class SharedPreference() {
    companion object {
        /**
         * Variable estática para identificar las [SharedPreferences] a la hora de recuperarlas.
         */
        val PREFS_NAME = "Salle_Library"

        /**
         * Variable estática para identificar el objeto que se introduce en el [SharedPreferences.Editor].
         */
        val WISHLIST = "Book_Wishlist"
    }

    /**
     * Aplica los cambios en la [ArrayList] de [Book] en el [SharedPreferences.Editor].
     *
     * [context] Contexto de la actividad.
     * [booksInWishlist] [ArrayList] de [Book] con cambios.
     * @see [Gson]
     */
    fun persistentWishlist(context: Context, booksInWishlist: List<Book>) {
        val settings: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor

        editor = settings.edit()

        val gson = Gson()
        val jsonWishlist: String = gson.toJson(booksInWishlist)
        editor.putString(WISHLIST, jsonWishlist)

        editor.apply()
    }

    /**
     * Añade un [Book] a la [ArrayList].
     *
     * [context] Contexto de la actividad.
     * [book] Objeto [Book] a añadir.
     */
    fun addToWishlist(context: Context, book: Book) {
        var booksInWishlist: ArrayList<Book>? = getBooksInWishlist(context)

        if (booksInWishlist == null) {
            booksInWishlist = ArrayList<Book>()
            booksInWishlist.add(book)
            persistentWishlist(context, booksInWishlist)
        } else {
            booksInWishlist.add(book)
            persistentWishlist(context, booksInWishlist)
        }
    }

    /**
     * Elimina un [Book] de la [ArrayList].
     *
     * [context] Contexto de la actividad.
     * [book] Objeto [Book] a eliminar.
     */
    fun removeFromWishlist(context: Context, book: Book) {
        val booksInWishlist: ArrayList<Book>? = getBooksInWishlist(context)
        var counter = 0

        if (booksInWishlist != null) {
            for (item in booksInWishlist) {
                if (item.title == book.title) {
                    break
                }
                counter++
            }

            booksInWishlist.removeAt(counter)
            persistentWishlist(context, booksInWishlist)
        }
    }

    /**
     * Permite ver todos los [Book] almacenados en la [ArrayList].
     *
     * [context] Contexto de la actividad.
     * @return [ArrayList] de [Book]
     */
    fun getBooksInWishlist(context: Context): ArrayList<Book>? {
        val settings: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val booksInWishlist: List<Book>

        if (settings.contains(WISHLIST)) {
            val jsonFavorites = settings.getString(WISHLIST, null)
            val gson = Gson()
            val favoriteItems = gson.fromJson<Array<Book>>(jsonFavorites, Array<Book>::class.java)

            booksInWishlist = ArrayList<Book>(favoriteItems.asList())
        } else {
            return null
        }

        return booksInWishlist
    }
}