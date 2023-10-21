package com.example.bibleapp.ui.screen

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bibleapp.ui.ViewModel.BibleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterScreen(navController: NavController,bibleViewModel:BibleViewModel,book:String, chapter:String){

    val chapterContent by bibleViewModel.contentChapter.observeAsState()


    LaunchedEffect(key1 = Unit){
        bibleViewModel.getTheContentChapter(book, chapter)
    }

    Scaffold(
        topBar = { MyTopBar(book, navController)}
    ) { innerPadding ->
        //Here I'm using the let with the run to do an if else statement,
        // doing so I will not have to do the null check when calling the
        // chapterContent variable like this: chapterContent?.chapter
        //region The body of the screen
        chapterContent?.let {
            Surface(modifier = Modifier.padding(innerPadding)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Chapter: ${it.Chapter}",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Divider(thickness = 1.dp)
                    Surface(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(top = 10.dp)
                    ) {
                        Text(text = it.Output, fontSize = 20.sp)
                    }
                }
            }
        }?: run {

            Surface(modifier = Modifier.padding(innerPadding)) {
                Text(text = "Loading...")
            }
        }


        //endregion
    }

}

