package com.example.fitpeodemoapp.feature.data.model

import com.squareup.moshi.Json

data class Photos(
    @Json(name = "category")
    val category: String = "",
    @Json(name = "imageUrl")
    val imageUrl: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "desc")
    val desc: String = ""

//    @Json(name = "id")
//val id: Int = 0,
//@Json(name = "title")
//val title: String = "",
//@Json(name = "url")
//val url: String = "",
//@Json(name = "thumbnailUrl")
//val thumbnailUrl: String = ""
)
