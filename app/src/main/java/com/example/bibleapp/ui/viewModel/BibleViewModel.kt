package com.example.bibleapp.ui.viewModel

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    //region The original function textToSpeech

//    fun textToSpeech(context: Context, textToRead:String){
//        //region Test displaying the long chapter
//
//        val maxLength = 4000 // Max length for a log message
//
//            // Your long text from the API
//        Log.d(TAG, "The length of the text: ${textToRead.length}")
//            // Split the text into smaller chunks for logging
//        var index = 0
//        while (index < textToRead.length) {
//            val end =
//                if (index + maxLength < textToRead.length) index + maxLength else textToRead.length
//            Log.d(TAG, textToRead.substring(index, end))
//            Log.d(TAG, "hello")
//            index += maxLength
//        }
//        //endregion
//        textToSpeech = TextToSpeech(context) {
//            if(it == TextToSpeech.SUCCESS){
//
//                    textToSpeech?.let{text ->
//                        text.language = Locale.US
//                        text.setSpeechRate(0.8F)
//
//                            text.speak(
//                                textToRead,
//                                TextToSpeech.QUEUE_ADD,
//                                null,
//                                null
//                            )
//                    }
//            }
//        }
//    }


    //endregion

    fun textToSpeech(context: Context, textToRead: String) {
        val maxLength = 4000 // Max length for a speech chunk

        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.apply {
                    language = Locale.US
                    setSpeechRate(0.8F)

                    var index = 0
                    while (index < textToRead.length) {
                        val chunk = textToRead.substring(index, kotlin.math.min(index + maxLength, textToRead.length))
                        speak(chunk, TextToSpeech.QUEUE_ADD, null, null)
                        index += maxLength
                    }
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
//                Log.d(TAG, "The chapter selected is: $response")
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
//                Log.d(TAG, "The number of chapters are: ${response.size}")
            }catch (e:Exception){
                Log.d(TAG, "Something went wrong - ${e.message}")
            }
        }
    }
}