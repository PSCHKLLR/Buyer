
package com.example.buyer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Search(navController: NavController, modifier: Modifier = Modifier){
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var active by rememberSaveable {
        mutableStateOf(false)
    }
    var history = remember {
        mutableStateListOf(
            ""
        )
    }
    Scaffold(modifier = modifier) {
        Box(
            Modifier
                .fillMaxSize()
                .semantics { isTraversalGroup = true }
        ) {
            SearchBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .semantics { traversalIndex = -1f },
                query = text,
                onQueryChange = { text = it },
                onSearch = {
                    history.add(text)
                    active = false
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                text = ""
                            }
                        )
                    }
                },
                //                colors =
            ) {
                history.forEach {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable { text = it }) {
                        Icon(
                            imageVector = Icons.Filled.History,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(text = it)
                    }
                }
            }
//        }
//        Row(
//        ) {
//            Icon(
//                modifier = Modifier
//                    .clickable { navController.navigateUp() }
//                    .align(Alignment.Top)
//                    .padding(vertical = 24.dp, horizontal = 8.dp),
//                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                contentDescription = null
//            )
//
        }
        //ADD HERE
        if (!active){
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .padding(top = 64.dp)
            ) {
                val bookList: List<Book> = Datasource().loadBooks()
                items(bookList.size){
                    BookCard(navController = navController, book = bookList[it])
                }
            }
        }
        
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevSearch(){
    val navController  = rememberNavController()
    Search(navController = navController, modifier = Modifier.fillMaxSize())
}

