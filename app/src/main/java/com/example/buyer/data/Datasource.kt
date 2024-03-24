package com.example.buyer.data

import com.example.buyer.R
import com.example.buyer.model.Book

class Datasource() {
    fun loadBooks(): List<Book> {
        return listOf<Book>(
            Book(R.string.title1, R.drawable._504_peaceful_reflections_mhc),
            Book(R.string.title1, R.drawable._504_peaceful_reflections_mhc),
            Book(R.string.title1, R.drawable._504_peaceful_reflections_mhc),
            Book(R.string.title1, R.drawable._504_peaceful_reflections_mhc),
            Book(R.string.title1, R.drawable._504_peaceful_reflections_mhc),
            Book(R.string.title1, R.drawable._504_peaceful_reflections_mhc),
            Book(R.string.title1, R.drawable._504_peaceful_reflections_mhc),
            Book(R.string.title1, R.drawable._504_peaceful_reflections_mhc),
            Book(R.string.title1, R.drawable._504_peaceful_reflections_mhc)
        )
    }
}