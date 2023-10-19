package com.example.bibleapp.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bibleapp.TAG
import com.example.bibleapp.ui.ViewModel.BibleViewModel

@Composable
fun BookScreen(bibleViewModel: BibleViewModel,navController: NavController, bookId:String, name:String){

    val chapters by bibleViewModel.chapters.observeAsState()

    LaunchedEffect(key1 = Unit) {
        Log.d(TAG, "Inside the Launch Effect")
        Log.d(TAG, "The bookId is: $bookId")
        bibleViewModel.getTheChapters(bookId)
    }

    if (chapters != null){
        Column() {
            Text("$name: ${chapters?.size}")
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ){
                chapters?.let {chapterList ->  //Testing if the chapter is not null, we use a new variable chapterList (we could also use the operator "it")
                    items(chapterList){ chapter->
                        if(chapter.number != "intro"){
                            Surface(
                                modifier = Modifier.clickable {navController.navigate("chapter/${name}/${chapter.number}") }
                            ) {
                                Text(chapter.number)
                            }
                        }
                    }
                }
            }

            Button(onClick = { navController.navigateUp() }) {
                Text(text = "Go back")
            }
        }
    }else{
        Text(text = "Loading...")
    }
}