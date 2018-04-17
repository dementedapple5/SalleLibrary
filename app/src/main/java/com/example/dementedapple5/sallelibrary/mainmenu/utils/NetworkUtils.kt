package com.example.dementedapple5.sallelibrary.mainmenu.utils

import android.net.Uri
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Grupo de funcionalidades para las requests a la API.
 *
 * Esta clase consiste dispone de una serie de funcionalidades para realizar requests a la API de Google Books.
 *
 */
class NetworkUtils {
    companion object {
        /**
         * URL por defecto que se utilizará como raíz para las requests con parámetros.
         */
        val BASE_URL = "https://www.googleapis.com/books/v1/volumes?"
        /**
         * Tipo de parámetro para realizar la query sobre una categoría.
         */
        val QUERY_PARAM = "q"
        /**
         * Define la cantidad de resultados que debe retornar la query.
         */
        val MAX_RESULTS = "maxResults"
        /**
         * Tipo de resultado que debe retornar la query.
         */
        val PRINT_TYPE = "printType"

        /**
         * Devuelve un [ArrayList] con los datos sobre los libros que cumplen los parámetros de la request.
         *
         * @return [ArrayList]
         * @see [HttpURLConnection]
         */
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

                categoriesArray.add(bookJSONString)
            }


            return categoriesArray
        }

        /**
         * Devuelve un [String] con los datos sobre un libro en concreto que cumple los parámetros de la request.
         *
         * @return [String]
         * @see [HttpURLConnection]
         */
        fun searchSingleBook(queryString: String): String? {
            var urlConnection: HttpURLConnection? = null
            var reader: BufferedReader? = null
            var bookJSONString = ""

            try {
                val builtUri: Uri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, queryString)
                        .appendQueryParameter(MAX_RESULTS, "10")
                        .appendQueryParameter(PRINT_TYPE, "books")
                        .build()

                val requestUrl = URL(builtUri.toString())

                urlConnection = requestUrl.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.connect()

                val inputStream: InputStream? = urlConnection.inputStream
                val buffer = StringBuffer()

                if (inputStream == null) {
                    return null
                }

                reader = BufferedReader(InputStreamReader(inputStream))

                val jsonArr = reader.readLines()

                for (elem in jsonArr) {
                    buffer.append("$elem\n")
                }

                if (buffer.isEmpty()) {
                    return null
                }

                bookJSONString = buffer.toString()
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect()
                }

                if (reader != null) {
                    try {
                        reader.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            return bookJSONString
        }

    }
}