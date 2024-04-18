package com.example.buyer.data

import com.example.buyer.R
import com.example.buyer.model.Book

class Datasource() {
    fun loadBooks(): List<Book> {
        return listOf<Book>(
            Book("B001","Pride and Prejudice", R.drawable.pnp, R.string.description1,"Jane Austen", "Romance",  5, 125.69) ,
            Book("B002","To Kill a Mockingbird", R.drawable.tkam, R.string.TKaM, "Harper Lee","Thriller",  5, 100.00),
            Book("B003","The Great Gatsby", R.drawable.tgg, R.string.TGG, "F. Scott Fitzgerald","Novel",  5, 105.69),
            Book("B004","The Lord of The Rings", R.drawable.lord, R.string.LoTR, "J.R.R. Tolkien", "Novel",  5, 99.69),
            Book("B005","Don Quixote", R.drawable.dq, R.string.Don,"Miguel de Cervantes", "Romance",  5, 189.69),
            Book("B006","One Hundred Years of Solitude", R.drawable.one, R.string.oneHs,"Gabriel García Márquez", "Novel",  5, 130.69),
            Book("B007","Alice's Adventures in Wonderland", R.drawable.aaw, R.string.AliceW, "Lewis Carroll","Children's Literature",  5, 69.69)
        )
    }


}