package com.example.myzoom.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ZoomDataSource(private val service: ZoomAPIService) {
    suspend fun getZoomLocation() : ApiResult<Any>  {
        return withContext(Dispatchers.IO) {
            try {
                val getDeferred = service.getZoomLocationAsync()
                var result = getDeferred.await()
                return@withContext ApiResult.Success(result.result.results)
            } catch (e: HttpException) {
                return@withContext ApiResult.Error(IOException("Error getZoomLocation:", e))

            }
        }

    }

    suspend fun getZoomPlant() :ApiResult<Any>  {
        return withContext(Dispatchers.IO) {
            try {
                val getDeferred = service.getZoomPlantAsync()
                var result = getDeferred.await()
                return@withContext ApiResult.Success(result.result.results)
            } catch (e: HttpException) {
                return@withContext ApiResult.Error(IOException("Error getZoomLocation:", e))

            }
        }

    }
}