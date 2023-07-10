package com.example.fitpeodemoapp.core.api

import com.example.fitpeodemoapp.feature.data.model.Photos
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

//    @GET("photos")
    @GET("movielist.json")
    suspend fun getPhoto(): Response<List<Photos>>

}