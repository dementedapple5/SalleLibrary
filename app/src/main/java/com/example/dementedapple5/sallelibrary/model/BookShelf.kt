package com.example.dementedapple5.sallelibrary.model

/**
 * Categoría de un libro.
 *
 * Esta clase consiste en diferenciar cada conjunto de [Book] en base a su categoría.
 *
 * @constructor Crea una instancia con los siguientes parámetros.
 * @property title Título de la categoría de [Book]
 * @property books Conjunto de [Book] por cada categoría
 *
 * @author Daniel de la Lastra
 * @author Javier Torrus
 */
data class BookShelf(val title: String, val books: ArrayList<Book>)