package com.example.myzoom.vm

import android.util.Log
import androidx.lifecycle.*
import com.example.myzoom.service.ApiResult
import com.example.myzoom.service.ZoomDataSource
import com.example.myzoom.service.ZoomLocationItem
import com.example.myzoom.service.ZoomPlantItem
import kotlinx.coroutines.launch

class ZoomViewModal (private val repository: ZoomRepository) : ViewModel(){
    private var _zoomLocationList = MutableLiveData<MutableList<ZoomLocationItem>>()
    val zoomLocationList: LiveData<MutableList<ZoomLocationItem>>
        get() = _zoomLocationList


    private var _zoomPlanList = MutableLiveData<MutableList<ZoomPlantItem>>()
    val zoomPlanList: LiveData<MutableList<ZoomPlantItem>>
        get() = _zoomPlanList

    fun getZoomLocation(){
        viewModelScope.launch {
            val result = repository.getZoomLocation()
            if( result is ApiResult.Success) {
                _zoomLocationList.postValue((result.data as List<ZoomLocationItem>).toMutableList())
            }
        }
    }


    fun getPlantList(){
        viewModelScope.launch {
            val result = repository.getZoomPlantList()
            if( result is ApiResult.Success) {
                _zoomPlanList.postValue((result.data as List<ZoomPlantItem>).toMutableList())

            }
        }
    }
    class Factory(val value: ZoomRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ZoomViewModal::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ZoomViewModal(value) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}