package com.example.bibleapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bibleapp.TAG
import com.example.bibleapp.ui.ViewModel.BibleViewModel

@Composable
fun BookScreen(navController: NavController, bookId:String, name:String){
    //in this screen I want to load the list of chapters for the selected book
    //for now I will instantiate the view model here, but afterwards I will hoist it
    val bibleViewModel: BibleViewModel = viewModel()

    val chapters by bibleViewModel.chapters.observeAsState()

    LaunchedEffect(key1 = Unit) {
        Log.d(TAG, "Inside the Launch Effect")
        bibleViewModel.getTheChapters(bookId)
    }

    Column() {
        Text("$name")
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ){
            chapters?.let {
                items(it.size){ it->
                    Text("${it + 1}")
                }
            }
        }

        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Go back")
        }
    }


}