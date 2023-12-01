package com.example.bibleapp.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.bibleapp.TAG
import com.example.bibleapp.ui.viewModel.BibleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    navController: NavController,
    bibleViewModel:BibleViewModel,
    book:String,
    chapter:String
){

    val chapterContent by bibleViewModel.contentChapter.observeAsState()
    val context = LocalContext.current

//region The Side Effects

    LaunchedEffect(key1 = Unit){
        bibleViewModel.getTheContentChapter(book, chapter)
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                Log.d(TAG,"That is the ON_START")
            } else if (event == Lifecycle.Event.ON_STOP) {
                Log.d(TAG,"That is the ON_STOP")
                bibleViewModel.textToSpeechStop()
            }


        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

  //endregion

    Scaffold(
        topBar = { MyTopBar(book, navController)}
    ) { innerPadding ->
        //Here I'm using the let with the run to do an if else statement,
        // doing so I will not have to do the null check when calling the
        // chapterContent variable like this: chapterContent?.chapter
        //region The body of the screen
        chapterContent?.let {
            Surface(modifier = Modifier.padding(innerPadding)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    //region The button to read the text
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        IconButton(
                            onClick = { bibleViewModel.textToSpeech(context, it.Output) },
                            modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer)
                        ) {
                           Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "The play button")
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        IconButton(
                            onClick = { bibleViewModel.textToSpeechStop()},
                            modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer)
                        ) {
                            Text(text = "Stop")
                        }
                    }

                    //endregion
                    Text(
                        text = "Chapter: ${it.Chapter}",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Divider(thickness = 1.dp)
                    Surface(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(top = 10.dp)
                    ) {
                        Text(text = it.Output, fontSize = 20.sp)
                    }
                }
            }
        }?: run {

            Surface(modifier = Modifier.padding(innerPadding)) {
                Text(text = "Loading...")
            }
        }


        //endregion
    }

}

