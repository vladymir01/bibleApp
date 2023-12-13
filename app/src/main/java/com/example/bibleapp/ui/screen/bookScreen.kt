package com.example.bibleapp.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bibleapp.TAG
import com.example.bibleapp.ui.viewModel.BibleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(bibleViewModel: BibleViewModel,navController: NavController, bookId:String, name:String){

    val chapters by bibleViewModel.chapters.observeAsState()

    LaunchedEffect(key1 = Unit) {
        Log.d(TAG, "Inside the Launch Effect")
        Log.d(TAG, "The bookId is: $bookId")
        bibleViewModel.getTheChapters(bookId)
    }
    Scaffold(
        topBar = { MyTopBar(name, navController)}
    ) {innerPadding ->
        //Here I'm using the let with the run to do an if else statement,
        // doing so I can quietly call the new variable theChapters without doing the null check
        //region The body of the book screen
        chapters?.let {theChapters ->
            Column(modifier = Modifier.padding(innerPadding).background(MaterialTheme.colorScheme.background)) {
                Text("Chapters", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(start = 20.dp,bottom = 10.dp))
                Divider(thickness = 1.dp, modifier = Modifier.padding(bottom = 10.dp))
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
                                Text(
                                    chapter.number,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }?:run{
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(innerPadding)
            )
        }
        //endregion
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(title:String, navController: NavController){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        title = { Text(title, style = MaterialTheme.typography.headlineMedium)},
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Home Screen")
            }
        }
    )
}