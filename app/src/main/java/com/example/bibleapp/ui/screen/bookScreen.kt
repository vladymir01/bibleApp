package com.example.bibleapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bibleapp.TAG
import com.example.bibleapp.ui.ViewModel.BibleViewModel

@Composable
fun BookScreen(navController: NavController, bookId:String){
    //in this screen I want to load the list of chapters for the selected book
    //for now I will instantiate the view model here, but afterwards I will hoist it
    val bibleViewModel: BibleViewModel = viewModel()

    LaunchedEffect(key1 = Unit) {
        Log.d(TAG, "Inside the Launch Effect")
        bibleViewModel.getTheChapters(bookId)
    }

    Column() {
        Button(onClick = { navController.navigateUp()  }) {
            Text(text = "Go Back")
        }
    Text(text = bookId)
    }

}