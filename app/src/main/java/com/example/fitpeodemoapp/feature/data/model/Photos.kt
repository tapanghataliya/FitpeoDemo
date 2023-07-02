package com.example.fitpeodemoapp.feature.data.model

import com.squareup.moshi.Json

data class Photos(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "title")
    val title: String = "",
    @Json(name = "url")
    val url: String = "",
    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String = ""
)
