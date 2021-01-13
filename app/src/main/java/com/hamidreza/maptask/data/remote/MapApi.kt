package com.hamidreza.maptask.data.remote

import com.hamidreza.maptask.data.remote.response.RouteResponse
import com.hamidreza.maptask.utils.Conts.MAP_BOX_TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MapApi {


    @GET("{coordinates}")
    @Headers( "x-api-key:$MAP_BOX_TOKEN" )
    suspend fun getRoute(
        @Path("coordinates") coordinates: String,
        @Query("overview")  overview :String ="full",
        @Query("steps")  steps :Boolean =true,
        @Query("alternatives")  alternatives :Boolean =true
    ) : Response<RouteResponse>

}