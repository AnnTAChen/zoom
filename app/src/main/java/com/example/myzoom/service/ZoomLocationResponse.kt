package com.example.myzoom.service

import com.example.myzoom.adapter.ZoomItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ZoomLocationResponse(
    @Json(name = "result")
    val result :ZoomLocationResponseResults
)

@JsonClass(generateAdapter = true)
data class ZoomLocationResponseResults(
    @Json(name = "results")
    val results :List<ZoomLocationItem>
)

@JsonClass(generateAdapter = true)
data class ZoomLocationItem(
    @Json(name = "_id")
    val id :Int,
    @Json(name = "e_name")
    val name :String,
    @Json(name = "e_pic_url")
    val pic_url :String,
    @Json(name = "e_info")
    val info :String,
    @Json(name = "e_category")
    val category :String,
    @Json(name = "e_url")
    val url :String,
    @Json(name = "e_memo")
    val memo :String
    ) : ZoomItem()