package com.example.bibleapp.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TabRow
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bibleapp.R
import com.example.bibleapp.TAG
import com.example.bibleapp.data.model.Bible
import com.example.bibleapp.data.model.Book
import com.example.bibleapp.ui.viewModel.BibleViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,bibleViewModel: BibleViewModel){
    val bible = Bible()
    var tabState by remember { mutableStateOf(0) }
    val tabTitles = listOf("Old Testament", "New Testament")

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
      drawerState = drawerState,
      drawerContent = { ModalDrawerSheet { DrawerContent(bibleViewModel)}}
    ){
        Scaffold(
            topBar = { HomeTopBar(bibleViewModel,onClickMenu = {
                scope.launch {
                    drawerState.apply { if (isClosed) open() else close() }
                }
            })}
        ) { innerPadding ->
            //region The body of the home screen
            Surface(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                ) {
                    TabRow(selectedTabIndex = tabState) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = tabState == index,
                                onClick = { tabState = index },
                                text = {
                                        Text(text = title,
                                            style = when(bibleViewModel.SelectedOption.value){
                                                "Small" -> MaterialTheme.typography.titleSmall
                                                "Medium" -> MaterialTheme.typography.titleMedium
                                                "Large" -> MaterialTheme.typography.titleLarge
                                                else -> MaterialTheme.typography.titleMedium
                                            })
                                        }
                            )
                        }
                    }

                    if(tabState == 0){
                        ListOfBooks(bibleViewModel,testament = bible.oldTestament, onClickBookName = {id,name-> navController.navigate("book/${id}/${name}")})
                    }else{
                        ListOfBooks(bibleViewModel,testament = bible.newTestament, onClickBookName = {id, name-> navController.navigate("book/${id}/${name}")})
                    }
                }


            }
            //endregion
        }
    }
}


@Composable
fun DrawerContent(bibleViewModel: BibleViewModel){
    Column(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .padding(20.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Settings",
                style = when(bibleViewModel.SelectedOption.value){
                    "Small" -> MaterialTheme.typography.headlineSmall
                    "Medium" -> MaterialTheme.typography.headlineMedium
                    "Large" -> MaterialTheme.typography.headlineLarge
                    else -> MaterialTheme.typography.headlineMedium
                },
                fontWeight = FontWeight.Bold
            )
        }
        Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Text To Speech",
                style = when(bibleViewModel.SelectedOption.value){
                    "Small" -> MaterialTheme.typography.titleSmall
                    "Medium" -> MaterialTheme.typography.titleMedium
                    "Large" -> MaterialTheme.typography.titleLarge
                    else -> MaterialTheme.typography.titleMedium
                },
                fontWeight = FontWeight.Bold
            )
            Switch(checked = bibleViewModel.textToSpeechIsActive.value, onCheckedChange = {bibleViewModel.setTextToSpeech(it)} )
        }
        Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Dark Mode",
                style = when(bibleViewModel.SelectedOption.value){
                    "Small" -> MaterialTheme.typography.titleSmall
                    "Medium" -> MaterialTheme.typography.titleMedium
                    "Large" -> MaterialTheme.typography.titleLarge
                    else -> MaterialTheme.typography.titleMedium
                },
                fontWeight = FontWeight.Bold

            )
            Switch(checked = bibleViewModel.darkModeIsActive.value, onCheckedChange = {bibleViewModel.setTheDarkMode(it)} )
        }
        Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))
        Row() {
            FontSizeRadioButton(viewModel = bibleViewModel)
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(bibleViewModel: BibleViewModel,onClickMenu:()->Unit){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
//        modifier = Modifier.padding(start = 20.dp),
        title = { Text(
            text = "Bible App",
            style = when(bibleViewModel.SelectedOption.value){
                "Small" -> MaterialTheme.typography.headlineSmall
                "Medium" -> MaterialTheme.typography.headlineMedium
                "Large" -> MaterialTheme.typography.headlineLarge
                else -> MaterialTheme.typography.headlineMedium
            }
        )},
        navigationIcon = {
                         Image(
                             painter = painterResource(id = R.drawable.bible_app) ,
                             contentDescription = "Logo",
                             modifier = Modifier.size(48.dp)
                         )
        },
        actions = {
            IconButton(onClick = { onClickMenu()}) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "menu"
                )
            }
        }

        )
}



@Composable
fun ListOfBooks(bibleViewModel: BibleViewModel,testament:List<Book>, onClickBookName:(id:String, name:String)->Unit){
    
    LazyColumn(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        itemsIndexed(testament){index, book ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClickBookName(book.id, book.name) }

            ) {
                Row() {
                    Text(
                        text = "${index + 1}  -  ",
                        style = when(bibleViewModel.SelectedOption.value){
                            "Small" -> MaterialTheme.typography.bodySmall
                            "Medium" -> MaterialTheme.typography.bodyMedium
                            "Large" -> MaterialTheme.typography.bodyLarge
                            else -> MaterialTheme.typography.bodyMedium
                        }
                    )
                    Text(
                        text = book.nameLong,
                        style = when(bibleViewModel.SelectedOption.value){
                            "Small" -> MaterialTheme.typography.bodySmall
                            "Medium" -> MaterialTheme.typography.bodyMedium
                            "Large" -> MaterialTheme.typography.bodyLarge
                            else -> MaterialTheme.typography.bodyMedium
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun FontSizeRadioButton(viewModel: BibleViewModel) {
// Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup()) {
        Text(
            text = "Change the font size of the App",
            style = when(viewModel.SelectedOption.value){
                "Small" -> MaterialTheme.typography.titleSmall
                "Medium" -> MaterialTheme.typography.titleMedium
                "Large" -> MaterialTheme.typography.titleLarge
                else -> MaterialTheme.typography.titleMedium
            },
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        viewModel.radioOptions.forEach(){text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        onClick = { viewModel.onOptionSelected(text) },
                        selected = (text == viewModel.SelectedOption.value),
                        role = Role.RadioButton
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    style = when(viewModel.SelectedOption.value){
                        "Small" -> MaterialTheme.typography.bodySmall
                        "Medium" -> MaterialTheme.typography.bodyMedium
                        "Large" -> MaterialTheme.typography.bodyLarge
                        else -> MaterialTheme.typography.bodyMedium
                    },
                )
                RadioButton(
                    selected = (text == viewModel.SelectedOption.value),
                    onClick = null // null recommended for accessibility with screenreaders
                )

            }
        }
        Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))
    }

}