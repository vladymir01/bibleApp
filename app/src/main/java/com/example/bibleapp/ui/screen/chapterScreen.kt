package com.example.bibleapp.ui.screen

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bibleapp.ui.ViewModel.BibleViewModel

@Composable
fun ChapterScreen(bibleViewModel:BibleViewModel,book:String, chapter:String){

    val chapterContent by bibleViewModel.contentChapter.observeAsState()


    LaunchedEffect(key1 = Unit){
        bibleViewModel.getTheContentChapter(book, chapter)
    }
    //Here I'm using the let with the run to do an if else statement,
    // doing so I will not have to do the null check when calling the
    // chapterContent variable like this: chapterContent?.chapter
    chapterContent?.let {
        Column(modifier = Modifier.padding(20.dp)) {
                Text(it.Book, fontSize = 24.sp, modifier = Modifier.padding(bottom = 20.dp))
                Text(text = "Chapter: ${it.Chapter}", fontSize = 24.sp, modifier = Modifier.padding(bottom = 20.dp))
            Surface(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(text = it.Output)
            }
        }
    }?:run{
        Text(text = "Loading...")
    }
}

