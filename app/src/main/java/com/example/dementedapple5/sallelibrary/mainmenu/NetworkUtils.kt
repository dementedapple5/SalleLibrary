package com.example.dementedapple5.sallelibrary.mainmenu

import android.net.Uri
import android.util.Log
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.example.dementedapple5.sallelibrary.model.Book
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

/**
 * Created by dementedapple5 on 10/03/2018.
 */
class NetworkUtils {

    companion object {
        val LOG_TAG = NetworkUtils.javaClass.simpleName
        val BASE_URL = "https://www.googleapis.com/books/v1/volumes?"
        val QUERY_PARAM = "q"
        val MAX_RESULTS = "maxResults"
        val PRINT_TYPE = "printType"

        fun getBookInfo(queryString: String): String? {
            val urlConnection: HttpURLConnection
            val reader: BufferedReader
            var bookJSONString = ""

            try {
                val requestUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, queryString)
                        .appendQueryParameter(MAX_RESULTS, "4")
                        .appendQueryParameter(PRINT_TYPE, "books")
                        .build()

                val requestUrl = URL(requestUri.toString())

                Log.d("URLCONN:", requestUrl.toString())

                urlConnection = requestUrl.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                Log.d("URLCONN:", requestUrl.toString())
                urlConnection.connect()

                val inputStream = urlConnection.inputStream

                val buffer = StringBuffer()


                if (inputStream == null) {
                    return null
                }

                reader = BufferedReader(InputStreamReader(inputStream))

                val jsonArr = reader.readLines()

                Log.d("BOOKJSON:", bookJSONString)

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
            return bookJSONString
        }

    }


}