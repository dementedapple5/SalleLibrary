package com.example.dementedapple5.sallelibrary.mainmenu.asyncTasks

import android.graphics.Bitmap
import android.os.AsyncTask
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView


/**
 * Created by dementedapple5 on 11/03/2018.
 */
class SetBookImages(val bookImg: ImageView) : AsyncTask<String, Void, Bitmap?>(){

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