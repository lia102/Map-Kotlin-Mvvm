package com.hamidreza.maptask.data.remote.response


import com.google.gson.annotations.SerializedName

data class RouteResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("routes")
    val routes: List<Route>,
    @SerializedName("waypoints")
    val waypoints: List<Waypoint>
)