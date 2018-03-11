package com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.example.dementedapple5.sallelibrary.mainmenu.utils.NetworkUtils

class SearchBook(context: Context, var queryString: String): AsyncTaskLoader<String>(context) {

    override fun loadInBackground(): String {
        TODO()
    }

    override fun onStartLoading() {
        super.onStartLoading()
    }
}