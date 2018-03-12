package com.example.dementedapple5.sallelibrary.model

import java.io.Serializable

class Book(): Serializable {
    var title: String = ""
    var publisher: String = ""
    var genre: String = ""
    var numPages: Int = 0
    var img: String = ""
    var author: String = ""
    var releaseDate: String = ""
    var description: String = ""

    constructor(title: String, author: String, releaseDate: String, publisher: String, genre: String, numPages: Int, description: String, img: String) : this() {
        this.title = title
        this.img = img
        this.author = author
        this.releaseDate = releaseDate
        this.publisher = publisher
        this.genre = genre
        this.numPages = numPages
        this.description = description
    }

    override fun toString(): String {
        return "Book(title='$title', author='$author')"
    }



}