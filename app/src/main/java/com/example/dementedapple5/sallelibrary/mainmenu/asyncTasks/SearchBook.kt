package com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.example.dementedapple5.sallelibrary.mainmenu.utils.NetworkUtils

/**
 * Tarea asíncrona para la búsqueda de libros.
 *
 * Esta clase consiste en realizar una tarea en background que se encargará de realizar una request a la API de Google Books cuando el usuario escriba un libro para buscar.
 *
 * @constructor Crea una instancia de [SearchBook] con una serie de atributos
 * @property context Contexto de la actividad.
 * @property queryString Query adjunta con los parámetros
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class SearchBook(context: Context, var queryString: String) : AsyncTaskLoader<String>(context) {

    /**
     * Devuelve un [String] que sale de la consulta realizada en [NetworkUtils] con la [queryString] obtenida.
     * @return [String]
     */
    override fun loadInBackground(): String? {
        return NetworkUtils.searchSingleBook(queryString)
    }

    /**
     * Fuerza la ejecución de la [AsyncTaskLoader]
     */
    override fun onStartLoading() {
        forceLoad()
    }
}