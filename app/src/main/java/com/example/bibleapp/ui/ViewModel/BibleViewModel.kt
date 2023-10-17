package com.example.bibleapp.ui.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.TAG
import com.example.bibleapp.data.model.bibleApi.Chapter
import com.example.bibleapp.data.repository.ChapterRepository
import kotlinx.coroutines.launch

class BibleViewModel: ViewModel() {
    private val chapterRepository = ChapterRepository()
//    var chapters: List<Chapter> by mutableStateOf(listOf())

    private val _chapters:MutableLiveData<List<Chapter>> = MutableLiveData()
    val chapters:MutableLiveData<List<Chapter>> = _chapters

    fun setChapters(theChapters:List<Chapter>){
        _chapters.value = theChapters
    }


    fun getTheChapters(bookId:String){
        viewModelScope.launch {
            try {
                val response:List<Chapter> = chapterRepository.getChapters(bookId)
                setChapters(response)
//                Log.d(TAG, "The number of chapters are: ${chapters.size}")
            }catch (e:Exception){
                Log.d(TAG, "Something went wrong - ${e.message}")
            }
        }
    }
}