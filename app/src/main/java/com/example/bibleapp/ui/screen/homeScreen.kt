package com.example.bibleapp.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bibleapp.TAG
import com.example.bibleapp.data.model.Bible
import com.example.bibleapp.data.model.Book

@Composable
fun HomeScreen(navController: NavController){
    val bible = Bible()
    var tabState by remember { mutableStateOf(0) }
    val tabTitles = listOf("Old Testament", "New Testament")
    Column {
        TabRow(selectedTabIndex = tabState) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = tabState == index,
                    onClick = { tabState = index },
                    text = { Text(text = title) }
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

@Composable
fun ListOfBooks(testament:List<Book>, onClickBookName:(id:String, name:String)->Unit){
    
    LazyColumn{
        items(testament){book ->
            Surface(modifier = Modifier.clickable { onClickBookName(book.id, book.name) }) {
                Text(text = "${book.nameLong}")
            }
        }
    }

}