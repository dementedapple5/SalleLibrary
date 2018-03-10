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
import kotlinx.android.synthetic.main.booklayout.view.*

class BookLayout : ConstraintLayout, View.OnClickListener {
    private lateinit var mCallback: OnAddedToWishlistListener


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_add_to_wishlist -> {
                mCallback.onAddedToWishlist(this, book_title.text.toString())
            }
        }
    }

    interface OnAddedToWishlistListener {
        fun onAddedToWishlist(source: BookLayout, textToDisplay: String)
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

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LayoutInflater.from(context).inflate(R.layout.booklayout, this)

        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BookLayout, defStyle, 0)

        setImageOfBook(typedArray.getResourceId(R.styleable.BookLayout_imageOfBook, 0))
        setBookTitle(typedArray.getString(R.styleable.BookLayout_bookTitle))
        setBookAuthor(typedArray.getString(R.styleable.BookLayout_bookAuthor))
        setBookPublisher(typedArray.getString(R.styleable.BookLayout_bookPublisher))
        setBookDate(typedArray.getString(R.styleable.BookLayout_bookDate))
        setBookIcon(typedArray.getResourceId(R.styleable.BookLayout_buttonIcon, 0))
        setBookGenre(typedArray.getString(R.styleable.BookLayout_bookGenre))
        setBookPages(typedArray.getString(R.styleable.BookLayout_bookPages))
        setBookDescription(typedArray.getString(R.styleable.BookLayout_bookDescription))

        button_add_to_wishlist.setOnClickListener(this)

        typedArray.recycle()
    }

    private fun setImageOfBook(resId: Int) {
        if (checkInts(resId)) {
            book_image.setImageResource(resId)
            invalidate()
            requestLayout()
        }
    }

    private fun setBookTitle(title: String) {
        if (checkStrings(title)) {
            book_title.text = title
            invalidate()
            requestLayout()
        }

    }

    private fun setBookAuthor(author: String) {
        if (checkStrings(author)) {
            book_author.text = author
            invalidate()
            requestLayout()
        }

    }

    private fun setBookPublisher(publisher: String) {
        if (checkStrings(publisher)) {
            book_publisher.text = publisher
            invalidate()
            requestLayout()
        }

    }

    private fun setBookDate(date: String) {
        if (checkStrings(date)) {
            book_date.text = date
            invalidate()
            requestLayout()
        }

    }

    private fun setBookIcon(resId: Int) {
        if (checkInts(resId)) {
            button_add_to_wishlist.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, resId, 0)
            invalidate()
            requestLayout()
        }

    }

    private fun setBookGenre(genre: String) {
        if (checkStrings(genre)) {
            book_genre.text = genre
            invalidate()
            requestLayout()
        }

    }

    private fun setBookPages(pages: String) {
        if (checkStrings(pages)) {
            book_pages.text = "${pages} páginas"
            invalidate()
            requestLayout()
        }
    }

    private fun setBookDescription(description: String) {
        if (checkStrings(description)) {
            expandable_desc.text = description
            invalidate()
            requestLayout()
        }
    }

    private fun checkStrings(sequence: String): Boolean {
        return sequence.isNotEmpty()
    }

    private fun checkInts(resId: Int): Boolean {
        return resId != null
    }
}
