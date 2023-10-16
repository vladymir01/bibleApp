package com.example.bibleapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Chapter1Screen(navController: NavController){
    Column() {

        Text(text = "Chapter 1")

        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Back to Book Screen")
        }

    }
}

@Composable
fun Chapter2Screen(navController: NavController){
    Column() {

        Text(text = "Chapter 2")

        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Back to Book Screen")
        }

    }
}