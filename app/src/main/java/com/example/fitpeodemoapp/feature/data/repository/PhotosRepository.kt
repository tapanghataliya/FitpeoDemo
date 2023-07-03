package com.example.fitpeodemoapp.feature.data.repository

import com.example.fitpeodemoapp.core.api.ApiHelper
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPhotos() =  apiHelper.getPhoto()
}