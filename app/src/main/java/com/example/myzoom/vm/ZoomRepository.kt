package com.example.myzoom.vm

import com.example.myzoom.service.ApiResult
import com.example.myzoom.service.ZoomDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ZoomRepository(private val dataSource: ZoomDataSource) {
    suspend fun  getZoomLocation() : ApiResult<Any>  {
        return withContext(Dispatchers.IO) {
            return@withContext dataSource.getZoomLocation()
        }
    }

    suspend fun getZoomPlantList() : ApiResult<Any>  {
        return withContext(Dispatchers.IO) {
            return@withContext dataSource.getZoomPlant()
        }
    }
}