package com.example.myzoom.service

import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface ZoomAPIService {
    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun getZoomLocationAsync(): Deferred<ZoomLocationResponse>

    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    fun getZoomPlantAsync(): Deferred<ZoomPlantResponse>
}