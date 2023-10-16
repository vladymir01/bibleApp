package com.example.bibleapp.ui.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.TAG
import com.example.bibleapp.data.model.bibleApi.Chapter
import com.example.bibleapp.data.repository.ChapterRepository
import kotlinx.coroutines.launch

class BibleViewModel: ViewModel() {
    private val chapterRespository = ChapterRepository()
    var chapters: List<Chapter> by mutableStateOf(listOf())

    fun getTheChapters(bookId:String){
        viewModelScope.launch {
            try {
                chapters = chapterRespository.getChapters(bookId)
                Log.d(TAG, "The number of chapters are: ${chapters.size}")
            }catch (e:Exception){
                Log.d(TAG, "Something went wrong - ${e.message}")
            }
        }
    }
}