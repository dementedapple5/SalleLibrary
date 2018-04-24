package com.example.booklayout

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.booklayout.view.*

/**
 * Representación del componente visual de los detalles de un libro.
 *
 * Esta clase consiste en definir un layout personalizado como componente visual que se utiliza para mostrar los detalles de un libro en la aplicación.
 *
 * @see View.OnClickListener
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
class BookLayout : ConstraintLayout, View.OnClickListener {
    /**
     * Retrollamada que activa una acción cuando finaliza el proceso de añadir a favoritos.
     */
    private lateinit var mCallback: OnAddedToWishlistListener

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_add_to_wishlist -> {
                mCallback.onAddedToWishlist(this, book_title.text.toString())
            }
        }
    }

    /**
     * Interfaz que está pendiente de qué libros están añadidos a favoritos.
     */
    interface OnAddedToWishlistListener {
        /**
         *  Detecta cuando el usuario ha añadido un libro a favoritos para realizar una acción.
         *
         *  [source] Layout actual.
         *  [textToDisplay] Texto que se mostrará al añadir a favoritos.
         */
        fun onAddedToWishlist(source: BookLayout, textToDisplay: String)
    }

    /**
     * Establecimiento del [mCallback] como listener de la adición a favoritos.
     *
     * [listener] Interfaz pendiente de qué libros están añadidos a favoritos.
     */
    fun setOnAddedToWishlistListener(listener: OnAddedToWishlistListener) {
        mCallback = listener
    }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    /**
     * Inicializador del componente visual
     *
     * [attrs] Conjunto de atributos que pueden ser establecidos en el Layout.
     * [defStyle]
     */
    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.booklayout, this)

        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BookLayout, defStyle, 0)

        setBookTitle(typedArray.getString(R.styleable.BookLayout_bookTitle))
        setBookAuthor(typedArray.getString(R.styleable.BookLayout_bookAuthor))
        setBookPublisher(typedArray.getString(R.styleable.BookLayout_bookPublisher))
        setBookDate(typedArray.getString(R.styleable.BookLayout_bookDate))
        setWishlistButtonIcon(typedArray.getResourceId(R.styleable.BookLayout_buttonIcon, 0))
        setBookGenre(typedArray.getString(R.styleable.BookLayout_bookGenre))
        setBookPages(typedArray.getString(R.styleable.BookLayout_bookPages))
        setBookDescription(typedArray.getString(R.styleable.BookLayout_bookDescription))

        button_add_to_wishlist.setOnClickListener(this)

        typedArray.recycle()
    }

    /**
     * Recupera la imagen del libro.
     *
     * @return [ImageView] Imagen del libro.
     */
    fun getImageOfBook(): ImageView {
        return book_image
    }

    /**
     * Establece el título del libro.
     *
     * [title] Título del libro.
     */
    fun setBookTitle(title: String) {
        if (checkStrings(title)) {
            book_title.text = title
            invalidate()
            requestLayout()
        }

    }

    /**
     * Establece el autor del libro.
     *
     * [author] Autor del libro.
     */
    fun setBookAuthor(author: String) {
        if (checkStrings(author)) {
            book_author.text = author
            invalidate()
            requestLayout()
        }

    }

    /**
     * Establece el texto del botón de añadir a favoritos.
     *
     * [buttonText] Texto del botón de añadir a favoritos.
     */
    fun setButtonText(buttonText: String) {
        if (checkStrings(buttonText)) {
            button_add_to_wishlist.text = buttonText
            invalidate()
            requestLayout()
        }
    }

    /**
     * Establece la editorial del libro.
     *
     * [publisher] Editorial del libro.
     */
    fun setBookPublisher(publisher: String) {
        if (checkStrings(publisher)) {
            book_publisher.text = publisher
            invalidate()
            requestLayout()
        }

    }

    /**
     * Establece la fecha de lanzamiento del libro.
     *
     * [date] Fecha de lanzamiento del libro.
     */
    fun setBookDate(date: String) {
        if (checkStrings(date)) {
            book_date.text = date
            invalidate()
            requestLayout()
        }

    }

    /**
     * Establece el icono del botón de añadir a favoritos.
     *
     * [resId] Identificador del icono del botón de añadir a favoritos.
     */
    fun setWishlistButtonIcon(resId: Int) {
        if (checkInts(resId)) {
            button_add_to_wishlist.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, resId, 0)
            invalidate()
            requestLayout()
        }

    }

    /**
     * Establece el género del libro.
     *
     * [genre] Género del libro.
     */
    fun setBookGenre(genre: String) {
        if (checkStrings(genre)) {
            book_genre.text = genre
            invalidate()
            requestLayout()
        }

    }

    /**
     * Establece el número de páginas del libro.
     *
     * [pages] Número de páginas del libro.
     */
    fun setBookPages(pages: String) {
        if (checkStrings(pages)) {
            book_pages.text = "${pages} páginas"
            invalidate()
            requestLayout()
        }
    }

    /**
     * Establece la descripción del libro.
     *
     * [description] Descripción del libro.
     */
    fun setBookDescription(description: String) {
        if (checkStrings(description)) {
            expandable_desc.text = description
            invalidate()
            requestLayout()
        }
    }

    /**
     * Comprueba si una cadena de texto pasada por parámetro está vacía
     *
     * [sequence] Cadena de texto.
     *
     * @return [Boolean] Resultado de la comprobación.
     */
    private fun checkStrings(sequence: String): Boolean {
        return sequence.isNotEmpty()
    }

    private fun checkInts(resId: Int): Boolean {
        return true
    }
}
