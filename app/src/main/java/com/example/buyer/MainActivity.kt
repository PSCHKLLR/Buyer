package com.example.buyer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book
import com.example.buyer.model.TabItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface {

                BuyerApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BuyerHome(modifier: Modifier = Modifier){
    val tabItems = listOf(
        TabItem(
            title = "For You"
        ),
        TabItem(
            title = "Genres"
        )
    )
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState {
        tabItems.size
    }
    val mContext = LocalContext.current

    LaunchedEffect(selectedTabIndex){
        if(!pagerState.isScrollInProgress){
            pagerState.animateScrollToPage(selectedTabIndex)
        }
    }
    LaunchedEffect(pagerState.currentPage){
        selectedTabIndex = pagerState.currentPage
    }
    Column(modifier = modifier) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {

                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                        /* doSomething() */
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                            mContext.startActivity(Intent(mContext, Cart::class.java))
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = "Localized description"
                            )
                        }
                        IconButton(
                            onClick = {
                        /* doSomething() */
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Localized description"
                            )
                        }
                        IconButton(
                            onClick = {
                            /* doSomething() */
                            }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            content = {
                Column (
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .fillMaxSize()
                    ) {
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        tabItems.forEachIndexed { index, tabItem ->
                            Tab(
                                selected = index == selectedTabIndex,
                                onClick = {
                                    selectedTabIndex = index
                                }
                            ) {
                                Text(
                                    text = tabItem.title,
                                    Modifier.padding(8.dp)
                                )
                            }
                        }
                    }

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {index ->
                        if(index == 0){
                            Box(
                                modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(state = scrollState),
                                contentAlignment = Alignment.TopStart
                            ) {
                                Column (
                                    modifier = Modifier.fillMaxWidth()
                                ){

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = "Featured Books",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                   )
                                    BookSlider(
                                        bookList = Datasource().loadBooks(),
                                        modifier = Modifier.height(200.dp)

                                    )
                                    Text(
                                        text = "Suggested For You",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    BookSlider(
                                        bookList = Datasource().loadBooks(),
                                        modifier = Modifier.height(200.dp)
                                    )
                                    Text(
                                        text = "Discover New Books",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    BookSlider(
                                        bookList = Datasource().loadBooks(),
                                        modifier = Modifier.height(200.dp)
                                    )
                                    BookSlider(
                                        bookList = Datasource().loadBooks(),
                                        modifier = Modifier.height(200.dp)
                                    )
                                    BookSlider(
                                        bookList = Datasource().loadBooks(),
                                        modifier = Modifier.height(200.dp)
                                    )
                                }
                            }
                        } else {
                            Box (
                                modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(state = scrollState),
                                contentAlignment = Alignment.TopStart
                                ){
                                Column {

                                }
                            }
                        }

                    }
                }
            }
        )
    }
}

@Composable
fun BookCard(book: Book, modifier: Modifier = Modifier){
    val mContext = LocalContext.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomStart
    ) {

        Image(
            painterResource(book.imageResId),
            contentDescription = "books",
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                           mContext.startActivity(Intent(mContext, BookDescription::class.java))
                },
            contentScale = ContentScale.FillHeight,
        )
        Text(
            text = LocalContext.current.getString(book.stringResId),
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}


@Composable
fun BookSlider(bookList: List<Book>,modifier: Modifier = Modifier){
    LazyRow(modifier = modifier){
        items(bookList){book ->
            BookCard(
                book = book,
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        bottom = 12.dp,
                        start = 8.dp,
                        end = 8.dp,
                    )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BuyerApp(){
    BuyerHome(modifier = Modifier.fillMaxSize())
}
