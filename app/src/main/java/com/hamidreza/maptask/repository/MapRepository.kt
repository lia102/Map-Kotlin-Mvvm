package com.hamidreza.maptask.repository

import com.hamidreza.maptask.data.local.MapDao
import com.hamidreza.maptask.data.local.MapEntity
import com.hamidreza.maptask.data.remote.MapApi
import javax.inject.Inject

class MapRepository @Inject constructor(val api:MapApi,val dao:MapDao) {

    suspend fun getRoute(coordinates:String) = api.getRoute(coordinates)

    suspend fun insertToMap(mapEntity: MapEntity) = dao.insertToMap(mapEntity)

    fun getSymbols() = dao.getSymbols()
}