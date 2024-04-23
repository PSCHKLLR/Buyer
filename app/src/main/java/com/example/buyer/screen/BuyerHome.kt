package com.example.buyer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Castle
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.TheaterComedy
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.buyer.Navigation
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book
import com.example.buyer.model.ListItem

import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    buyerViewModel: BuyerViewModel = viewModel(),
    navController: NavController,
){
    val buyerUiState by buyerViewModel.uiState.collectAsState()
    val listItems = listOf(
        ListItem(
            title = "For You",
            onClick = { },
            icon = Icons.Filled.Menu
        ),
        ListItem(
            title = "Genres",
            onClick = { },
            icon = Icons.Filled.Menu
        )
    )
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState {
        listItems.size
    }

    val genres = listOf(
        ListItem(
            title = "Fiction",
            onClick = { },
            icon = Icons.Filled.Description
        ),
        ListItem(
            title = "Non-Fiction",
            onClick = { },
            icon = Icons.AutoMirrored.Filled.Article
        ),
        ListItem(
            title = "Drama",
            onClick = { },
            icon = Icons.Filled.TheaterComedy
        ),
        ListItem(
            title = "Children's Literature",
            onClick = { },
            icon = Icons.Filled.ChildCare
        ),
        ListItem(
            title = "Science Fiction (Sci-Fi)",
            onClick = { },
            icon = Icons.Filled.Science
        ),
        ListItem(
            title = "Fantasy",
            onClick = { },
            icon = Icons.Filled.Castle
        ),
        ListItem(
            title = "Mystery",
            onClick = { },
            icon = Icons.Filled.PersonSearch
        ),
        ListItem(
            title = "Romance",
            onClick = { },
            icon = Icons.Filled.Favorite
        ),
        ListItem(
            title = "Thriller",
            onClick = { },
            icon = Icons.Filled.WaterDrop
        ),
        ListItem(
            title = "Novel",
            onClick = { },
            icon = Icons.Filled.Book
        )
    )


    LaunchedEffect(selectedTabIndex){
        if(!pagerState.isScrollInProgress){
            pagerState.animateScrollToPage(selectedTabIndex)
        }
    }
    LaunchedEffect(pagerState.currentPage){
        selectedTabIndex = pagerState.currentPage
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val bookList = Datasource().loadBooks()

    Column(modifier = modifier) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text(
                        text = "K-Store",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null)
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(text = "Profile")
                            }
                        },
                        selected = false,
                        onClick = { /*navController.navigate(Navigation.TradeIn.name)*/ }
                    )
                    NavigationDrawerItem(
                        label = {
                            BadgedBox(badge = {
                                if (buyerUiState.currentOrder.cartList.size > 0) {
                                    Badge {
                                        Text(text = "${buyerUiState.currentOrder.cartList.size}")
                                    }
                                }
                            }
                            ) {
                                Row (
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(text = "Cart")
                                }
                            }
                        },
                        selected = false,
                        onClick = { navController.navigate(Navigation.Cart.name) }
                    )
                    NavigationDrawerItem(
                        label = {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(imageVector = Icons.Filled.Inventory, contentDescription = null)
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(text = "Orders")
                            }
                        },
                        selected = false,
                        onClick = { navController.navigate(Navigation.Orders.name) }
                    )
                    NavigationDrawerItem(
                        label = {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(text = "Search")
                            }
                        },
                        selected = false,
                        onClick = { navController.navigate(Navigation.Search.name) }
                    )
                    NavigationDrawerItem(
                        label = {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(text = "Wishlist")
                            }
                                },
                        selected = false,
                        onClick = { navController.navigate(Navigation.Wishlist.name) }
                    )
                    NavigationDrawerItem(
                        label = {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(imageVector = Icons.Filled.SwapHoriz, contentDescription = null)
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(text = "Trade In")
                            }
                        },
                        selected = false,
                        onClick = { navController.navigate(Navigation.TradeIn.name) }
                    )
                }
            },
//            gesturesEnabled = false
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "K-Store",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .fillMaxWidth()
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Navigation Drawer"
                                )
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    navController.navigate(Navigation.Cart.name)
                                }
                            ) {
                                BadgedBox(badge = {
                                    if (buyerUiState.currentOrder.cartList.size > 0){
                                        Badge{
                                            Text(text = "${buyerUiState.currentOrder.cartList.size}")
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.ShoppingCart,
                                        contentDescription = "Cart"
                                    )
                                }

                            }
                            IconButton(
                                onClick = {
                                    navController.navigate(Navigation.Search.name)
                                }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search"
                                )
                            }
                            IconButton(
                                onClick = {
                                    /* doSomething() */
                                }) {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "Profile"
                                )
                            }
                        }
                    )
                },
            ){
                Column (
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        listItems.forEachIndexed { index, tabItem ->
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
                                        navController = navController,
                                        bookList = bookList,
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
                                        navController = navController,
                                        bookList = bookList,
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
                                        navController = navController,
                                        bookList = bookList,
                                        modifier = Modifier.height(200.dp)
                                    )
                                    BookSlider(
                                        navController = navController,
                                        bookList = bookList,
                                        modifier = Modifier.height(200.dp)
                                    )
                                    BookSlider(
                                        navController = navController,
                                        bookList = bookList,
                                        modifier = Modifier.height(200.dp)
                                    )
                                }
                            }
                        } else if (index == 1) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
//                                    .verticalScroll(state = scrollState),
                                contentAlignment = Alignment.TopStart
                            ){
                                LazyColumn {
                                    items(genres) {
                                        Row (
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    it.onClick
                                                }
                                                .padding(16.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ){
                                            Icon(
                                                imageVector = it.icon,
                                                contentDescription = null
                                            )
                                            Spacer(modifier = Modifier.width(16.dp))
                                            Text(text = it.title)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookCard(navController: NavController, book: Book){
//    val mContext = LocalContext.current
    Box(
        modifier = Modifier
            .aspectRatio(3 / 4f)
            .padding(
                top = 4.dp,
                bottom = 12.dp,
                start = 8.dp,
                end = 8.dp,
            )
            .clip(RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            painterResource(book.bookImg),
            contentDescription = "books",
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                    navController.navigate(Navigation.BookDescription.name + "/${book.bookId}")
                },
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        0f,
                        500f
                    )
                )
        ) {}
        Text(
            text = book.bookTitle,
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}


@Composable
fun BookSlider(navController: NavController, bookList: List<Book>, modifier: Modifier = Modifier){
    LazyRow(modifier = modifier){
        items(bookList){book ->
            BookCard(
                navController = navController,
                book = book
            )
        }
    }
}

