package com.example.bibleapp.data.model.rapidApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://ajith-holy-bible.p.rapidapi.com/"

val rapidApiRetrofit =  Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface  rapidApiInterface{
    @Headers("X-RapidAPI-Key:29202af9d6msh3d0c54817dd56fbp183243jsn85735d59fbe0")
    @GET("GetChapter")
    suspend fun getChapterContent(
        @Query("Book") Book:String,
        @Query("chapter") chapter: String
    ):ContentChapter
}

object rapidApiPublicObject {
    val retrofitService: rapidApiInterface by lazy {
        rapidApiRetrofit.create(rapidApiInterface::class.java)
    }
}