package com.example.bibleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.bibleapp.ui.screen.BookScreen
import com.example.bibleapp.ui.screen.Chapter1Screen
import com.example.bibleapp.ui.screen.Chapter2Screen
import com.example.bibleapp.ui.screen.HomeScreen
import com.example.bibleapp.ui.theme.BibleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BibleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp(){
 val navController = rememberNavController()

 NavHost(navController, startDestination = "home"){
     composable(route = "home"){
         HomeScreen(navController)
     }
    //region The nested navigation
     navigation(startDestination = "bookHome", route = "book"){
         composable("bookHome"){ BookScreen(navController)}
         composable("chapter1"){ Chapter1Screen(navController)}
         composable("chapter2"){ Chapter2Screen(navController)}
     }
     //endregion



 }
}