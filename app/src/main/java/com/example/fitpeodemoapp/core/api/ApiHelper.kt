package com.example.fitpeodemoapp.core.api

import com.example.fitpeodemoapp.feature.data.model.Photos
import retrofit2.Response

interface ApiHelper {
    suspend fun getPhoto(): Response<List<Photos>>
}
