package com.example.bibleapp.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
    //Here I'm using the let with the run to do an if else statement,
    // doing so I can quietly call the new variable theChapters without doing the null check
    chapters?.let {theChapters ->
        Column(modifier = Modifier.padding(20.dp)) {
            Text("$name", modifier = Modifier.padding(bottom = 20.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 75.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ){
                    items(theChapters){ chapter->
                        if(chapter.number != "intro"){
                            Surface(
                                modifier = Modifier
                                    .clickable {navController.navigate("chapter/${name}/${chapter.number}") }
                            ) {
                                Text(chapter.number, modifier = Modifier.background(Color.Cyan), textAlign = TextAlign.Center)
                            }
                        }
                }
            }

            Button(onClick = { navController.navigateUp() }) {
                Text(text = "Go back")
            }
        }
    }?:run{
        Text(text = "Loading...")
    }
}