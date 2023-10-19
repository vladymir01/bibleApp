package com.example.bibleapp.data.repository

import com.example.bibleapp.data.model.rapidApi.ContentChapter
import com.example.bibleapp.data.model.rapidApi.rapidApiPublicObject

class RapidApiRepository {
    suspend fun getChapterContent(book:String, chapter:String):ContentChapter{
        return rapidApiPublicObject.retrofitService.getChapterContent(book, chapter)
    }
}