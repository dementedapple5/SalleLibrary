package com.example.dementedapple5.sallelibrary.model

import java.io.Serializable

/**
 * Representación de un libro.
 *
 * Esta clase consiste en definir un libro que serán los principales objetos que se mostrarán en la pantalla principal de la aplicación.
 *
 * @constructor Crea una instancia vacía de [Book].
 *
 * @property title Título.
 * @property author Autor.
 * @property releaseDate Fecha de salida.
 * @property publisher Editorial.
 * @property genre Género.
 * @property numPages Número de páginas.
 * @property description Descripción.
 * @property img Imagen de portada.
 */
class Book(): Serializable {
    var title: String = ""
    var publisher: String = ""
    var genre: String = ""
    var numPages: Int = 0
    var img: String = ""
    var author: String = ""
    var releaseDate: String = ""
    var description: String = ""

    /**
     * @constructor Crea una instancia de [Book] con una serie de propiedades.
     */
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

    /**
     * Permite imprimir un [String] conteniendo información sobre el [title] y [author].
     */
    override fun toString(): String {
        return "Book(title='$title', author='$author')"
    }



}