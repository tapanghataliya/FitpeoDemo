package com.example.fitpeodemoapp.core.api

import com.example.fitpeodemoapp.feature.data.model.Photos
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

//    override suspend fun getUsers(): Response<List<Photos>> = apiService.getUsers()
    override suspend fun getPhoto(): Response<List<Photos>> = apiService.getPhoto()
}