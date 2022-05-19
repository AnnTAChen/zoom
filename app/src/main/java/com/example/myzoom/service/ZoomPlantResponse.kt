package com.example.myzoom.service

import com.example.myzoom.adapter.ZoomItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ZoomPlantResponse(
    @Json(name = "result")
    val result :ZoomPlantResponseResults
)


@JsonClass(generateAdapter = true)
data class ZoomPlantResponseResults(
    @Json(name = "results")
    val results :List<ZoomPlantItem>
)

@JsonClass(generateAdapter = true)
data class ZoomPlantItem(
    @Json(name = "\uFEFFF_Name_Ch") // 中文名
    val F_Name_Ch :String="",
    @Json(name = "F_Name_En") // 英文名
    val F_Name_En :String,
    @Json(name="F_AlsoKnown") // 別名
    val F_AlsoKnown:String,
    @Json(name="F_Brief") // 簡介
    val F_Brief:String,
    @Json(name="F_Feature") // 辨認方式
    val F_Feature:String,
    @Json(name="F_Function＆Application") // 功能性
    val F_FunctionApplication:String,
    @Json(name="F_Update") // 最後更新日期
    val F_Update:String,
    @Json(name="F_Location")
    val F_Location:String,
    @Json(name="F_Pic01_URL")
    val F_Pic01_URL:String

): ZoomItem()