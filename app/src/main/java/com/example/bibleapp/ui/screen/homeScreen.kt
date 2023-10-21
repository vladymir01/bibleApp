package com.example.bibleapp.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TabRow
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bibleapp.TAG
import com.example.bibleapp.data.model.Bible
import com.example.bibleapp.data.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    val bible = Bible()
    var tabState by remember { mutableStateOf(0) }
    val tabTitles = listOf("Old Testament", "New Testament")
    Scaffold(
        topBar = { HomeTopBar()}
    ) { innerPadding ->
        //region The body of the home screen
        Surface(
        modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                TabRow(selectedTabIndex = tabState) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = tabState == index,
                            onClick = { tabState = index },
                            text = { Text(text = title, fontSize = 20.sp) }
                        )
                    }
                }

                if(tabState == 0){
                    ListOfBooks(testament = bible.oldTestament, onClickBookName = {id,name-> navController.navigate("book/${id}/${name}")})
                }else{
                    ListOfBooks(testament = bible.newTestament, onClickBookName = {id, name-> navController.navigate("book/${id}/${name}")})
                }
            }


        }
        //endregion
    }
   

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = { Text(text = "Bible App")},
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home Screen")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
            }
        }

        )
}

@Composable
fun ListOfBooks(testament:List<Book>, onClickBookName:(id:String, name:String)->Unit){
    
    LazyColumn(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        itemsIndexed(testament){index, book ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClickBookName(book.id, book.name) }

            ) {
                Row() {
                    Text(text = "${index + 1}  -  ", fontSize = 20.sp)
                    Text(text = book.nameLong, fontSize = 20.sp)
                }
            }
        }
    }

}