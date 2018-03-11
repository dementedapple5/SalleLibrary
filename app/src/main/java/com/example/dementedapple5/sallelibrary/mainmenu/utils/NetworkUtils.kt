package com.example.dementedapple5.sallelibrary.mainmenu.utils

import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by dementedapple5 on 10/03/2018.
 */
class NetworkUtils {

    companion object {
        val BASE_URL = "https://www.googleapis.com/books/v1/volumes?"
        val QUERY_PARAM = "q"
        val MAX_RESULTS = "maxResults"
        val PRINT_TYPE = "printType"

        fun getBookInfo(queryArray: ArrayList<String>): ArrayList<String>? {
            var urlConnection: HttpURLConnection
            var reader: BufferedReader
            var bookJSONString = ""
            val categoriesArray: ArrayList<String> = ArrayList()

            for (queryString in queryArray) {
                try {
                    val requestUri = Uri.parse(BASE_URL).buildUpon()
                            .appendQueryParameter(QUERY_PARAM, "+subject:$queryString")
                            .appendQueryParameter(MAX_RESULTS, "20")
                            .appendQueryParameter(PRINT_TYPE, "books")
                            .build()

                    val requestUrl = URL(requestUri.toString())

                    Log.d("URLCONN:", requestUrl.toString())

                    urlConnection = requestUrl.openConnection() as HttpURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.connect()

                    val inputStream = urlConnection.inputStream

                    val buffer = StringBuffer()


                    if (inputStream == null) {
                        return null
                    }

                    reader = BufferedReader(InputStreamReader(inputStream))

                    val jsonArr = reader.readLines()

                    for (elem in jsonArr) {
                        buffer.append("$elem\n")
                    }

                    bookJSONString = buffer.toString()

                    urlConnection.disconnect()
                    reader.close()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                Log.d("BOOKJSON:", bookJSONString)

                categoriesArray.add(bookJSONString)
            }


            return categoriesArray
        }

    }


}