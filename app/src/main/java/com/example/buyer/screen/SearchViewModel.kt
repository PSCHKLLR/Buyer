package com.example.buyer.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class SearchViewModel: ViewModel() {
    var searchQuery by mutableStateOf("")
        private set

    private val bookFlow = flowOf(
        Datasource().loadBooks()
    )

    val searchResult: StateFlow<List<Book>> =
        snapshotFlow { searchQuery }
            .combine(bookFlow) { searchQuery, books ->
                when{
                    searchQuery.isNotEmpty() -> books.filter { book ->
                        book.bookTitle.contains(searchQuery, ignoreCase = true)
                    }
                    else -> books
                }
            }.stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5_000)
            )


    fun onSearchQueryChange(newQuery: String){
        searchQuery = newQuery
    }
}