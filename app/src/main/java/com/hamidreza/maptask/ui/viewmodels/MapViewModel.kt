package com.hamidreza.maptask.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidreza.maptask.data.local.MapEntity
import com.hamidreza.maptask.data.remote.response.RouteResponse
import com.hamidreza.maptask.repository.MapRepository
import com.hamidreza.maptask.utils.ResultWrapper
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MapViewModel @ViewModelInject constructor(val repo:MapRepository) :ViewModel() {

    private val _path : MutableLiveData<ResultWrapper<String>> = MutableLiveData()
    val path get() = _path

    fun getPath(coordinates:String){
        viewModelScope.launch {
            try {
                val response = repo.getRoute(coordinates)
                path.postValue(handleResponse(response))
            }catch (e: IOException){
                path.postValue(ResultWrapper.Error("اینترنت خود را چک کنید"))
            }
        }
    }

    private fun handleResponse(response: Response<RouteResponse>): ResultWrapper<String>? {
        if (response.isSuccessful){
            response.body()?.let {
                return ResultWrapper.Success(it.routes[0].geometry)
            }
        }
        return ResultWrapper.Error("مبدا و مقصد را مشخص کنید")

    }

    fun insertToMap(mapEntity: MapEntity){
        viewModelScope.launch {
            repo.insertToMap(mapEntity)
        }
    }

    val getSymbols = repo.getSymbols()



}