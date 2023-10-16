package com.example.bibleapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BookScreen(navController: NavController, bookName:String){
    Column() {
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Go Back")
        }
    Text(text = bookName)
    }

}