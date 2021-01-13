package com.hamidreza.maptask.repository

import com.hamidreza.maptask.data.remote.MapApi
import javax.inject.Inject

class MapRepository @Inject constructor(val api:MapApi) {

    suspend fun getRoute(coordinates:String) = api.getRoute(coordinates)
}