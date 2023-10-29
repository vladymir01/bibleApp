package com.example.bibleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bibleapp.ui.viewModel.BibleViewModel
import com.example.bibleapp.ui.screen.BookScreen
import com.example.bibleapp.ui.screen.ChapterScreen
import com.example.bibleapp.ui.screen.HomeScreen
import com.example.bibleapp.ui.screen.TestingLifeCycle
import com.example.bibleapp.ui.theme.BibleAppTheme


const val TAG = "MainActivity"
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
//                    TestingLifeCycle()
                }
            }
        }
    }
}

@Composable
fun MainApp(){
    val bibleViewModel: BibleViewModel = viewModel()

 val navController = rememberNavController()

 NavHost(navController, startDestination = "home"){
     composable(route = "home"){
         HomeScreen(navController)
     }
     //region composable to navigate to the list of chapters of a selected book
     composable(
         route = "book/{id}/{name}",
         arguments = listOf(
             navArgument("id"){
                 type = NavType.StringType
                 defaultValue = "Default"
             },
         navArgument("name"){
             type = NavType.StringType
             defaultValue = "Default"
         }
         )
     ){navBackStackEntry ->
         val idParam = navBackStackEntry.arguments?.getString("id")
         val nameParam = navBackStackEntry.arguments?.getString("name")
         idParam?.let { nameParam?.let { it1 -> BookScreen(bibleViewModel,navController, it, it1) } }
     }
     //endregion

     //region composable to navigate to the content of a selected chapter
        composable(
            route = "chapter/{book}/{chapter}",
            arguments = listOf(
                navArgument("book"){
                    type = NavType.StringType
                    defaultValue = "Default"
                },
                navArgument("chapter"){
                    type = NavType.StringType
                    defaultValue = "Default"
                }
            )
        ){navBackStackEntry ->
            val bookParam = navBackStackEntry.arguments?.getString("book")
            val chapterParam = navBackStackEntry.arguments?.getString("chapter")
            bookParam?.let{chapterParam?.let{it1 -> ChapterScreen(
                navController = navController,
                bibleViewModel = bibleViewModel,
                book = it,
                chapter = it1
            )}}
        }
     //endregion
 }
}

