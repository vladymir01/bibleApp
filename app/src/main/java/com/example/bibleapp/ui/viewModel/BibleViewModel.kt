package com.example.bibleapp.ui.viewModel

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.TAG
import com.example.bibleapp.data.model.bibleApi.Chapter
import com.example.bibleapp.data.model.rapidApi.ContentChapter
import com.example.bibleapp.data.repository.ChapterRepository
import com.example.bibleapp.data.repository.RapidApiRepository
import kotlinx.coroutines.launch
import java.util.Locale

class BibleViewModel: ViewModel() {
    private val chapterRepository = ChapterRepository()
    private val _chapters:MutableLiveData<List<Chapter>> = MutableLiveData()
    val chapters:MutableLiveData<List<Chapter>> = _chapters

    private val rapidApiRepository = RapidApiRepository()
    private val _contentChapter:MutableLiveData<ContentChapter> = MutableLiveData()
    val contentChapter:MutableLiveData<ContentChapter> = _contentChapter

    private var textToSpeech:TextToSpeech? = null

    private val _textToSpeechIsActive = mutableStateOf(true)
    val textToSpeechIsActive: State<Boolean> = _textToSpeechIsActive

    private val _darkModeIsActive = mutableStateOf(false)
    val darkModeIsActive = _darkModeIsActive

    fun setTheDarkMode(theMode: Boolean){
        _darkModeIsActive.value = theMode
    }

    fun setTextToSpeech(theMode:Boolean){
        _textToSpeechIsActive.value = theMode
    }
    private fun setContentChapter(theContentChapter: ContentChapter){
        _contentChapter.value = theContentChapter
    }

    fun textToSpeech(context: Context, textToRead:String){
        Log.d(TAG, textToRead)
        textToSpeech = TextToSpeech(context) {
            if(it == TextToSpeech.SUCCESS){

                    textToSpeech?.let{text ->
                        text.language = Locale.US
                        text.setSpeechRate(0.8F)

                            text.speak(
                                textToRead,
                                TextToSpeech.QUEUE_ADD,
                                null,
                                null
                            )
                    }
            }
        }
    }

    fun textToSpeechStop(){
        textToSpeech?.stop()
    }

    private fun setChapters(theChapters:List<Chapter>){
        _chapters.value = theChapters
    }

    fun getTheContentChapter(book:String, chapter:String){
        viewModelScope.launch{
            try {
                val response = rapidApiRepository.getChapterContent(book, chapter)
                setContentChapter(response)
                Log.d(TAG, "The chapter selected is: $response")
            }catch (e:Exception){
                Log.d(TAG, "Something bad happen - ${e.message}")
            }
        }
    }


    fun getTheChapters(bookId:String){
        viewModelScope.launch {
            try {
                val response:List<Chapter> = chapterRepository.getChapters(bookId)
                setChapters(response)
                Log.d(TAG, "The number of chapters are: ${response.size}")
            }catch (e:Exception){
                Log.d(TAG, "Something went wrong - ${e.message}")
            }
        }
    }
}