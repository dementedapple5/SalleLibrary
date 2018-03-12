package com.example.dementedapple5.sallelibrary.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

class SharedPreference() {
    companion object {
        val PREFS_NAME = "Salle_Library"
        val WISHLIST = "Book_Wishlist"
    }

    fun persistentWishlist(context: Context, booksInWishlist: List<Book>) {
        val settings: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor

        editor = settings.edit()

        val gson = Gson()
        val jsonWishlist: String = gson.toJson(booksInWishlist)

        Log.d("EL OTRO ARRAY", booksInWishlist.count().toString())
        editor.putString(WISHLIST, jsonWishlist)

        editor.apply()
    }

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

    fun removeFromWishlist(context: Context, book: Book) {
        val booksInWishlist: ArrayList<Book>? = getBooksInWishlist(context)
        var counter = 0

        if (booksInWishlist != null) {
            for(item in booksInWishlist) {
                if (item.title == book.title) {
                    break
                }
                counter++
            }
        }

        booksInWishlist?.removeAt(counter)
        persistentWishlist(context, booksInWishlist!!)
    }

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