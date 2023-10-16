package com.example.bibleapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BookScreen(navController: NavController){
    
    Column() {
        Text(text = "The Book Screen")
        Button(onClick = { navController.navigate("chapter1") }) {
            Text(text = "Chapter1")
        }
        Button(onClick = { navController.navigate("chapter2") }) {
            Text(text = "Chapter2")
        }
        
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Go back to Home")
        }
    }
}