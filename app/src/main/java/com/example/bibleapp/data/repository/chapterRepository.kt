package com.example.bibleapp.data.repository

import com.example.bibleapp.data.model.bibleApi.Chapter
import com.example.bibleapp.service.ChapterApi

class ChapterRepository(){
    suspend fun getChapters(bookId:String):List<Chapter>{
        return ChapterApi.chapterRetrofitService.getDataChapters(bookId).data
    }
}