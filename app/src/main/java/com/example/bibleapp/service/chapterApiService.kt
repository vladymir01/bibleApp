package com.example.bibleapp.service

import com.example.bibleapp.data.model.bibleApi.Chapter
import com.example.bibleapp.data.model.bibleApi.ChapterList
import com.example.bibleapp.data.model.bibleApi.ChapterResponseApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val BASE_URL = "https://api.scripture.api.bible/v1/"

val chapterRetrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ChapterApiInterface {
    @Headers("api-key:6738ca38d8e768ea3070ca9b45e928ca")
    @GET("bibles/65eec8e0b60e656b-01/books/{bookId}/chapters")
    suspend fun getDataChapters(@Path("bookId") bookId: String) : ChapterResponseApi
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */

object  ChapterApi {
    val chapterRetrofitService: ChapterApiInterface by lazy {
        chapterRetrofit.create(ChapterApiInterface::class.java)
    }
}