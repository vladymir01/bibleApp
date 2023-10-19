package com.example.bibleapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
        Column() {
            Text(it.Book)
            Text(text = "Chapter:${it.Chapter}")
            Text(text = it.Output)
        }
    }?:run{
        Text(text = "Loading...")
    }
}

