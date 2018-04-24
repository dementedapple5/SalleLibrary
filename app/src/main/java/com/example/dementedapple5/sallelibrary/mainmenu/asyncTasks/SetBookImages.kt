package com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView

/**
 * Tarea asíncrona para las imagenes de los libros.
 *
 * Esta clase consiste en realizar una tarea en background que se encargará de establecer las imagenes correspondientes a cada libro.
 *
 * @constructor Crea una instancia de [SetBookImages] con una serie de atributos
 * @property bookImg [ImageView] donde se mostrará la imagen resultante de la tarea.
 * @see [AsyncTask]
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class SetBookImages(val bookImg: ImageView) : AsyncTask<String, Void, Bitmap?>() {

    override fun doInBackground(vararg p0: String?): Bitmap? {
        val urldisplay = p0[0]

        var mIcon11: Bitmap? = null
        try {
            val inputStream = java.net.URL(urldisplay).openStream()
            mIcon11 = BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

        return mIcon11
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        bookImg.setImageBitmap(result)
    }
}