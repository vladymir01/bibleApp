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
    if (chapterContent != null){
        Column() {
            Text("${chapterContent?.Book}")
            Text(text = "Chapter:${chapterContent?.Chapter}")
            Text(text = "${chapterContent?.Output}")
        }
    }else{
        Text(text = "Loading...")
    }
}

