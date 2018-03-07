package com.example.dementedapple5.sallelibrary.model

import java.util.*

/**
 * Created by dementedapple5 on 22/02/2018.
 */

class Book() {

    var title: String = ""
    var price: Float = 0.0f
    var img: Int = 0
    var author: String = ""
    var releaseDate: Date = Date()
    var rate: Int = 0



    constructor(title: String, price: Float, img: Int) : this() {
        this.title = title
        this.price = price
        this.img = img
    }

    constructor(title: String, price: Float, img: Int, author: String, releaseDate: Date, rate: Int) : this() {
        this.title = title
        this.price = price
        this.img = img
        this.author = author
        this.releaseDate = releaseDate
        this.rate = rate
    }

}